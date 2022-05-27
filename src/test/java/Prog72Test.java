import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.*;
/**
 * @version (20220526)
 * 
 * (注意) Prog72クラス内に main() が
 * 　　　　宣言されるまで、このテストクラスはエラーが表示される
 **/
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
        String[] prints = bos.toString().split("\r\n|\n");
        try {
            assertEquals("お化けキノコAは逃げ出した！", prints[0],"実行結果の１行目が実行例と異なります!");
            assertEquals("お化けキノコBは自爆した！", prints[1],"実行結果の２行目が実行例と異なります!");
        } catch (AssertionError err) {
            System.setOut(originalOut);
            throw err;
        } 
        // undo the binding in System
        System.setOut(originalOut);
    }
}
