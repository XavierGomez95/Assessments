package Persistence;

import Models.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDataTest {

    @Test
    void testIsCeo() {
        List<Employee> employees = EmployeeData.getEmployees(0);

        // Is CEO
        Employee ceo = employees.get(0);
        String ceoToString = ceo.toString();
        assertTrue(ceoToString.contains("name='Joan'"));
        assertTrue(ceo.isCeo());
    }

    @Test
    void testGetEmployeesLevelOne() {
        List<Employee> employees = EmployeeData.getEmployees(1);

        // 1 CEO + 6 employees
        assertEquals(7, employees.size());

        for (int i = 1; i < employees.size(); i++) {
            assertFalse(employees.get(i).isCeo());
        }
    }

    @Test
    void testGetEmployeesLevelFifteen() {
        List<Employee> employees = EmployeeData.getEmployees(15);

        // CEO (1) + lvl1 (3) + lvl2 (3^2 = 9) + lvl3 (3^3 = 27) + ... + lvl15 (3^11 * 6) = 1.328.602
        assertEquals(1328602, employees.size());

        for (int i = 1; i < employees.size(); i++) {
            assertFalse(employees.get(i).isCeo());
        }
    }

    @Test
    void testEmployeesHaveSupervisorExceptCeo() {
        List<Employee> employees = EmployeeData.getEmployees(2);

        Employee ceo = employees.get(0);
        assertNull(ceo.getSupervisorId());

        for (int i = 1; i < employees.size(); i++) {
            assertNotNull(employees.get(i).getSupervisorId());
        }
    }
}