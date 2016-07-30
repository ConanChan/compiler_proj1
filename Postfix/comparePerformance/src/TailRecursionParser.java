import java.io.*;
import java.util.*;
/**
*this function is used to transforms an infix expression into a postfix one
*@author Conan
*@version 1.0
*/
public class TailRecursionParser {
	static int lookahead;
	public String input;
	private long startTime;

	// 存储正在解析的字符的位置
	public int position;

	public TailRecursionParser(String _input) throws IOException {
		input = _input;
		startTime = 0;
		position = 0;

		if (position < input.length()) {
			lookahead = input.charAt(position);
		} else {
			lookahead = 0;
		}
	}

	public void goAhead() throws IOException {
		if (lookahead != '\0') {
			position += 1;
			if (position >= input.length()) {
				lookahead = 0;
			} else {
				lookahead = input.charAt(position);
			}
		}
	}

	public long expr() throws IOException {
		if (startTime == 0) {
			startTime = System.nanoTime();
		}

		if (lookahead == 0) {
			return System.nanoTime()-startTime;
		}
		
		term();
		rest();

		return System.nanoTime() - startTime;
	}

	void rest() throws IOException {
		if (lookahead == '+') {
			goAhead();
			term();
			// System.out.write('+');
			rest();
		} else if (lookahead == '-') {
			goAhead();
			term();
			// System.out.write('-');
			rest();
		} else {
			// do nothing with the input
		}
	}

	void term() throws IOException {
		if (Character.isDigit((char)lookahead)) {
			// System.out.write((char)lookahead);
			goAhead();
		} else if (lookahead == '\0') {
			System.out.println("this is the end.");
		} else {
			throw new Error("syntax error");
		}
	}
}
