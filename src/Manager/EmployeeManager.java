package Manager;

import Models.Assessment;
import Models.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Controller.EmployeeHierarchy;
import Persistence.EmployeeData;

public class EmployeeManager {
    public void run() {
        List<Employee> employees = EmployeeData.getEmployees();
        Employee ceo = null;
        for (Employee employee : employees) {
            if (employee.isCeo()) {
                ceo = employee;
                break;
            }
        }

        // Construir mapas
        Map<Integer, Employee> employeesById = EmployeeHierarchy.buildEmployeeByIdMap(employees);
        Map<Integer, List<Employee>> hierarchy = EmployeeHierarchy.buildHierarchyMap(employees);

        // Si el ceo no existe no crea evaluaciones
        if (ceo != null) {
            List<Assessment> assessmentsToInsert = EmployeeHierarchy.createAssessments(hierarchy, ceo.getId(), employeesById);

            // Imprimir las evaluaciones creadas
            System.out.println("Assessments creados:");
            for (Assessment assessment : assessmentsToInsert) {
                System.out.println(assessment);
            }
            System.out.println("Cantidad de assessments creados: " + assessmentsToInsert.size());
        } else {
            System.out.println("Error while trying to generate assessments, non existing CEO.");
        }
    }
}
