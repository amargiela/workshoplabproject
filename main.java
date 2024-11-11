import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ArticleProcessor {
    private List<String> stopWords;
    private Map<String, Integer> sentimentLexicon;

    // constructor to load stop words and the sentiment
    public ArticleProcessor(String stopWordsFilePath, String sentimentLexiconFilePath) throws FileNotFoundException {
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
    // method to process file and do sentiment analysis
    public void preprocessFile(String filePath) throws FileNotFoundException {
        StringBuilder processedText = new StringBuilder();
        List<String> words = new ArrayList<>();
        List<Integer> frequencies = new ArrayList<>();
        int wordCount = 0;
        int sentenceCount = 0;
        int sentimentScore = 0;

        Scanner sc = new Scanner(new File(filePath));

        while (sc.hasNextLine()) {
            String sentence = sc.nextLine();
            String[] wordArray = sentence.toLowerCase().split("\\s+");

            // count sentences
            sentenceCount += sentence.split("[.!?]").length;
            for (String word : wordArray) {
                word = word.replaceAll("[^a-zA-Z]", "");
                if (!stopWords.contains(word) && !word.isEmpty()) {
                    processedText.append(word).append(" ");
                    wordCount++;
                    // word count frequency
                    int wordIndex = words.indexOf(word);
                    if (wordIndex != -1) {
                        frequencies.set(wordIndex, frequencies.get(wordIndex) + 1);
                    } else {
                        words.add(word);
                        frequencies.add(1);
                    }

                    // add  sentiment score if word is in lexicon file
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

        // results
        System.out.println("processed text: " + processedText.toString().trim());
        System.out.println("word count: " + wordCount);
        System.out.println("sentence count: " + sentenceCount);
        System.out.println("word frequencies (sorted): ");
        for (int i = 0; i < words.size(); i++) {
            System.out.println(words.get(i) + ": " + frequencies.get(i));
        }
        System.out.println("sentiment score: " + sentimentScore);
    }

    public static void main(String[] args) {
        try {
            ArticleProcessor processor = new ArticleProcessor(
                    "/Users/a/Downloads/stopwords.txt",
                    "/Users/a/Desktop/sentiment.txt"
            );

            // process article
            processor.preprocessFile("/Users/a/Desktop/article.txt");

        } catch (FileNotFoundException e) {
            System.out.println("file not found: " + e.getMessage());
        }
    }
}
