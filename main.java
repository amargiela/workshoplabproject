import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ArticleProcessor {

    private List<String> stopWords;

    // stop words loader
    public ArticleProcessor(String stopWordsFilePath) throws FileNotFoundException {
        stopWords = new ArrayList<>();
        Scanner sc = new Scanner(new File(stopWordsFilePath));
        while (sc.hasNext()) {
            stopWords.add(sc.next().toLowerCase());
        }
        sc.close();
    }

    // stop words removed and stats
    public void preprocessFile(String filePath) throws FileNotFoundException {
        StringBuilder processedText = new StringBuilder();
        List<String> words = new ArrayList<>();
        List<Integer> frequencies = new ArrayList<>();
        int wordCount = 0;
        int sentenceCount = 0;

        Scanner sc = new Scanner(new File(filePath));

        while (sc.hasNextLine()) {
            String sentence = sc.nextLine();
            String[] wordArray = sentence.toLowerCase().split("\\s+");

            // sentence counter
            sentenceCount += sentence.split("[.!?]").length;

            for (String word : wordArray) {
                word = word.replaceAll("[^a-zA-Z]", "");
                if (!stopWords.contains(word) && !word.isEmpty()) {
                    processedText.append(word).append(" ");
                    wordCount++;

                    // word frequency counter
                    int wordIndex = words.indexOf(word);
                    if (wordIndex != -1) {
                        frequencies.set(wordIndex, frequencies.get(wordIndex) + 1);
                    } else {
                        words.add(word);
                        frequencies.add(1);
                    }
                }
            }
        }
        sc.close();

        // frequency sorter used bubble sort
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

        // out
        System.out.println("processed text: " + processedText.toString().trim());
        System.out.println("word count: " + wordCount);
        System.out.println("sentence count: " + sentenceCount);
        System.out.println("word frequencies (sorted): ");

        // output of word frequencies
        for (int i = 0; i < words.size(); i++) {

            System.out.println(words.get(i) + ": " + frequencies.get(i));
        }
    }

    public static void main(String[] args) {
        try {
            //stop words file
            ArticleProcessor processor = new ArticleProcessor("/Users/a/Downloads/stopwords.txt");

            // article file
            processor.preprocessFile("/Users/a/Desktop/article.txt");

        } catch (FileNotFoundException e) {

            System.out.println("file not found: " + e.getMessage());
        }
    }
}
