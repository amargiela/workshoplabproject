package ArticleProcessor;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;
public class ArticleProcessor2 {
    private List<String> stopWords;
    private Map<String, Integer> sentimentLexicon;

    // constructor to load stop words and the sentiment
    public ArticleProcessor(String stopWordsFilePath , String sentimentLexiconFilePath) throws FileNotFoundException {
        stopWords = new ArrayList<>();
        sentimentLexicon = new HashMap<>();

        // stop words load
        Scanner sc = new Scanner(new File(stopWordsFilePath));
        while (sc.hasNext()) {
            stopWords.add(sc.next().toLowerCase());
        }
        sc.close();

        // sentiment load
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
    // method to process an article and return its analysis result
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

                    // word count frequency
                    int wordIndex = words.indexOf(word);
                    if (wordIndex != -1) {
                        frequencies.set(wordIndex, frequencies.get(wordIndex) + 1);
                    } else {
                        words.add(word);
                        frequencies.add(1);
                    }

                    // add sentiment score if word is in lexicon
                    if (sentimentLexicon.containsKey(word)) {
                        sentimentScore += sentimentLexicon.get(word);
                    }
                }
            }
        }
        sc.close();

        // sort the word frequencies
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

        // calculate vocabulary richness
        double vocabRichness = uniqueWords.size() / (double) wordCount;

        // return  analysis object
        return new ArticleAnalysis(processedText.toString().trim(), wordCount, sentenceCount, words, frequencies, sentimentScore, vocabRichness);
    }
    // nethod to compare multiple articles
    public void compareArticles(String[] filePaths) throws FileNotFoundException {
        List<ArticleAnalysis> analyses = new ArrayList<>();
        for (String filePath : filePaths) {
            analyses.add(analyzeArticle(filePath));
        }

        // richest vocabulary and display all analysis results
        ArticleAnalysis richestVocabArticle = analyses.get(0);
        for (ArticleAnalysis analysis : analyses) {
            if (analysis.getVocabRichness() > richestVocabArticle.getVocabRichness()) {
                richestVocabArticle = analysis;
            }
            System.out.println("article analysis:");
            analysis.displayResults();
            System.out.println();
        }

        System.out.println("article with the richest vocabulary:");
        richestVocabArticle.displayResults();
    }
    public static void main(String[] args) {
        try {
            ArticleProcessor processor = new ArticleProcessor(
                    "/Users/a/Downloads/stopwords.txt",
                    "/Users/a/Desktop/sentiment.txt"
            );

            // process multiple articles
            processor.compareArticles(new String[]{
                    "/Users/a/Desktop/Articles/article1.txt",
                    "/Users/a/Desktop/Articles/article2.txt",
                    "/Users/a/Desktop/Articles/article3.txt",

            });

        } catch (FileNotFoundException e) {
            System.out.println("file not found: " + e.getMessage());
        }
    }
}
class library {
    public static HashMap<String, ArrayList<String>> artLibrary = new HashMap<>();

    static {
        artLibrary.put("Politics", new ArrayList<>());
        artLibrary.put("Sports", new ArrayList<>());
        artLibrary.put("News", new ArrayList<>());

        // Correct the file paths based on where your files are located
        loadCategoryFromFile("Politics", "C:\\Users\\Matt\\Desktop\\java2\\ArticleProcessor\\article1.txt");
        loadCategoryFromFile("Sports", "C:\\Users\\Matt\\Desktop\\java2\\ArticleProcessor\\Sports.txt");
        loadCategoryFromFile("News", "C:\\Users\\Matt\\Desktop\\java2\\ArticleProcessor\\News.txt");
    }

    // Updated method to load file contents into the respective category
    private static void loadCategoryFromFile(String category, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                artLibrary.get(category).add(line); // Add each line (article) to the category's ArrayList
            }
        } catch (IOException e) {
            System.out.println("Error loading category " + category + " from file: " + e.getMessage());
        }
    }

    // Process articles in the selected category
    public static void processCategoryArticles(String category, ArticleProcessor processor) {
        ArrayList<String> articles = artLibrary.get(category);

        if (articles == null || articles.isEmpty()) {
            System.out.println("No articles found for the category: " + category);
            return;
        }

        try {
            System.out.println("Processing articles for category: " + category);
            for (String article : articles) {
                // If you are adding file paths as articles, ensure they are processed as such
                System.out.println("\nAnalyzing article: " + article);
                ArticleAnalysis analysis = processor.analyzeArticle(article);
                analysis.displayResults();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error processing articles: " + e.getMessage());
        }
    }
}

//  class to store and display analysis results
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
        System.out.println("word count: " + wordCount);
        System.out.println("sentence count: " + sentenceCount);
        System.out.println("vocabulary richness: " + vocabRichness);
        System.out.println("word frequencies (sorted): ");
        for (int i = 0; i < words.size(); i++) {
            System.out.println(words.get(i) + ": " + frequencies.get(i));
        }
        System.out.println("sentiment Score: " + sentimentScore);
    }
}
