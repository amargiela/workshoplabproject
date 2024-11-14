
# ArticleProcessor

## Overview

The **ArticleProcessor** is a Java program designed to process text articles by removing stop words, counting words and sentences, calculating word frequencies, performing sentiment analysis, and measuring vocabulary richness. It can compare multiple articles to highlight differences in vocabulary richness, word frequency, and sentiment.

## Features

- **Stop Words Removal**: Reads a list of stop words from a file and removes them from the article.
- **Word Count**: Counts the total number of words in the processed article.
- **Sentence Count**: Counts the total number of sentences in the article.
- **Word Frequency Analysis**: Calculates and sorts the frequency of each word in descending order.
- **Sentiment Analysis**: Calculates a sentiment score based on a lexicon of words with associated sentiment values.
- **Vocabulary Richness**: Measures vocabulary richness as the unique-to-total word ratio.
- **Multiple Article Comparison**: Compares multiple articles on vocabulary richness, word frequency, and sentiment.

## Prerequisites

- **Java Development Kit (JDK)**: Ensure you have JDK 8 or higher installed.
- **Text Files**:
  - **Stop Words File**: A text file containing stop words (one word per line).
  - **Sentiment Lexicon File**: A CSV file containing words and their sentiment scores (e.g., `happy,1`).
  - **Article Files**: Text files containing the articles to process and compare.

## Getting Started

### 1. Clone or Download the Source Code

Copy `ArticleProcessor.java` and `ArticleAnalysis.java` into your project directory.

### 2. Prepare the Stop Words File

Create a text file named `stopwords.txt` containing stop words, each on a new line. For example:

```
a
an
the
and
or
but
if
while
with
```

### 3. Prepare the Sentiment Lexicon File

Create a CSV file named `sentiment.txt` with each word and its sentiment score, separated by a comma. For example:

```
happy,1
sad,-1
joy,2
anger,-2
```

### 4. Prepare the Article Files

Create or obtain text files named `article1.txt`, `article2.txt`, etc., containing the articles to process and compare.

### 5. Update File Paths in the Code

In the `main` method of `ArticleProcessor.java`, update the file paths to point to your `stopwords.txt`, `sentiment.txt`, and article files:

```java
public static void main(String[] args) {
    try {
        ArticleProcessor processor = new ArticleProcessor(
                "/path/to/stopwords.txt",
                "/path/to/sentiment.txt"
        );
        
        processor.compareArticles(new String[]{
                "/path/to/article1.txt",
                "/path/to/article2.txt"
        });
        
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + e.getMessage());
    }
}
```

### 6. Compile the Program

Open a terminal or command prompt, navigate to the directory containing `ArticleProcessor.java`, and run:

```bash
javac ArticleProcessor.java
javac ArticleAnalysis.java
```

### 7. Run the Program

After successful compilation, run the program using:

```bash
java ArticleProcessor
```

## Example Output

```
Article Analysis:
Processed text: example text after processing without stop words
Word count: 150
Sentence count: 10
Vocabulary richness: 0.67
Word frequencies (sorted):
example: 15
text: 12
processing: 8
without: 5
stop: 3
words: 2
Sentiment score: 4

Article with the richest vocabulary:
Processed text: another example text ...
Word count: 130
Sentence count: 8
Vocabulary richness: 0.75
```

## How It Works

1. **Loading Stop Words and Sentiment Lexicon**:
   - The constructor `ArticleProcessor(String stopWordsFilePath, String sentimentLexiconFilePath)` reads the stop words and sentiment words from specified files and stores them in a `List<String>` and `Map<String, Integer>`.

2. **Processing the Article**:
   - The method `analyzeArticle(String filePath)` reads the article file line by line.
   - Converts all text to lowercase, splits it into words, removes punctuation, and filters out stop words.
   - Counts words, sentences, and calculates word frequencies, sentiment score, and vocabulary richness.

3. **Comparing Articles**:
   - The method `compareArticles(String[] filePaths)` processes multiple articles, compares vocabulary richness, identifies the most repeated words, and calculates sentiment scores for each article.

4. **Output**:
   - Prints the processed text, word count, sentence count, vocabulary richness, word frequencies, and sentiment score for each article. Highlights the article with the highest vocabulary richness.

## Notes

- **File Paths**: Ensure the file paths provided in the `main` method are correct and accessible by the program.
- **Sorting**: A simple sorting method is used for word frequencies; for large datasets, consider more efficient sorting algorithms.
- **Analysis Method**: Vocabulary richness is calculated as the unique-to-total word ratio, and sentiment score sums values from the sentiment lexicon.

## Dependencies

- Java Standard Library classes:
  - `java.io.File`
  - `java.io.FileNotFoundException`
  - `java.util.Scanner`
  - `java.util.List`
  - `java.util.ArrayList`
  - `java.util.Map`
  - `java.util.HashMap`

## Troubleshooting

- **FileNotFoundException**: If you encounter this exception, check that the file paths are correct and that the files exist.
- **Character Encoding**: Ensure that your text files are saved with the correct encoding (e.g., UTF-8) to avoid character recognition issues.

## License

This project is not licensed.

## Member Info

For questions or suggestions, please contact [austinmargiela@gmail.com](mailto:austinmargiela@gmail.com) or [matthewmonasterski@gmail.com](mailto:matthewmonasterski@gmail.com).

---

**Disclaimer**: This program is intended for educational purposes only.
