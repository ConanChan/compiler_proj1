import java.io.*;
import java.util.*;
/**
 *this function is used to transform an infix expression into a postfix one
 *@author Conan
 *@version 1.0
**/
public class Postfix {
	public static void main(String[] args) throws IOException {
		System.out.println("Input an infix expression and output its postfix notation:");
		new NoTailRecursionParser().expr();
		System.out.println("\nEnd of program.");
	}
}
