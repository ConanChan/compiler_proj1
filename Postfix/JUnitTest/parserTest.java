import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.io.*;

public class parserTest {
	@Test
		public void parserTest() throws FileNotFoundException, IOException {
			FileInputStream fis1, fis2;
			String fileName;
			String fileNameBase = "testcases/tc-00";
			String input, ans;
			NoTailRecursionParser parse;

			for (int i = 1; i <= 5; i++) {
				fileName = fileNameBase + i + ".infix";
				fis1 = new FileInputStream(fileName);
				fileName = fileNameBase + i + ".postfix";
				fis2 = new FileInputStream(fileName);

				InputStreamReader isr = new InputStreamReader(fis1);
				BufferedReader br = new BufferedReader(isr);
				input = br.readLine();

				isr = new InputStreamReader(fis2);
				br = new BufferedReader(isr);
				ans = br.readLine();

				parse = new NoTailRecursionParser(input);
				parse.expr();
				String str = parse.getStr();
				//System.out.println("\nans: "+ans);
				//System.out.println("str: "+str);
				assertEquals(ans, str);
			}
		}
}
