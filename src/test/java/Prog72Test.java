import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.*;

public class Prog72Test {

    @Test
    public void testHelloWorld()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        Prog72.main(null);

        // assertion
        String[] prints = bos.toString().split("\n");
        assertEquals("お化けキノコAは逃げ出した!", prints[0]);
        assertEquals("お化けキノコBは自爆した!", prints[1]);

        // undo the binding in System
        System.setOut(originalOut);
    }
}
