import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import java.io.*;
/**
 * @version (20220526)
 *  注）感嘆符は全角文字とする
 **/
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
        try {
            assertEquals(0, m.hp,"Matango.selfDestruct()のhp設定が不正です!");
            String[] prints = bos.toString().split("\r\n|\n");
            assertEquals("お化けキノコGは自爆した！", prints[0], "Matango.selfDestruct()のprint出力が不正です!");
        } catch (AssertionError err) {
            System.setOut(originalOut);
            throw err;
        } catch (ArrayIndexOutOfBoundsException excpt) {
            System.setOut(originalOut);
            fail("コード中に適切なprint命令がありません!");
        }

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
        try {
            String[] prints = bos.toString().split("\r\n|\n");
            assertEquals("お化けキノコGは逃げ出した！", prints[0], "Matango.run()のprint出力が不正です!");
        } catch (AssertionError err) {
            System.setOut(originalOut);
            throw err;
        } catch (ArrayIndexOutOfBoundsException excpt) {
            System.setOut(originalOut);
            fail("コード中に適切なprint命令がありません!");
        }

        // undo the binding in System
        System.setOut(originalOut);
    }
}
