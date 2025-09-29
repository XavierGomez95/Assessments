package Controller;

import Manager.EmployeeManager;
import View.Menu;

public class MenuController {
    private final EmployeeManager manager;
    private final Menu menu;

    public MenuController() {
        this.menu = new Menu();
        this.manager = new EmployeeManager(menu);
    }

    public void start() {
        String option;
        menu.printMenu();
        do {
            option = menu.readOptionMenu("Select an option: ");
            boolean validOption = true;

            switch (option) {
                case "1" -> handleLoadEmployees();
                case "1.1" -> menu.printHierarchyExplanation();
                case "2" -> handleGenerateAssessments();
                case "2.1" -> menu.printAssessmentsExplanation();
                case "3" -> menu.printExit();
                default -> {
                    menu.printInvalidOption();
                    validOption = false;
                }
            }

            if (!option.equals("3") && validOption) {
                menu.printMenu();
            }

        } while (!option.equals("3"));
    }

    private void handleLoadEmployees() {
        while (true) {
            int maxLevels = menu.readMaxLevels("\nEnter the number of hierarchy levels (excluding CEO, between 1 and 12): ");
            if (maxLevels >= 1 && maxLevels <= 12) {
                manager.loadEmployees(maxLevels);
                break;
            }
            menu.printError("Invalid input. Please enter a number between 1 and 12.");
        }
    }

    private void handleGenerateAssessments() {
        manager.generateAssessments();
    }
}
