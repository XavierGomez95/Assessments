package Persistence;

import Models.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {
    // Método estático para obtener la lista de empleados
    public static List<Employee> getEmployees() {
        boolean notCeo = false;
        boolean isCeo = true, isSupervisor = true;
        int maxLevels = 7; // Sin contar el CEO
        // Empleados por 7 niveles> lvl_1=3, lvl_2=9 ,lvl_3=27 ,lvl_4=81 ,lvl_5=243 ,lvl_6=729 ,lvl_7=4374
        // Calculo del número total de evaluaciones> (4374*7) + (729*6) + (243*5) + (81*4) + (27*3) + (9*2) + 3 = 36633

        // Nivel 0: CEO, este nivel no cuenta
        Employee ceo = new Employee(1, "Joan", isSupervisor, null, isCeo);

        // Lista de empleados
        List<Employee> employees = new ArrayList<>();
        employees.add(ceo);

        int idCounter = 2; // Contador para IDs
        String[] names = generateNames(); // Generar nombres

        // Niveles de empleados
        List<Employee> currentLevel = new ArrayList<>();
        currentLevel.add(ceo); // Comenzar con el CEO

        for (int level = 1; level <= maxLevels; level++) {
            List<Employee> nextLevel = new ArrayList<>();
            for (Employee supervisor : currentLevel) {
                int numberOfSubordinates = (level < maxLevels) ? 3 : 6; // Supervisores: 3, Empleados finales: 6
                for (int i = 0; i < numberOfSubordinates; i++) {
                    boolean isLastLevel = (level == maxLevels);
                    String name = names[(idCounter - 2) % names.length]; // Reciclar nombres si se supera la longitud
                    Employee employee = new Employee(
                            idCounter,
                            name, // Tomar el nombre correspondiente
                            !isLastLevel, // Es supervisor si no es el último nivel
                            supervisor,
                            notCeo // No es CEO
                    );
                    employees.add(employee);
                    nextLevel.add(employee);
                    idCounter++;
                }
            }
            currentLevel = nextLevel; // Avanzar al siguiente nivel
        }

        return employees;
    }

    // Método auxiliar para generar 100 nombres
    private static String[] generateNames() {
        return new String[]{
                "Joan", "David", "Artur", "Iris", "Samaneh", "Marc", "Clara", "Edgar", "Laura", "Paul",
                "Victor", "Sofia", "Eva", "Xavi", "Marta", "Pau", "Júlia", "Arnau", "Núria",
                "Albert", "Carla", "Guillem", "Helena", "Oriol", "Anna", "Raúl", "Elena", "Jaume",
                "Maria", "Toni", "Rosa", "Ferran", "Lídia", "Ramon", "Isabel", "Pere", "Nora",
                "Francesc", "Gemma", "Andreu", "Cristina", "Ignasi", "Clàudia", "Gerard", "Mireia",
                "Jordi", "Adriana", "Pol", "Sandra", "Àlex", "Laia", "Víctor", "Teresa", "Nil",
                "Mònica", "Marcelo", "Silvia", "Lucas", "Bea", "Eric", "Vera", "Pau", "Glòria",
                "Roc", "Ester", "Hugo", "Camila", "Sergi", "Diana", "Miquel", "Nerea", "Mateu",
                "Blanca", "Joel", "Patricia", "Tomàs", "Sara", "Esteve", "Carolina", "Andrés",
                "Laura", "Quim", "Emma", "Eduard", "Irene", "Pablo", "Natàlia", "Carles", "Joana",
                "Simó", "Ingrid", "Manel", "Alba", "Martí", "Cèlia", "Oriol", "Aina", "Rafael"
        };
    }
}
