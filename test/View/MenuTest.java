package View;

import Models.Assessment;
import Models.Employee;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PrintStream originalOut;
    private InputStream originalIn;

    @BeforeEach
    void setUp() {
        originalIn = System.in; // The keyboard read is assigned
        originalOut = System.out;
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn); // The keyboard read is re-assigned to System.in
        System.setOut(originalOut); // Reassign
    }

    @Test
    void testInput() {
        String optionOneMenu = "1\n";

        System.setIn(new ByteArrayInputStream(optionOneMenu.getBytes())); // The test byte stream is assigned
        Scanner scanner = new Scanner(System.in); // The System.in is assigned to scanner (System.in is assigned in the upper line)
        System.out.println(scanner.nextLine());
    }

    @Test
    void testPrintMessage() {
        Menu menu = new Menu();
        System.setOut(new PrintStream(outputStream)); // From here on, System.out no longer writes to the console,
                                                      // but to the buffer (outputStream) in memory.

        Employee ceo = new Employee(1, "Test1", true, null, true);
        Employee employee = new Employee(2, "Test2", false, ceo, false);
        Assessment fakeAssessment = new Assessment(2025, employee, ceo, 100);

        menu.printMessage(fakeAssessment);

        String output = outputStream.toString();
        originalOut.println(output);

        assertTrue(output.contains("Test1"));
        assertTrue(output.contains("Test2"));
    }
}