import java.util.ArrayList;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class processor {

	public static void main(String[] args) throws Exception {

		// pass the path to the file as a parameter
		// File file = new File(
		// "C:\\Users\\mathe\\Desktop\\article1.txt"); //testing scanning text files
		// Scanner sc = new Scanner(file);

		// while (sc.hasNextLine())
		// System.out.println(sc.nextLine());

		try {
			ArticleProcessor processor = new ArticleProcessor("C:\\Users\\mathe\\Downloads\\stopwords.txt");

			String result = processor.preprocessFile("/Users/a/Desktop/article.txt"); // testing code logic

			System.out.println(result);
			String presult = result;
			System.out.println(processor.WordCount(result));
			processor.wordFrequency(presult);

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		}
	}

}

class ArticleProcessor {
	private List<String> stopWords;

	public ArticleProcessor(String stopWordsFilePath) throws FileNotFoundException {
		stopWords = new ArrayList<>();
		Scanner sc = new Scanner(new File("C:\\Users\\mathe\\Downloads\\stopwords.txt")); // importing stop words
		while (sc.hasNext()) {
			stopWords.add(sc.next().toLowerCase());
		}
		sc.close();
	}

	public String preprocessFile(String filePath) throws FileNotFoundException {
		StringBuilder processedText = new StringBuilder();
		Scanner sc = new Scanner(new File("C:\\Users\\mathe\\Desktop\\article1.txt"));

		while (sc.hasNext()) {
			String word = sc.next().toLowerCase().replaceAll("[^a-zA-Z]", ""); // removing stop words
			if (!stopWords.contains(word) && !word.isEmpty()) {
				processedText.append(word).append(" ");
			}
		}
		sc.close();
		return processedText.toString().trim(); // returning processed text

	}

	public int WordCount(String processedText) {
		String[] words = processedText.split("\\s+"); // returning total word count
		return words.length;
	}

	public void wordFrequency(String procesedText) {
		List<String> words = new ArrayList<String>(Arrays.asList(procesedText.split("\\s+")));
		ArrayList<wordCount> wordCountList = new ArrayList<>();
		for (String word : words) {
			boolean found = false;
			// Check if the word is already in the wordCountList
			for (wordCount wc : wordCountList) {
				if (wc.word.equals(word)) {
					wc.count++;
					found = true;
					break;
				}
			}
			// If the word is not found, add it to the list
			if (!found) {
				wordCountList.add(new wordCount(word, 1));
			}
			// Sort the wordCountList by count in descending order using Bubble Sort
			for (int i = 0; i < wordCountList.size() - 1; i++) {
				for (int j = 0; j < wordCountList.size() - i - 1; j++) {
					if (wordCountList.get(j).count < wordCountList.get(j + 1).count) {
						// Swap wordCountList[j] and wordCountList[j+1]
						wordCount temp = wordCountList.get(j);
						wordCountList.set(j, wordCountList.get(j + 1));
						wordCountList.set(j + 1, temp);
					}
				}
			}
			// Print the words ranked by frequency

		}
		System.out.println("Word Ranking by Frequency:");
		for (wordCount wc : wordCountList) {
			System.out.println(wc.word + ": " + wc.count);
		}
	}

}

class wordCount {
	String word;
	int count;

	wordCount(String word, int count) {
		this.word = word;
		this.count = count;
	}
}
