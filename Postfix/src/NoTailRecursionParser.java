import java.io.*;
/**
 *this function is used to transform an infix expression into a postfix one
 *@author Conan
 *@version 1.0
**/
class NoTailRecursionParser {
	/** 
	* 向前看符号，用来存储每次读入的词法单元
	*/
	static int lookahead;

    /**
    * 记录当前lookahead的位置
    */
	int count;

	/**
	* 记录读取到的第一个字符
	*/
	int firstChar;

	/**
    * 用来存储出错信息
    */
	StringBuffer errorMsg;

	/**
	* 构造函数，用来进行初始化
	*/
	public NoTailRecursionParser() throws IOException {
		// 在构造函数里初始化为0
		count = 0;
		// 调用goAhead函数来初始化lookahead的值
		goAhead();
		// 初始化用来存储错误信息的StringBuffer对象
		errorMsg = new StringBuffer();
	}

	/*
	public int getFirstChar() {
		firstChar = System.in.read();
		return firstChar;
	} 
	*/
    
    /**
    * 继续前进，读取下一个字符
    */
	public void goAhead() throws IOException {
		lookahead = System.in.read();
	    /** 
	    * 当前正在被处理的字符的位置加1
	    */
		count++;

        /**
        * 获取第一个字符，为了下面区分错误类型
        */
        if (count == 1) {
        	firstChar = lookahead;
        }

        /**
        * 忽略空格处理
        */
		if (lookahead == ' ') {
			lookahead = System.in.read();
			count++;
		}
	}

	/**
	* 处理输入字符串的函数
	*/ 
	void expr() throws IOException {
		term();
		rest();
		if (errorMsg.length() != 0) {
			System.out.println("\n" + errorMsg);
		}
	}

	/**
	* 处理输入的运算符，当遇到错误时，捕获并继续读取下一位，直至可以处理为止
	*/
	void rest() throws IOException {
		while (true) {
			if (lookahead == '+') {
				match('+');
				term();
				System.out.write('+');
				continue;
			} else if (lookahead == '-') {
				match('-');
				term();
				System.out.write('-');
				continue;
			} else if (Character.isDigit((char)lookahead)) {
				/**
				* 错误：运算量间缺少运算符
				* eg:89+3
				*/
				errorMsg.append("错误：第" + count + "个字符\n" + "语法错误：运算量间缺少运算符\n");
                System.out.print((char)lookahead + " <!运算量间缺少运算符> ");
                goAhead();
                continue;
            } else if (Character.isAlphabetic(lookahead)) {
            	/**
            	* 错误：非法的输入
            	* eg:1+a-2
            	*/
            	errorMsg.append("错误：第" + count + "个字符\n" + "词法错误：非法的输入\n");
            	System.out.print(" <!非法的运算量> ");
            	goAhead();
            	continue;
			} else {
				// do nothing with the input
			}
			break;
		}
	}

	/**
	* 处理输入的操作数，当遇到错误时，捕获并继续读取下一位，直至可以处理为止
	*/
	void term() throws IOException {
		while (true) {
			if (Character.isDigit((char)lookahead)) {
				System.out.write((char)lookahead);
				match(lookahead);
				break;
			} else if (lookahead == '\r') {
				/**
				* 错误：运算符缺失右运算分量
				* eg:9+
				*/
				errorMsg.append("错误：第" + count + "个字符\n" + "语法错误：缺少右运算分量\n");
            	System.out.print(" <!运算符缺少右运算分量> ");
            	goAhead();
            	break;
        	} else if (lookahead == '+' || lookahead == '-') {
        		/**
        		* 错误：运算符缺少左运算分量
        		* eg:+9
        		* 此处不需要break，因为还需要再继续调用term()函数
        		*/
        		errorMsg.append("错误：第" + count + "个字符\n" + "语法错误：缺少左运算分量\n");
        		System.out.print(" <!运算符缺少左运算分量> ");
        		goAhead();
        		// System.out.print("lookahead:" + (char)lookahead+"\n");
			} else if (count == 1) {
        		/**
        		* 错误：表达式首项为非法的运算量
        		* eg:a+1+2
        		*/
        		errorMsg.append("错误：第" + count + "个字符\n" + "词法错误：表达式首项应为数字\n");
            	System.out.print(" <!非法的运算量> ");
            	goAhead();
            	break;
        	} else {
        		/**
        		* if ((char)lookahead == '\r')  
        		*	 System.out.println("true"); 
        		* else 
        		* 	 System.out.println("false");
            	*
        		* System.out.print((char)lookahead);
        		* System.out.print(lookahead);
        		*/
        		errorMsg.append("错误：第" + count + "个字符\n" + "词法错误：非法的输入\n");
            	System.out.print(" <!非法的运算量> ");
            	goAhead();
            	break;
        	}
		}
	}

	/**
	* 将它的参数t和向前看符号比较,如果匹配就前进到下一个输入终结符号
	* 改变了全局变量lookahead的值
	* @param t 当前正在读取的字符
	* @throws IOException 当出现异常时抛出
	*/
	void match(int t) throws IOException {
		if (lookahead == t) {
			goAhead();
		} else {
			throw new Error("syntax error");
		}
	}
}
