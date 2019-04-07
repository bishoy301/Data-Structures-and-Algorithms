package com.bishoy.chapter4;

// brackets.java
// stacks used to check matching brackets
// to run this program: C>java bracketsApp
// for I/O
////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class StackX<T> {
	private ArrayList<T> stackArray;
	private int top;

	public StackX(int s) {
		stackArray = new ArrayList<T>();
		top = -1;
	}

	public void push(T j) {
		stackArray.add(++top, j);
	}

	public T pop() {
		return stackArray.get(--top);
	}

	public T peek() {
		return stackArray.get(top);
	}

	public int size() {
		return top + 1;
	}

	public T peekN(int n) {
		return stackArray.get(n);
	}

	public boolean isEmpty() {
		return (top == -1);
	}

	public void displayStack(String s) {
		System.out.print(s);
		System.out.print("Stack (bottom-->top): ");
		for (int j = 0; j < size(); j++) {
			System.out.print(peekN(j));
			System.out.print(' ');
		}
		System.out.println("");
	}
//--------------------------------------------------------------
} // end class StackX

////////////////////////////////////////////////////////////////
class BracketChecker {
	private String input; // input string
//--------------------------------------------------------------

	public BracketChecker(String in) // constructor
	{
		input = in;
	}

//--------------------------------------------------------------
	public void check() {
		int stackSize = input.length(); // get max stack size
		StackX<String> theStack = new StackX<String>(stackSize); // make stack

		for (int j = 0; j < input.length(); j++) // get chars in turn
		{
			char ch = input.charAt(j); // get char
			switch (ch) {
			case '{': // opening symbols
			case '[':
			case '(':
				theStack.push(String.valueOf(ch)); // push them
				break;

			case '}': // closing symbols
			case ']':
			case ')':
				if (!theStack.isEmpty()) // if stack not empty,
				{
					char chx = theStack.pop().charAt(0); // pop and check
					if ((ch == '}' && chx != '{') || (ch == ']' && chx != '[') || (ch == ')' && chx != '('))
						System.out.println("Error: " + ch + " at " + j);
				} else // prematurely empty
					System.out.println("Error: " + ch + " at " + j);
				break;
			default: // no action on other characters
				break;
			} // end switch
		} // end for
			// at this point, all characters have been processed
		if (!theStack.isEmpty())
			System.out.println("Error: missing right delimiter");
	} // end check()
//--------------------------------------------------------------
} // end class BracketChecker
////////////////////////////////////////////////////////////////

class BracketsApp {
	public static void main(String[] args) throws IOException {
		String input;
		while (true) {
			System.out.print("Enter string containing delimiters: ");
			System.out.flush();
			input = getString(); // read a string from kbd
			if (input.equals("")) // quit if [Enter]
				break;
			// make a BracketChecker
			BracketChecker theChecker = new BracketChecker(input);
			theChecker.check(); // check brackets
		} // end while
	} // end main()
//--------------------------------------------------------------

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
//--------------------------------------------------------------
} // end class BracketsApp
////////////////////////////////////////////////////////////////
