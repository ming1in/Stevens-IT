package stacks;

public class PalindromeChecker {
	// Data fields
	private String inputStr;
	private MyStack<Character> chStack;
	
	// Constructor
	PalindromeChecker(String input) {
		inputStr = input;
		chStack = new MyStack<>();
		fillStack(input);
	}
	
	private void fillStack(String str) {
		// TODO 
		// fill the stack with all the letters from str
		// Hint: s.charAt(i) i-th character in the string s
		for (int i=0; i<str.length();i++) {
			if (str.charAt(i)!=' ') {
			    chStack.push(str.charAt(i));
			}
		}
		
	}
	
	private String reverse() {
		StringBuilder s = new StringBuilder();
		
		while (!chStack.isEmpty()) {
			s.append(chStack.pop());
		}
		return s.toString();
				
	}
	
	public boolean isPalindrome() {
	     return inputStr.replaceAll("\\s", "").equalsIgnoreCase(reverse());
	}
	
	public static void main(String[] args) {
		  PalindromeChecker p1 = new PalindromeChecker("kaya   K");
		  PalindromeChecker p2 = new PalindromeChecker("kayaks");
		  		  
		  System.out.println(p1.isPalindrome());
		  System.out.println(p2.isPalindrome());
		  
			
	}	
}
