import main.API.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainPageTest {

    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        mainPage = new MainPage();
    }

    @Test
    public void testWelcome() {
        // uisng RSO here to redirect output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        MainPage.welcome();
        String expectedOutput = "\u001B[32mworker MANAGEMENT APPLICATION\n************************************************\033[0m\n";
        assertEquals(expectedOutput, outputStream.toString());

        // end RSO to stop redirecting output
        System.setOut(System.out);
    }

    @Test
    public void testPrintOption() {
        // Redirect standard output for testing
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        MainPage.printOption();
        String expectedOutput = """
                \nPlease select one of the following menu items:	\u001B[34m
                (1) Capture a new worker
                (2) Search for a worker
                (3) Delete a worker
                (4) Print worker report
                (5) Exit Application\n""";
        assertEquals(expectedOutput, outputStream.toString());

        // Restore standard output
        System.setOut(System.out);
    }

    @Test
    public void testRun() {
      //using mocking tests here are the best way according to the IDE
        InputStream originalIn = System.in;
        ByteArrayInputStream testInput = new ByteArrayInputStream("1\n".getBytes());
        System.setIn(testInput);

        try {
            //need a try call here to simulate the testing or calling of a function inside the jvm. It sadly is the only way the jUnit library like it done in newer versions
            //RSO is usefull but for testing console apps its the only good one
        } finally {
            // Restore original System.in and System.out
            System.setIn(originalIn);
            // Restore standard output if you redirected it
            System.setOut(System.out);
        }
    }

}
