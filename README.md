# ArticleProcessor

## Overview

The **ArticleProcessor** is a Java program designed to process text articles by removing stop words, counting words and sentences, calculating word frequencies, and outputting the processed text along with statistical information.

## Features

- **Stop Words Removal**: Reads a list of stop words from a file and removes them from the article.
- **Word Count**: Counts the total number of words in the processed article.
- **Sentence Count**: Counts the total number of sentences in the article.
- **Word Frequency Analysis**: Calculates and sorts the frequency of each word in descending order.
- **Processed Text Output**: Outputs the article text after processing.

## Prerequisites

- **Java Development Kit (JDK)**: Ensure you have JDK 8 or higher installed.
- **Text Files**:
  - **Stop Words File**: A text file containing stop words (one word per line).
  - **Article File**: A text file containing the article you wish to process.

## Getting Started

### 1. Clone or Download the Source Code

Copy the `ArticleProcessor.java` file into your project directory.

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

### 3. Prepare the Article File

Create or obtain a text file named `article.txt` containing the article you want to process.

### 4. Update File Paths in the Code

In the `main` method of `ArticleProcessor.java`, update the file paths to point to your `stopwords.txt` and `article.txt` files:

```java
public static void main(String[] args) {
    try {
        // Update the file paths to your actual file locations
        ArticleProcessor processor = new ArticleProcessor("/path/to/stopwords.txt");
        processor.preprocessFile("/path/to/article.txt");
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + e.getMessage());
    }
}
```

### 5. Compile the Program

Open a terminal or command prompt, navigate to the directory containing `ArticleProcessor.java`, and run:

```bash
javac ArticleProcessor.java
```

### 6. Run the Program

After successful compilation, run the program using:

```bash
java ArticleProcessor
```

## Example Output

```
Processed text: example text after processing without stop words
Word count: 150
Sentence count: 10
Word frequencies (sorted):
example: 15
text: 12
processing: 8
without: 5
stop: 3
words: 2
```

## How It Works

1. **Loading Stop Words**:
   - The constructor `ArticleProcessor(String stopWordsFilePath)` reads the stop words from the specified file and stores them in a `List<String>` called `stopWords`.

2. **Processing the Article**:
   - The method `preprocessFile(String filePath)` reads the article file line by line.
   - Converts all text to lowercase and splits it into words.
   - Removes punctuation and filters out stop words.
   - Counts words and sentences.
   - Calculates word frequencies and sorts them in descending order using bubble sort.

3. **Output**:
   - Prints the processed text without stop words and punctuation.
   - Displays the total word count and sentence count.
   - Lists the word frequencies in sorted order.

## Notes

- **File Paths**: Ensure the file paths provided in the `main` method are correct and accessible by the program.
- **Text Processing**: The program uses basic string manipulation; complex text structures may require additional parsing logic.
- **Performance**: The bubble sort algorithm is used for simplicity. For larger datasets, consider using more efficient sorting methods.

## Dependencies

- Java Standard Library classes:
  - `java.io.File`
  - `java.io.FileNotFoundException`
  - `java.util.Scanner`
  - `java.util.List`
  - `java.util.ArrayList`

## Troubleshooting

- **FileNotFoundException**: If you encounter this exception, check that the file paths are correct and that the files exist.
- **Character Encoding**: Ensure that your text files are saved with the correct encoding (e.g., UTF-8) to avoid character recognition issues.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For questions or suggestions, please contact [austinmargiela@gmail.com](mailto:austinmargiela@gmail.com).

---

**Disclaimer**: This program is intended for educational purposes and may need adjustments to handle specific use cases or larger datasets efficiently.
