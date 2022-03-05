import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.*;

public class MatangoTest {
    @Test
    public void testSelfDestruct()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // action
        Matango m = new Matango();
        m.suffix = 'G';
        m.hp = 90;
        m.selfDestruct();

        // assertion
        assertEquals(0, m.hp);
        assertEquals("お化けキノコGは自爆した!\n", bos.toString());

        // undo the binding in System
        System.setOut(originalOut);

    }

    @Test
    public void testRun()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // action
        Matango m = new Matango();
        m.suffix = 'G';
        m.hp = 90;
        m.run();

        // assertion
        assertEquals("お化けキノコGは逃げ出した!\n", bos.toString());

        // undo the binding in System
        System.setOut(originalOut);
    }


}
