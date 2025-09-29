package Manager;

import Models.Employee;
import Persistence.EmployeeData;
import View.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.*;

class EmployeeManagerTest {
    private Menu menuMock;
    private EmployeeManager manager;

    @BeforeEach
    void setUp() {
        menuMock = mock(Menu.class);
        manager = new EmployeeManager(menuMock);
    }

    @Test
    void testLoadEmployeesCallsMenuPrints() {
        List<Employee> fakeList = List.of();

        try(MockedStatic<EmployeeData> employeeDataMockedStatic = Mockito.mockStatic(EmployeeData.class)){
            employeeDataMockedStatic.when(() -> EmployeeData.getEmployees(2)).thenReturn(fakeList);
        }

        // Llamada
        manager.loadEmployees(2);

        verify(menuMock).printEnter();
        verify(menuMock).printSuccess(anyString());
    }

    @Test
    void testGenerateAssessmentsWithoutCeoShowsError() {
        manager.generateAssessments();

        verify(menuMock).printError(contains("non existing CEO"));
    }

    @Test
    void testGenerateAssessmentsWithCeoRunsNormally() {
        Employee ceoMock = mock(Employee.class);
        when(ceoMock.isCeo()).thenReturn(true);
        when(ceoMock.getId()).thenReturn(1);

        try (MockedStatic<EmployeeData> employeeDataMockedStatic = Mockito.mockStatic(EmployeeData.class)) {
            employeeDataMockedStatic.when(() -> EmployeeData.getEmployees(1))
                    .thenReturn(List.of(ceoMock));

            manager.loadEmployees(1); // ahora sí carga un CEO real
        }

        manager.generateAssessments();

        verify(menuMock).printInfo(contains("Assessments created"));
        verify(menuMock).printSuccess(contains("Number of assessments"));
    }
}