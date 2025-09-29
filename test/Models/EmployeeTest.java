package Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testEmployeeCreation() {
        Employee emp = new Employee(1, "Xavier", true, null, true);

        assertEquals(1, emp.getId());
    }

    @Test
    void isNotCeo() {
        Employee ceo = new Employee(1, "Xavier", true, null, true);
        Employee emp = new Employee(2, "Izan", false, ceo, false);

        assertFalse(ceo.isNotCeo());
        assertTrue(emp.isNotCeo());
    }

    @Test
    void getId() {
        Employee ceo = new Employee(1, "Xavier", true, null, true);

        assertEquals(1, ceo.getId());
    }

    @Test
    void getSupervisorId() {
        Employee ceo = new Employee(1, "Xavier", true, null, true);
        Employee emp = new Employee(2, "Izan", false, ceo, false);

        assertEquals(1, emp.getSupervisorId());
        assertNull(ceo.getSupervisorId());
    }

    @Test
    void addAssessment() {

    }

    @Test
    void testToString() {
        Employee ceo = new Employee(1, "Xavier", true, null, true);
        Employee employee = new Employee(2, "Izan", false, ceo, false);
        String employeeToString = employee.toString();

        assertTrue(employeeToString.contains("id='2'"));
        assertTrue(employeeToString.contains("name='Izan'"));
        assertTrue(employeeToString.contains("isSupervisor=false"));
        assertTrue(employeeToString.contains("id='1'")); // Testing if the ceo (supervisor) id exists
    }

    @Test
    void isCeo() {
        Employee ceo = new Employee(1, "Xavier", true, null, true);

        assertTrue(ceo.isCeo());
    }
}