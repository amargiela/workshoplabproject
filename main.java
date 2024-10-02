import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ArticleProcessor {

    private List<String> stopWords;

    public ArticleProcessor(String stopWordsFilePath) throws FileNotFoundException {
        stopWords = new ArrayList<>();
        Scanner sc = new Scanner(new File(stopWordsFilePath));
        while (sc.hasNext()) {
            stopWords.add(sc.next());
        }
        sc.close();
    }
