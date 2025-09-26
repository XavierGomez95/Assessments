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
        int maxLevels = menu.readMaxLevels("\nEnter the number of levels (excluding CEO): ");
        manager.loadEmployees(maxLevels);
    }

    private void handleGenerateAssessments() {
        manager.generateAssessments();
    }
}
