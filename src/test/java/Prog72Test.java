import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
/**
 * @version (20220526) 注）感嘆符は全角文字とする
 * @version (20261021) revised for method renaming, lifecycle management, explicit trailing newline validation, and index safety
 * 
 * (注意) Prog72クラス内に main() が
 * 　　　　宣言されるまで、このテストクラスはエラーが表示される
 **/
public class Prog72Test {
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
    public void testMain()
    {
        Prog72.main(null);

        String rawOutput = bos.toString().replace("\r\n", "\n");

        // 1. 末尾改行のチェック 
        assertTrue(rawOutput.endsWith("\n"), 
            "Prog72.main() の出力末尾に改行がありません! 各行の出力直後に改行（println）を入れているか確認してください。"
        );

        // 2. 行数チェック
        String[] prints = rawOutput.split("\n");
        assertTrue(prints.length >= 2, 
            "Prog72.main() の実行結果が2行分確認できません! メッセージ出力の直後に改行（println）を入れているか確認してください。"
        );

        // 3. 各行の出力メッセージ検証
        assertEquals("お化けキノコAは逃げ出した！", prints[0].trim(), "実行結果の1行目が実行例と異なります!");
        assertEquals("お化けキノコBは自爆した！", prints[1].trim(), "実行結果の2行目が実行例と異なります!");
    }
}
