package com.periodicTable;

import com.periodicTable.models.Elements;
import com.periodicTable.services.DataLoader;
import com.periodicTable.services.ElementService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {


        List<Elements> elements = new ArrayList<>();

        // Specify the CSV file location
        String fileName = "resource/element.csv";

        // Create and start the DataLoader thread
        DataLoader dataLoader = new DataLoader(elements, fileName);
        dataLoader.start();

        // Wait for the DataLoader thread to finish
        try {
            dataLoader.join();
        } catch (InterruptedException e) {
            System.out.println("Data loading thread interrupted.");
            e.printStackTrace();
        }

        // Check if elements were loaded successfully
        if (elements.isEmpty()) {
            System.out.println("No elements were loaded. Please check the file path or format.");
            return;
        }

        // Create the ElementService for performing operations on the elements
        ElementService elementService = new ElementService(elements);

        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Interactive menu
        while (true) {
            System.out.println("\n--- Periodic Table Explorer ---");
            System.out.println("1. Search elements by name");
            System.out.println("2. Search elements by symbol");
            System.out.println("3. Get elements by group");
            System.out.println("4. Get elements by period");
            System.out.println("5. Get elements by atomic weight (greater than)");
            System.out.println("6. Show all elements");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
                continue;
            }

            switch (choice) {
                case 1: // Search elements by name
                    System.out.print("Enter the name to search: ");
                    String name = scanner.nextLine();
                    List<Elements> byName = elementService.searchByName(name);
                    printResults(byName);
                    break;

                case 2: // Search elements by symbol
                    System.out.print("Enter the symbol to search: ");
                    String symbol = scanner.nextLine();
                    List<Elements> bySymbol = elementService.searchBySymbol(symbol);
                    printResults(bySymbol);
                    break;

                case 3: // Get elements by group
                    System.out.print("Enter the group number: ");
                    try {
                        int group = Integer.parseInt(scanner.nextLine());
                        List<Elements> byGroup = elementService.getByGroup(group);
                        printResults(byGroup);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid group number.");
                    }
                    break;

                case 4: // Get elements by period
                    System.out.print("Enter the period number: ");
                    try {
                        int period = Integer.parseInt(scanner.nextLine());
                        List<Elements> byPeriod = elementService.getByPeriod(period);
                        printResults(byPeriod);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid period number.");
                    }
                    break;

                case 5: // Get elements by atomic weight
                    System.out.print("Enter the minimum atomic weight: ");
                    try {
                        double weight = Double.parseDouble(scanner.nextLine());
                        List<Elements> byWeight = elementService.getByAtomicWeight(weight);
                        printResults(byWeight);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid atomic weight.");
                    }
                    break;

                case 6: // Show all elements
                    printResults(elements);
                    break;

                case 7: // Exit
                    System.out.println("Exiting Periodic Table Explorer. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 7.");
            }
        }
    }

    // Utility method to print the results
    private static void printResults(List<Elements> results) {
        if (results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            results.forEach(System.out::println);
        }

    }
}
