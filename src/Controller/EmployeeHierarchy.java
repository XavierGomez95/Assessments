package Controller;

import Models.Assessment;
import Models.Employee;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeHierarchy {
    // Construye un mapa de empleados por ID
    public static Map<Integer, Employee> buildEmployeeByIdMap(List<Employee> employees) {
        Map<Integer, Employee> employeesById = new HashMap<>();
        for (Employee emp : employees) {
            employeesById.put(emp.getId(), emp);
        }
        return employeesById;
    }

    // Construye un mapa jerárquico de empleados
    public static Map<Integer, List<Employee>> buildHierarchyMap(List<Employee> employees) {
        Map<Integer, List<Employee>> hierarchy = new HashMap<>();
        for (Employee currentEmployee : employees) {
            Integer supervisorId = null;
            if (currentEmployee.isNotCeo())
                supervisorId = currentEmployee.getSupervisorId();
            if (supervisorId != null) {
                if (!hierarchy.containsKey(supervisorId)) {
                    hierarchy.put(currentEmployee.getSupervisorId(), new ArrayList<Employee>());
                }
                hierarchy.get(currentEmployee.getSupervisorId()).add(currentEmployee);
            }
        }
        return hierarchy;
    }


    public static List<Assessment> createAssessments(Map<Integer, List<Employee>> hierarchy, Integer supervisorId, Map<Integer, Employee> employeesById) {
        List<Assessment> assessmentsToInsert = new ArrayList<>(); // Lista para almacenar los assessments

        createAssessmentsRecursive(hierarchy, supervisorId, employeesById, assessmentsToInsert);

        for (Assessment assessment : assessmentsToInsert) {
            Employee employee = assessment.getEmployee(); // Obtener el empleado del assessment
            employee.addAssessment(assessment); // Agregar el assessment al empleado
        }

        return assessmentsToInsert; // Devuelve la lista llena
    }

    // Crea evaluaciones recursivamente
    public static void createAssessmentsRecursive(Map<Integer, List<Employee>> hierarchy, Integer supervisorId, Map<Integer, Employee> employeesById, List<Assessment> assessmentsToInsert) {
        if (!hierarchy.containsKey(supervisorId)) {
            return;
        }

        List<Employee> subordinates = hierarchy.get(supervisorId);
        for (Employee emp : subordinates) {
            createAssessmentsForSupervisors(emp, employeesById, assessmentsToInsert);
            createAssessmentsRecursive(hierarchy, emp.getId(), employeesById, assessmentsToInsert);
        }
    }

    // Crea evaluaciones para los supervisores de un empleado
    private static void createAssessmentsForSupervisors(Employee employee, Map<Integer, Employee> employeesById, List<Assessment> assessmentsToInsert) {
        Integer currentSupervisorId = employee.getSupervisorId();
        do {
            Employee currentSupervisor = employeesById.get(currentSupervisorId);
            if (currentSupervisor == null) {
                break;
            }

            int currentYear = Year.now().getValue();
            assessmentsToInsert.add(new Assessment(
                    currentYear,
                    employee,
                    currentSupervisor,
                    employee.getId()
            ));
            // Comprobar si es CEO
            currentSupervisorId = currentSupervisor.getSupervisorId();
            // Solo funcionará si el CEO tiene el campo supervisor = null, sino = bucle infinito
        } while (currentSupervisorId != null);
    }
}
