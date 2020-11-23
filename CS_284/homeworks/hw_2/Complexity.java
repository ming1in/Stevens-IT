package homeworks.hw_2;

public class Complexity {

  //time complexity: O(n^2)
  public static void method1(int n) {
    int counter = 0;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.println("Operations: " + counter);
        counter++;
      }
    }
  }
  
  // time complexity: O(n^3)
  public static void method2(int n) {
    int counter = 0;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < n; k++) {
          System.out.println("Operations: " + counter);
          counter++;
        }
      }
    }
  }

  // time complexity: O(log(n))
  public static void method3(int n) {
    int counter = 0;

    for (int i = 1; i <= n; i *= 2) {
      System.out.println("Operations: " + counter);
      counter++;
    }
  }

  // time complexity: O(n * log(n))
  public static void method4(int n) {
    int counter = 0;
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < n; j *= 2) {
        System.out.println("Operations: " + counter);
        counter++;
      }
    }
  }

  // time complexity: O(log(log(n)))
  public static void method5(int n) {
    int counter = 0;

    for (int i = 2; i < n; i *= 2) {
      System.out.println("Operation " + counter);
      counter++;
    }
  }

  // time complexity: O(2^n), this algorithm should return ints in a fibonacci sequence
  public static int method6(int n) {
    if (n <= 1) {
      return n;
    } else {
      return method6(n - 1) + method6(n - 2);
    }
  }

  public static void main(String[] args) {
    method5(16);
  }
}
