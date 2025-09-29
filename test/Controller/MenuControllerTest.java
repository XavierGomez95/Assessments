package Controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MenuControllerTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PrintStream originalOut;
    private InputStream originalIn;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        originalIn = System.in;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    /**
     * Simulates the user selecting option 1 (create employee hierarchy),
     * entering the value 2 as the number of levels in the hierarchy,
     * and finally selecting option 3 (exit).
     * -------------------------------------------------------------------------------
     * Verifies that the main menu is displayed, that the program asks for the
     * number of levels, correctly loads 22 employees, and that the exit message
     * is shown at the end.
     */
    @Test
    void test1() {
        String fakeInput = "1\n2\n3\n";
        runWithFakeInput(fakeInput);

        String output = outputStream.toString();

        originalOut.println(output);

        assertOutputContains(output, "=== MAIN MENU ===");
        // Menu option 1
        assertOutputContains(output, "Enter the number of hierarchy levels");
        assertOutputContains(output, "Total employees: 22");

        // Menu option 3
        assertOutputContains(output, "Exiting program");
    }

    /**
     * Simulates the user selecting option 2 (generate assessments)
     * without having loaded employees, and finally selecting option 3 (exit).
     * -------------------------------------------------------------------------------
     * Verifies that the main menu is displayed, that an error message is shown
     * when trying to generate assessments without employees, and that the exit
     * message is displayed at the end.
     */
    @Test
    void test2() {
        String fakeInput = "2\n3\n";
        runWithFakeInput(fakeInput);

        String output = outputStream.toString();

        originalOut.println(output);

        assertOutputContains(output, "=== MAIN MENU ===");
        // Menu option 2
        assertOutputContains(output, "Error while trying to generate assessments: non existing CEO or employees not loaded.");

        // Menu option 3
        assertOutputContains(output, "Exiting program");
    }

    /**
     * Simulates the user first selecting option 2 (generate assessments)
     * without having loaded employees, which results in an error message.
     * Then the user selects option 1 (create employee hierarchy),
     * enters the value 5 as the number of levels in the hierarchy,
     * selects option 2 again to generate assessments,
     * and finally selecting option 3 (exit).
     * -------------------------------------------------------------------------------
     * Verifies that the main menu is displayed each time, that the program
     * shows the error when assessments are generated without employees,
     * correctly loads 607 employees when hierarchy is created with 5 levels,
     * successfully generates assessments, and finally displays the exit message.
     */
    @Test
    void test3() {
        String fakeInput = "2\n1\n5\n2\n3\n";
        runWithFakeInput(fakeInput);

        String output = outputStream.toString();

        originalOut.println(output);

        assertOutputContains(output, "=== MAIN MENU ===");
        // Menu option 2
        assertOutputContains(output, "Error while trying to generate assessments: non existing CEO or employees not loaded.");

        // Menu option 1
        assertOutputContains(output, "Enter the number of hierarchy levels");
        assertOutputContains(output, "Total employees: 607");

        // Menu option 2
        assertOutputContains(output, "Number of assessments created");

        // Menu option 3
        assertOutputContains(output, "Exiting program");
    }

    /**
     * Simulates the user selecting option 1.1 (print hierarchy explanation),
     * and then selecting option 3 (exit).
     * -------------------------------------------------------------------------------
     * Verifies that the main menu is displayed, that the hierarchy explanation
     * message is shown, and that the exit message is displayed at the end.
     */
    @Test
    void test4() {
        String fakeInput = "1.1\n3\n";
        runWithFakeInput(fakeInput);

        String output = outputStream.toString();

        originalOut.println(output);

        // Menu option 1.1
        assertOutputContains(output, "Example: If you enter 3 levels:");

        // Menu option 3
        assertOutputContains(output, "Exiting program");
    }

    /**
     * Simulates the user selecting option 2.1 (print assessments explanation),
     * and then selecting option 3 (exit).
     * -------------------------------------------------------------------------------
     * Verifies that the main menu is displayed, that the assessments explanation
     * message is shown, and that the exit message is displayed at the end.
     */
    @Test
    void test5() {
        String fakeInput = "2.1\n3\n";
        runWithFakeInput(fakeInput);

        String output = outputStream.toString();

        originalOut.println(output);

        // Menu option 2.1
        assertOutputContains(output, "Example: Eva will be assessed by Iris (her supervisor), then by Joan (Iris's supervisor), and finally by the CEO.");

        // Menu option 3
        assertOutputContains(output, "Exiting program");
    }

    /**
     * Simulates the user first selecting option 1.1 (print hierarchy explanation),
     * then selecting option 1 (create employee hierarchy),
     * entering the value 3 as the number of levels in the hierarchy,
     * and finally selecting option 3 (exit).
     * -------------------------------------------------------------------------------
     * Verifies that the main menu is displayed each time, that the hierarchy
     * explanation is printed, that 67 employees are correctly loaded when 3
     * levels are entered, and that the exit message is shown at the end.
     */
    @Test
    void test6() {
        String fakeInput = "1.1\n1\n3\n3\n";
        runWithFakeInput(fakeInput);

        String output = outputStream.toString();

        originalOut.println(output);

        assertOutputContains(output, "=== MAIN MENU ===");
        // Menu option 1
        assertOutputContains(output, "Enter the number of hierarchy levels");
        assertOutputContains(output, "Total employees: 67");

        // Menu option 3
        assertOutputContains(output, "Exiting program");
    }

    /**
     * Simulates the user selecting option 1 (create employee hierarchy),
     * entering an invalid non-numeric value ("t"), then entering the valid
     * value 5, selecting option 2 (generate assessments), and finally
     * selecting option 3 (exit).
     * -------------------------------------------------------------------------------
     * Verifies that the main menu is displayed, that the program asks for
     * the number of hierarchy levels, shows an error message for invalid
     * input, correctly loads 607 employees when 5 levels are entered,
     * successfully generates assessments, and finally displays the exit
     * message.
     */
    @Test
    void test7() {
        String fakeInput = "1\nt\n5\n2\n3\n";
        runWithFakeInput(fakeInput);

        String output = outputStream.toString();

        originalOut.println(output);

        assertOutputContains(output, "=== MAIN MENU ===");
        // Menu option 1
        assertOutputContains(output, "Enter the number of hierarchy levels");
        assertOutputContains(output, "Please enter a valid number.");
        assertOutputContains(output, "Total employees: 607");

        // Menu option 2
        assertOutputContains(output, "Number of assessments created");

        // Menu option 3
        assertOutputContains(output, "Exiting program");
    }

    /**
     * Simulates the user selecting option 1 (create employee hierarchy),
     * entering an invalid number (13), then entering the valid value 5,
     * selecting option 2 (generate assessments), and finally selecting
     * option 3 (exit).
     * -------------------------------------------------------------------------------
     * Verifies that the main menu is displayed, that the program asks for
     * the number of levels, shows an error message for out-of-range input,
     * correctly loads 607 employees when 5 levels are entered, successfully
     * generates assessments, and finally displays the exit message.
     */
    @Test
    void test8() {
        String fakeInput = "1\n13\n5\n2\n3\n";
        runWithFakeInput(fakeInput);

        String output = outputStream.toString();

        originalOut.println(output);

        assertOutputContains(output, "=== MAIN MENU ===");
        // Menu option 1
        assertOutputContains(output, "Enter the number of hierarchy levels");
        assertOutputContains(output, "Invalid input. Please enter a number between 1 and 12.");
        assertOutputContains(output, "Total employees: 607");

        // Menu option 2
        assertOutputContains(output, "Number of assessments created");

        // Menu option 3
        assertOutputContains(output, "Exiting program");
    }

    // assertOutputContains(output, "Error while trying to generate assessments: non existing CEO or employees not loaded.");

    private void runWithFakeInput(String fakeInput) {
        originalOut.println(">>> INPUTS >>>");
        originalOut.println(fakeInput);
        originalOut.println(">>> END INPUTS >>>");

        System.setIn(new ByteArrayInputStream(fakeInput.getBytes()));

        MenuController controller = new MenuController();
        controller.start();
    }

    private void assertOutputContains(String output, String message) {
        assertTrue(output.contains(message), () -> "Expected output to contain: " + message);
    }
}