import java.io.*;
import java.util.*;
import java.lang.*;
/**
 *this function is used to transforms an infix expression into a postfix one
 *@author Conan
 *@version 1.0
 */
public class NoTailRecursionParser {
	static int lookahead;
	public String input;
	private long startTime;
	// 存储正在解析的字符的位置
	int position;
	String str;

	public NoTailRecursionParser(String _input) throws IOException {
		input = _input;
		startTime = 0;
		position = 0;
		str = "";
		if (position < input.length()) {
			lookahead = input.charAt(position);
		} else {
			lookahead = 0;
		}
	}

	public String getStr() {
		return str;
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

		if (str == "error") {
			return System.nanoTime() - startTime;
		}

		rest();

		if (str == "error") {
			return System.nanoTime() - startTime;
		}

		return System.nanoTime() - startTime;
	}

	void rest() throws IOException {
		//if (str == "error") return;
		while (true) {
			if (lookahead == '+') {
				goAhead();
				term();
				if (str == "error") {
					break;
				}
				str += '+';
				// System.out.write('+');
				continue;
			} else if (lookahead == '-') {
				goAhead();
				term();
				if (str == "error") {
					break;
				}
				str += '-';
				// System.out.write('-');
				continue;
			} else if (Character.isDigit((char)lookahead)) {
				/**
				 * 错误：运算量间缺少运算符
				 * eg:89+3
				 */
				//errorMsg.append("错误：第" + count + "个字符\n" + "语法错误：运算量间缺少运算符\n");

				str = "error";
				// System.out.print("currentlookahead: " + lookahead);
				break;

				//} else if (Character.isAlphabetic(lookahead)) {
				/**
				 * 错误：非法的输入
				 * eg:1+a-2
				 */
				//errorMsg.append("错误：第" + count + "个字符\n" + "词法错误：非法的输入\n");

				//str = "error";
				// break;
		}
		break;
		}
	}

	void term() throws IOException {
		//if (str == "error") return;
		while (true) {
			if (Character.isDigit((char)lookahead)) {
				// System.out.write((char)lookahead);
				if (str == "error") {
					break;
				}
				str += (char)lookahead;
				goAhead();
				break;
			} else if (lookahead == '\0') {
				str = "error";
				break;
			} else if (lookahead == '\r') {
				str = "error";
				break;
			} else if (lookahead == '+' || lookahead == '-') {
				str = "error";
				break;
			}
		}
	}
}
