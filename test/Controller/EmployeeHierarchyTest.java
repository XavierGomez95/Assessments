package Controller;

import Models.Assessment;
import Models.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeHierarchyTest {

    private Employee ceo;
    private Employee manager;
    private Employee dev;

    @BeforeEach
    void setup() {
        // CEO, no tiene supervisor
        ceo = mock(Employee.class);
        when(ceo.getId()).thenReturn(1);
        when(ceo.isCeo()).thenReturn(true);
        when(ceo.getSupervisorId()).thenReturn(null);

        // Manager supervisado por CEO
        manager = mock(Employee.class);
        when(manager.getId()).thenReturn(2);
        when(manager.isNotCeo()).thenReturn(true);
        when(manager.getSupervisorId()).thenReturn(1);

        // Developer supervisado por Manager
        dev = mock(Employee.class);
        when(dev.getId()).thenReturn(3);
        when(dev.isNotCeo()).thenReturn(true);
        when(dev.getSupervisorId()).thenReturn(2);
    }

    @Test
    void buildEmployeeByIdMap() {
        List<Employee> employees = Arrays.asList(ceo, manager, dev);
        Map<Integer, Employee> map = EmployeeHierarchy.buildEmployeeByIdMap(employees);

        assertEquals(3, map.size());
        assertSame(ceo, map.get(1));
        assertSame(manager, map.get(2));
        assertSame(dev, map.get(3));
    }

    @Test
    void buildHierarchyMap() {
        List<Employee> employees = Arrays.asList(ceo, manager, dev);
        Map<Integer, List<Employee>> hierarchy = EmployeeHierarchy.buildHierarchyMap(employees);

        assertEquals(2, hierarchy.size());
        assertTrue(hierarchy.get(1).contains(manager));
        assertTrue(hierarchy.get(2).contains(dev));
    }

    @Test
    void createAssessments() {
        List<Employee> employees = Arrays.asList(ceo, manager, dev);

        Map<Integer, Employee> employeesById = EmployeeHierarchy.buildEmployeeByIdMap(employees);
        Map<Integer, List<Employee>> hierarchy = EmployeeHierarchy.buildHierarchyMap(employees);

        List<Assessment> assessments = EmployeeHierarchy.createAssessments(hierarchy, ceo.getId(), employeesById);

        assertFalse(assessments.isEmpty());

        // El developer es evaluado por manager y CEO
        assertTrue(
                assessments
                        .stream()
                        .anyMatch(
                                a -> a.getEmployee().getId() == 3 && a.getSupervisor().getId() == 2
                        )
        );
        assertTrue(
                assessments
                        .stream()
                        .anyMatch(
                                a -> a.getEmployee().getId() == 3 && a.getSupervisor().getId() == 1
                        )
        );

        // El manager es evaluado por el CEO
        assertTrue(
                assessments
                        .stream()
                        .anyMatch(
                                a -> a.getEmployee().getId() == 2 && a.getSupervisor().getId() == 1
                        )
        );

        // El manager no es evaluado por el developer
        assertFalse(
                assessments
                        .stream()
                        .anyMatch(
                                a -> a.getEmployee().getId() == 2 && a.getSupervisor().getId() == 3
                        )
        );

        // El CEO no es evaluado ni por el developer ni por el manager
        assertFalse(
                assessments
                        .stream()
                        .anyMatch(
                                a -> a.getEmployee().getId() == 1 && a.getSupervisor().getId() == 2
                        )
        );
        assertFalse(
                assessments
                        .stream()
                        .anyMatch(
                                a -> a.getEmployee().getId() == 1 && a.getSupervisor().getId() == 3
                        )
        );

        // Verifica que se llamó addAssessment() en cada empleado
        verify(manager, atLeastOnce()).addAssessment(any(Assessment.class));
        verify(dev, atLeastOnce()).addAssessment(any(Assessment.class));
    }
}