import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ArticleProcessor {
    private List<String> stopWords;
    private Map<String, Integer> sentimentLexicon;

    public ArticleProcessor(String stopWordsFilePath, String sentimentLexiconFilePath) throws FileNotFoundException {
        stopWords = new ArrayList<>();
        sentimentLexicon = new HashMap<>();

        //  stop words
        Scanner sc = new Scanner(new File(stopWordsFilePath));
        while (sc.hasNext()) {
            stopWords.add(sc.next().toLowerCase());
        }
        sc.close();

        //  sentiment lexicon
        sc = new Scanner(new File(sentimentLexiconFilePath));
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(",");
            if (line.length == 2) {
                String word = line[0].toLowerCase();
                int score = Integer.parseInt(line[1]);
                sentimentLexicon.put(word, score);
            }
        }
        sc.close();
    }

    // analyze  article
    public ArticleAnalysis analyzeArticle(String filePath) throws FileNotFoundException {
        Set<String> uniqueWords = new HashSet<>();
        List<String> words = new ArrayList<>();
        List<Integer> frequencies = new ArrayList<>();
        int wordCount = 0;
        int sentenceCount = 0;
        int sentimentScore = 0;

        Scanner sc = new Scanner(new File(filePath));
        StringBuilder processedText = new StringBuilder();

        while (sc.hasNextLine()) {
            String sentence = sc.nextLine();
            String[] wordArray = sentence.toLowerCase().split("\\s+");

            // count sentences
            sentenceCount += sentence.split("[.!?]").length;
            for (String word : wordArray) {
                word = word.replaceAll("[^a-zA-Z]", "");
                if (!stopWords.contains(word) && !word.isEmpty()) {
                    processedText.append(word).append(" ");
                    uniqueWords.add(word);
                    wordCount++;

                    //  word frequency
                    int wordIndex = words.indexOf(word);
                    if (wordIndex != -1) {
                        frequencies.set(wordIndex, frequencies.get(wordIndex) + 1);
                    } else {
                        words.add(word);
                        frequencies.add(1);
                    }

                    //  sentiment score if word is lexicon
                    if (sentimentLexicon.containsKey(word)) {
                        sentimentScore += sentimentLexicon.get(word);
                    }
                }
            }
        }
        sc.close();

        //  word frequencies
        for (int i = 0; i < frequencies.size(); i++) {
            for (int j = i + 1; j < frequencies.size(); j++) {
                if (frequencies.get(i) < frequencies.get(j)) {
                    int tempFreq = frequencies.get(i);
                    frequencies.set(i, frequencies.get(j));
                    frequencies.set(j, tempFreq);

                    String tempWord = words.get(i);
                    words.set(i, words.get(j));
                    words.set(j, tempWord);
                }
            }
        }

        double vocabRichness = uniqueWords.size() / (double) wordCount;
        return new ArticleAnalysis(processedText.toString().trim(), wordCount, sentenceCount, words, frequencies, sentimentScore, vocabRichness);
    }

    // multiple articles
    public void compareArticles(String[] filePaths) throws FileNotFoundException {
        List<ArticleAnalysis> analyses = new ArrayList<>();
        for (String filePath : filePaths) {
            analyses.add(analyzeArticle(filePath));
        }

        ArticleAnalysis richestVocabArticle = analyses.get(0);
        for (ArticleAnalysis analysis : analyses) {
            if (analysis.getVocabRichness() > richestVocabArticle.getVocabRichness()) {
                richestVocabArticle = analysis;
            }
            System.out.println("Article Analysis:");
            analysis.displayResults();
            System.out.println();
        }

        System.out.println("Article with the Richest Vocabulary:");
        richestVocabArticle.displayResults();
    }

    // method with User Interface
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Article Processor!");

        try {
            ArticleProcessor processor = new ArticleProcessor(
                    "/Users/a/Downloads/stopwords.txt",
                    "/Users/a/Desktop/sentiment.txt"
            );

            boolean running = true;
            while (running) {
                System.out.println("\nMenu:");
                System.out.println("1. Analyze Articles in a Directory");
                System.out.println("2. Add and Analyze a New Article");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        System.out.print("Enter the directory path: ");
                        String dirPath = scanner.nextLine();
                        File dir = new File(dirPath);
                        if (dir.isDirectory()) {
                            String[] files = Arrays.stream(dir.listFiles())
                                    .filter(file -> file.getName().endsWith(".txt"))
                                    .map(File::getAbsolutePath)
                                    .toArray(String[]::new);
                            processor.compareArticles(files);
                        } else {
                            System.out.println("Invalid directory path.");
                        }
                        break;

                    case 2:
                        System.out.print("Enter the article file path: ");
                        String articlePath = scanner.nextLine();
                        File articleFile = new File(articlePath);
                        if (articleFile.exists() && articleFile.isFile()) {
                            ArticleAnalysis analysis = processor.analyzeArticle(articleFile.getAbsolutePath());
                            analysis.displayResults();
                        } else {
                            System.out.println("Invalid file path.");
                        }
                        break;

                    case 3:
                        running = false;
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

//  store and display analysis results
class ArticleAnalysis {
    private String processedText;
    private int wordCount;
    private int sentenceCount;
    private List<String> words;
    private List<Integer> frequencies;
    private int sentimentScore;
    private double vocabRichness;

    public ArticleAnalysis(String processedText, int wordCount, int sentenceCount, List<String> words, List<Integer> frequencies, int sentimentScore, double vocabRichness) {
        this.processedText = processedText;
        this.wordCount = wordCount;
        this.sentenceCount = sentenceCount;
        this.words = words;
        this.frequencies = frequencies;
        this.sentimentScore = sentimentScore;
        this.vocabRichness = vocabRichness;
    }

    public double getVocabRichness() {
        return vocabRichness;
    }

    public void displayResults() {
        System.out.println("Word Count: " + wordCount);
        System.out.println("Sentence Count: " + sentenceCount);
        System.out.println("Vocabulary Richness: " + vocabRichness);
        System.out.println("Word Frequencies (Sorted):");
        for (int i = 0; i < words.size(); i++) {
            System.out.println(words.get(i) + ": " + frequencies.get(i));
        }
        System.out.println("Sentiment Score: " + sentimentScore);
    }
}
