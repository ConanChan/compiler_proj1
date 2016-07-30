import java.io.*;
import java.util.*;
/**
*this function is used to compare the performance between NoTailRecursionParser and TailRecursionParser
*in the progress which transforms an infix expression into a postfix one
*@author Conan
*@version 1.0
*/
public class TestPerformance {
	/**
	* 产生形如1+1+1+……长度为n的测试表达式
	*/
	public static String generateExpression(int n) {
		char[] testExpr = new char[n];
		for (int i = 0; i < n; i += 2) {
			testExpr[i] = '1';
		}
		for (int i = 1; i < n; i += 2) {
			testExpr[i] = '+';
		}
		return new String(testExpr);
	}


	public static void main(String[] args) throws IOException {
		ArrayList tailRecur = new ArrayList();
		ArrayList noTailRecur = new ArrayList();
		// tailRecur.clear();
		// noTailRecur.clear();
		
		String str;
		long t_nano;

		System.out.println("要测试的字符串长度如下所示：");
		for (int i = 1; i < 40; i += 2) {
			System.out.println(i);
			str = generateExpression(i);
			t_nano = new TailRecursionParser(str).expr();
			tailRecur.add(t_nano);
			t_nano = new NoTailRecursionParser(str).expr();
			noTailRecur.add(t_nano);
		}
		
		System.out.println("\n" + "使用尾递归 测试对应的字符串 程序执行的时间长度分别为(nanos)");
		for (int i = 0; i < tailRecur.size(); i++) {
			System.out.print((long)tailRecur.get(i)+",");
		}

		System.out.println("\n" + "不使用尾递归 测试对应的字符串 程序执行的时间长度分别为(nanos)");
		for (int i = 0; i < noTailRecur.size(); i++) {
			System.out.print((long)noTailRecur.get(i)+",");
		}
		System.out.println("");
		/*
		str = "1";
		t_nano = new NoTailRecursionParser(str).expr();
		System.out.println(t_nano);

		str = "1+1";
		t_nano = new NoTailRecursionParser(str).expr();
		System.out.println(t_nano);

		str = "1+1+1";
		t_nano = new NoTailRecursionParser(str).expr();
		System.out.println(t_nano);
		*/
	}
}
