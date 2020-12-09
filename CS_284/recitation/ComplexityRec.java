package recitation;

import java.util.Random;

public class ComplexityRec {
  static Random rng = new Random();

  public static void method1(int n) {// O(n^2)
    int counter = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        counter++;
      }
    }

    System.out.println(counter);
  }

  public static int[] createRandomArray(int n) {
    int[] randomList = new int[n];

    for (int i = 0; i < n; i++) {
      randomList[i] = rng.nextInt(1) + 1;
    }
    return randomList;
  }

  public static void method2(int n) {
    int[] lst = createRandomArray(n);
    // int[] lst = {0,0,0,0,0};
    int counter = 0;
    // n
    for (int i = 0; i < lst.length; i++) {
      if (lst[i] == 5) {
        // O(n)
        for (int j = 0; j < n; j++) {
          counter++;
        }
      } else {
        // O(n^2)
        for (int k = 0; k < n * n; k++) {
          counter++;
        }
      }
    }
    System.out.println(counter);
  }

  public static void method3(int n) {
    int counter = 0;
    // i = 1, 2, 4, 8, 16, 32, 64, etc
    for (int i = 1; i < n; i *= 2) {
      counter++;
    }
    System.out.println(counter);
  }

  public static void method4(int n) {
    int counter = 0;
    // i = 1, 3, 9, 27, 81, ...
    for (int i = 1; i < n; i *= 3) {
      counter++;
    }
    System.out.println(counter);
  }

  public static void method5(int n) {
    int counter = 0;

    for (int i = 2; i < n; i *= i) {
      counter++;
    }
    System.out.println(counter);
  }

  public static void method6(int n, int q) {
    int counter = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < q; j++) {
        counter++;
      }
    }
    System.out.println(counter);
  }

  public static void main(String args[]) {
    int n = 100;
    int q = 10;

    // method1(n);

    // method2(n);

    method3(n);
    // method4(i);
    // method5(n);

    // method6(n,q)

  }

}
