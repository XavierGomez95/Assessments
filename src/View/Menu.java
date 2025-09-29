package View;

import Models.Assessment;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }


    public void printMenu() {
        System.out.println();
        System.out.println("=== MAIN MENU ===");
        System.out.println("1. Generate employee hierarchy");
        System.out.println("\t1.1 More info about hierarchy");
        System.out.println();
        System.out.println("2. Generate performance assessments");
        System.out.println("\t2.1 More info about assessments");
        System.out.println();
        System.out.println("3. Exit");
    }

    public void printAssessmentsExplanation() {
        System.out.println();
        System.out.println("\tAbout Assessments:");
        System.out.println("\t\t- Each employee is evaluated by their direct supervisor.");
        System.out.println("\t\t- Then by the supervisor's supervisor, and so on.");
        System.out.println("\t\t- The process continues until reaching the CEO.");
        System.out.println("\tExample: Eva will be assessed by Iris (her supervisor), then by Joan (Iris's supervisor), and finally by the CEO.");
    }

    public void printHierarchyExplanation() {
        System.out.println();
        System.out.println("\tAbout hierarchy levels:");
        System.out.println("\t\t- Level 0 is the CEO (not counted).");
        System.out.println("\t\t- Each supervisor (except in the last level) has 3 subordinates.");
        System.out.println("\t\t- In the last level, each supervisor has 6 employees");
        System.out.println("\tExample: If you enter 3 levels:");
        System.out.println(ConsoleColors.PURPLE + """
                \t\tCEO: Joan (1)
                \t\t ├── David (2)
                \t\t │    ├── Employee 5
                \t\t │    │     ├── Employee 14 … 19
                \t\t │    ├── Employee 6
                \t\t │    │     ├── Employee 20 … 25
                \t\t │    └── Employee 7
                \t\t │          ├── Employee 26 … 31
                \t\t ├── Artur (3)
                \t\t │    ├── Employee 8
                \t\t │    │     ├── Employee 32 … 37
                \t\t │    ├── Employee 9
                \t\t │    │     ├── Employee 38 … 43
                \t\t │    └── Employee 10
                \t\t │          ├── Employee 44 … 49
                \t\t └── Iris (4)
                \t\t      ├── Employee 11
                \t\t      │     ├── Employee 50 … 55
                \t\t      ├── Employee 12
                \t\t      │     ├── Employee 56 … 61
                \t\t      └── Employee 13
                \t\t            ├── Employee 62 … 67
                """ + ConsoleColors.RESET);

    }

    public String readOptionMenu(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public int readMaxLevels(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            printError("Please enter a valid number.");
            scanner.next();
            System.out.print(message);
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // clean buffer
        return value;
    }

    public void printExit() {
        System.out.println("\nExiting program...");
    }

    public void printInfo(String message) {
        System.out.println(message);
    }

    public void printSuccess(String message) {
        System.out.println(ConsoleColors.GREEN + message + ConsoleColors.RESET);
    }

    public void printInvalidOption() {
        System.out.println(ConsoleColors.YELLOW + "Invalid option." + ConsoleColors.RESET);
    }

    public void printError(String message) {
        System.out.println(ConsoleColors.RED + message + ConsoleColors.RESET);
    }

    public void printEnter() {
        System.out.println();
    }

    public void printMessage(Assessment assessment) { System.out.println(assessment); }
}
