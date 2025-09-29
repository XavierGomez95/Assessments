package Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssessmentTest {

    @Test
    void getEmployee() {
        Employee ceo = new Employee(1, "Xavier", true, null, true);
        Employee employee = new Employee(2, "Izan", false, ceo, false);

        Assessment assessment = new Assessment(2025, employee, ceo, 100);

        // Comprobamos que getEmployee devuelve el mismo objeto que pasamos en el constructor
        assertEquals(employee, assessment.getEmployee());
    }

    @Test
    void testToString() {
        Employee ceo = new Employee(1, "Xavier", true, null, true);
        Employee employee = new Employee(2, "Izan", false, ceo, false);

        Assessment assessment = new Assessment(2025, employee, ceo, 100);
        String assessmentToString = assessment.toString();

        // Verificamos que contiene información relevante
        assertTrue(assessmentToString.contains("Employee ref"));
        assertTrue(assessmentToString.contains("name='Izan'"));
        assertTrue(assessmentToString.contains("name='Xavier'"));
        assertTrue(assessmentToString.contains("year=2025"));
        assertTrue(assessmentToString.contains("id=100"));
    }
}