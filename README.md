
# ArticleProcessor

## Overview

The **ArticleProcessor** is a Java program designed to process text articles by removing stop words, counting words and sentences, calculating word frequencies, performing sentiment analysis, and measuring vocabulary richness. It can compare multiple articles to highlight differences in vocabulary richness, word frequency, and sentiment. Users can interact with the program via a text-based interface to analyze directories or add new articles dynamically.

## Features

- **Stop Words Removal**: Reads a list of stop words from a file and removes them from the article.
- **Word Count**: Counts the total number of words in the processed article.
- **Sentence Count**: Counts the total number of sentences in the article.
- **Word Frequency Analysis**: Calculates and sorts the frequency of each word in descending order.
- **Sentiment Analysis**: Calculates a sentiment score based on a lexicon of words with associated sentiment values.
- **Vocabulary Richness**: Measures vocabulary richness as the unique-to-total word ratio.
- **Multiple Article Comparison**: Compares multiple articles on vocabulary richness, word frequency, and sentiment.
- **Interactive User Interface**: Provides a menu for analyzing directories or adding new articles dynamically.

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

### 5. Run the Program with Interactive Interface

Open a terminal or command prompt, navigate to the directory containing `ArticleProcessor.java`, and compile:

```bash
javac ArticleProcessor.java
javac ArticleAnalysis.java
```

Run the program:

```bash
java ArticleProcessor
```

When the program starts, you can choose from the following menu options:
1. Analyze all articles in a directory.
2. Add and analyze a single new article.
3. Exit the program.

### Example Input and Output

- Input for **Option 1**:
  ```
  Enter the directory path: /Users/a/Desktop/Articles/
  ```

- Input for **Option 2**:
  ```
  Enter the article file path: /Users/a/Desktop/new_article.txt
  ```

- Example Output:
  ```
  Word Count: 905
  Sentence Count: 239
  Vocabulary Richness: 0.726
  Word Frequencies (Sorted):
  campaign: 13
  harris: 10
  trump: 9
  ...
  Sentiment Score: 28
  ```

---

## How It Works

### 1. Loading Stop Words and Sentiment Lexicon
The constructor `ArticleProcessor(String stopWordsFilePath, String sentimentLexiconFilePath)` reads the stop words and sentiment words from specified files and stores them in a `List<String>` and `Map<String, Integer>`.

### 2. Analyzing Articles
The method `analyzeArticle(String filePath)` processes a single article by:
- Removing stop words.
- Calculating word and sentence counts.
- Sorting word frequencies.
- Calculating sentiment scores and vocabulary richness.

### 3. Comparing Articles
The method `compareArticles(String[] filePaths)` analyzes multiple articles, comparing their vocabulary richness, sentiment scores, and word frequencies. It identifies and displays the article with the richest vocabulary.

### 4. Interactive Interface
Users can dynamically:
- Analyze all articles in a specified directory.
- Add and process a single new article.

## Notes

- Ensure all file paths in the `main` method are correct and accessible.
- Articles should be plain `.txt` files for proper processing.
- Vocabulary richness is calculated as the unique-to-total word ratio.

## Troubleshooting

- **FileNotFoundException**: If encountered, verify that the specified file paths exist and are correctly formatted.
- **Encoding Issues**: Save text files with UTF-8 encoding to avoid character recognition problems.

## Dependencies

- Java Standard Library classes:
  - `java.io.File`
  - `java.io.FileNotFoundException`
  - `java.util.Scanner`
  - `java.util.List`
  - `java.util.ArrayList`
  - `java.util.Map`
  - `java.util.HashMap`

## License

This project is not licensed.

## Member Info

For questions or suggestions, please contact [austinmargiela@gmail.com](mailto:austinmargiela@gmail.com) or [matthewmonasterski@gmail.com](mailto:matthewmonasterski@gmail.com).

---
