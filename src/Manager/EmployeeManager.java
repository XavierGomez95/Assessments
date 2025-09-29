package Manager;

import Models.Assessment;
import Models.Employee;

import java.util.List;
import java.util.Map;

import Controller.EmployeeHierarchy;
import Persistence.EmployeeData;
import View.Menu;

public class EmployeeManager {
    private List<Employee> employees;
    private Map<Integer, Employee> employeesById;
    private Map<Integer, List<Employee>> hierarchy;
    private Employee ceo;
    private final Menu menu;

    public EmployeeManager(Menu menu) {
        this.menu = menu;
    }

    public void loadEmployees(int maxLevels) {
        // Employees by 7 levels > lvl_1=3, lvl_2=9 ,lvl_3=27 ,lvl_4=81 ,lvl_5=243 ,lvl_6=729 ,lvl_7=4374
        // Calculation of the total number of assessments > (4374*7) + (729*6) + (243*5) + (81*4) + (27*3) + (9*2) + 3 = 36633
        employees = EmployeeData.getEmployees(maxLevels);
        ceo = null;
        for (Employee employee : employees) {
            if (employee.isCeo()) {
                ceo = employee;
                break;
            }
        }

        // Build maps
        employeesById = EmployeeHierarchy.buildEmployeeByIdMap(employees);
        hierarchy = EmployeeHierarchy.buildHierarchyMap(employees);

        menu.printEnter();
        menu.printSuccess("Employees loaded with " + maxLevels + " levels. Total employees: " + employees.size());
    }

    public void generateAssessments() {
        if (ceo != null && employees != null && !employees.isEmpty()) {
            List<Assessment> assessmentsToInsert = EmployeeHierarchy.createAssessments(hierarchy, ceo.getId(), employeesById);

            menu.printInfo("\nAssessments created:");
            for (Assessment assessment : assessmentsToInsert) {
                menu.printMessage(assessment);
            }
            menu.printEnter();
            menu.printSuccess("Number of assessments created: " + assessmentsToInsert.size());
        } else {
            menu.printError("Error while trying to generate assessments: non existing CEO or employees not loaded.");
        }
    }
}
