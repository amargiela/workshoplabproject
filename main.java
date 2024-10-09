import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ArticleProcessor {

    private List<String> stopWords;

    public ArticleProcessor(String stopWordsFilePath) throws FileNotFoundException {
        stopWords = new ArrayList<>();
        Scanner sc = new Scanner(new File(stopWordsFilePath));
        while (sc.hasNext()) {
            stopWords.add(sc.next().toLowerCase());
        }
        sc.close();
    }

    public String preprocessFile(String filePath) throws FileNotFoundException {
        StringBuilder processedText = new StringBuilder();
        Scanner sc = new Scanner(new File(filePath));

        while (sc.hasNext()) {
            String word = sc.next().toLowerCase().replaceAll("[^a-zA-Z]", "");
            if (!stopWords.contains(word) && !word.isEmpty()) {
                processedText.append(word).append(" ");
            }
        }
        sc.close();
        return processedText.toString().trim();
    }

    public static void main(String[] args) {
        try {
            ArticleProcessor processor = new ArticleProcessor("/Users/a/Downloads/stopwords.txt");

            String result = processor.preprocessFile("/Users/a/Desktop/article.txt");

            System.out.println(result);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
