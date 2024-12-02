import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Thank you for choosing our article processor!");

        // Load the ArticleProcessor instance
        ArticleProcessor processor = null;
        try {
            processor = new ArticleProcessor(
                    "C:\\Users\\Matt\\Downloads\\stopwords.txt",
                    "C:\\Users\\Matt\\Downloads\\lexicon_scores.txt"
            );
        } catch (FileNotFoundException e) {
            System.out.println("Error initializing ArticleProcessor: " + e.getMessage());
            return; // Exit if the processor cannot be initialized
        }

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Choose category");
            System.out.println("2. Input new article");
            System.out.println("3. Perform text analysis");
            System.out.println("4. Compare articles");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
// Choose and process a category
    try {
        // Display available categories
        System.out.println("Available categories:");
        for (String category : library.artLibrary.keySet()) {
            System.out.println("- " + category);
        }

        // Ask the user for a category to process
        System.out.print("\nEnter a category to process articles: ");
        String chosenCategory = scanner.nextLine();

        // Check if the category exists
        if (library.artLibrary.containsKey(chosenCategory)) {
            // Process articles in the chosen category
            library.processCategoryArticles(chosenCategory, processor);
        } else {
            System.out.println("Category does not exist. Please try again.");
        }

   
    } catch (Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
    }
    break;

                case 2:
                    // Add a new article to a category
                    System.out.println("Available categories:");
                    for (String category : library.artLibrary.keySet()) {
                        System.out.println("- " + category);
                    }

                    System.out.print("\nEnter the category to add the article to: ");
                    String category = scanner.nextLine();

                    if (!library.artLibrary.containsKey(category)) {
                        System.out.println("Category does not exist. Please try again.");
                        break;
                    }

                    System.out.print("Enter the path to the new article file: ");
                    String articlePath = scanner.nextLine();

                    library.artLibrary.get(category).add(articlePath);
                    System.out.println("Article added to category: " + category);
                    break;

                case 3:
                    // Perform analysis on a single article
                    System.out.print("Enter the path to the article for analysis: ");
                    String articleFilePath = scanner.nextLine();

                    try {
                        ArticleAnalysis analysis = processor.analyzeArticle(articleFilePath);
                        System.out.println("\nAnalysis Results:");
                        analysis.displayResults();
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found: " + e.getMessage());
                    }
                    break;

                case 4:
                    // Compare multiple articles
                    System.out.print("Enter the paths to articles separated by commas: ");
                    String[] articlePaths = scanner.nextLine().split(",");

                    try {
                        processor.compareArticles(articlePaths);
                    } catch (FileNotFoundException e) {
                        System.out.println("Error comparing articles: " + e.getMessage());
                    }
                    break;

                case 5:
                    // Exit the program
                    running = false;
                    System.out.println("Thank you for using the Article Processor. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
