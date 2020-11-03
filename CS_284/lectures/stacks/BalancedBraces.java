package stacks;


public class BalancedBraces {
  private String strInput;
  private MyStack<Character> stack;

  BalancedBraces(String input) {
    strInput = input;
    stack = new MyStack<>();
  }
  
  public boolean isBalanced() {
    return true;
  }
  
  public static void main(String[] args) {
    
  }
}
