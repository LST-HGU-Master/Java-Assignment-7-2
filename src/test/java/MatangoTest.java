import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
/**
 * @version (20220526) 注）感嘆符は全角文字とする
 * @version (20261021) revised for lifecycle management, explicit trailing newline validation, and index safety
 **/
public class MatangoTest {
    InputStream originalIn;
    PrintStream originalOut;
    ByteArrayOutputStream bos;
    StandardInputStream in;

    @BeforeEach
    void before() {
        // back up binding
        originalIn  = System.in;
        originalOut = System.out;
        // modify binding
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        in = new StandardInputStream();
        System.setIn(in);
    }

    @AfterEach
    void after() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    
    @Test
    public void testSelfDestruct()
    {
        Matango m = new Matango();
        m.suffix = 'G';
        m.hp = 90;
        m.selfDestruct();

        // 1. HPの変化検証 (0 に変更されているか)
        assertEquals(0, m.hp, "Matango.selfDestruct() 実行後の hp が 0 に設定されていません!");

        // 2. 末尾改行のチェック
        String rawOutput = bos.toString().replace("\r\n", "\n");
        assertTrue(rawOutput.endsWith("\n"), 
            "Matango.selfDestruct() の出力末尾に改行がありません! print ではなく println を使用しているか確認してください。"
        );

        // 3. 出力メッセージの検証
        String[] prints = rawOutput.split("\n");
        assertTrue(prints.length >= 1, "Matango.selfDestruct() の出力結果が確認できません!");
        assertEquals("お化けキノコGは自爆した！", prints[0], "Matango.selfDestruct() の出力メッセージが指定と異なります!");
    }

    @Test
    public void testRun()
    {
        Matango m = new Matango();
        m.suffix = 'G';
        m.hp = 90;
        m.run();

        // 1. 末尾改行のチェック
        String rawOutput = bos.toString().replace("\r\n", "\n");
        assertTrue(rawOutput.endsWith("\n"), 
            "Matango.run() の出力末尾に改行がありません! print ではなく println を使用しているか確認してください。"
        );

        // 2. 出力メッセージの検証
        String[] prints = rawOutput.split("\n");
        assertTrue(prints.length >= 1, "Matango.run() の出力結果が確認できません!");
        assertEquals("お化けキノコGは逃げ出した！", prints[0], "Matango.run() の出力メッセージが指定と異なります!");
    }
}
