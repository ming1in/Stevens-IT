package lectures.time_complexity;

/**
 * determine the time growth rate of the following functions
 */

public class TimeComplexity {

  /**
   * time complexity: T(n)=(n-1)log₂(n) or O(n*log(n))
   * 
   *   i  |  x
   * -----------
   *   0  | m+1
   *   1  | m+1
   *   2  | m+1
   *  n -1| n(m+1) 
   */
  public static void method1(int n) {
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < n; j *= 2) {
        System.out.println(i + " " + j);
      }
    }
  }


  /*
  time complexity:T(n) = log₂((n-1)!) = O(log(n!))
  
  when i = 1, then time-complexity of inner for-loop is log₂(1) = 0,
  when i = 2, then time-complexity of inner for-loop is log₂(2),
  when i = 3, then time-complexity of inner for-loop is log₂(3), 
  until n-1, then time-complexity of inner for-loop is log₂(n - 1)
   */
  public static void method2(int n) {
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < i; j *= 2) {
        System.out.println(i + " " + j);
      }
    }
  }

  /*
  time complexity: T(n) = n 

   when i = 1, the time complexity of inner for-loop is 1,
   when i = 2, the time complexity of inner for-loop is 1,
   until n-1, the time complexity of inner for-loop is 1
  */
  public static void method3(int n) {
    // for (int i = 1; i < n; i++) {
    //   for (int j = 1; j < n; j *= 2) {
    //     break;
    //   }
    // }
  }

  /**
   * time complexity: T(n) = n or O(n)
   * 
   * every iteration of the for-loop takes 1 unit of time,
   * so the worse case of time complexity is n = x.length 
   * unit of time. This is also know as a linear time algorithm
   */
  public static int method4(int[] x, int target) {
    for (int i = 0; i < x.length; i++) {
      if (x[i] == target) {
        return i;
      }
    }
    return -1;
  }

  /**
   * time complexity: T(n)=n(m+1) or O(n*m)
   * 
   *   i  |  x
   * -----------
   *   0  | m+1
   *   1  | m+1
   *   2  | m+1
   *  n -1| n(m+1) 
   * 
   * time complexity of f() is m
   * 
   * This is an example of a a method with multiple dependents, this 
   * should be reflected in the time complexity polynomial
   */
  public static boolean method5(int[] x, int[] y) {
    for (int i = 0; i < x.length; i++) {
      // if (f(y, x[i]) != -1) {
      //   return false;
      // }
    }
    return true;
  }

  /**
   * time complexity: T(n) = n^2 or O(n^2)
   * 
   *  i  |  x
   * ----------
   *  0  | n
   *  1  | n
   *  2  | n
   * n-1 | n^2
   * 
   * this method returns false if there are repeated elements in x,
   * and has a quadratic growth rate
   */
  public static boolean method6(int[] x) {
    for (int i = 0; i < x.length; i++) {
      for (int j = 0; j < x.length; j++) {
        if (i != j && x[i] == x[j]) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * time complexity: T(n) = (n^2)/2 - n/2 or O(n^2)
   * 
   * used a summation formula to derive the time complexity
   * 
   * table notation of inner for-loop
   *  i  | x
   * ----------
   *  0  | 0
   *  1  | 1
   *  2  | 2
   *  3  | 3
   * n-1 | n - 1
   * 
   */
  public static void method7(int n) {
    for (int i = 0; i < n; i++) {
      for (int j = i; j > 0; j--) {
        System.out.println(i + " " + j);
      }
    }
  }

  /**
   * time complexity: T(n) = 9n or O(n) 
   * 
   * this is a linear operation
   * 
   *  i  | x
   * ----------
   * n-1 | 9
   * n-2 | 9
   * n-3 | 9
   *  0  | 9
   */
  public static void method8(int n) {
    for (int i = n - 1; i >= 0; i--) {
      for (int j = 9; j > 0; j--) {
        System.out.println("hello");
      }
    }
  }

 /**
   * time complexity: T(n) = 
   * 
   * 
   *  i  | x
   * ----------
   *  1  | 0
   *  2  | 2
   *  3  | 0 
   *  4  | 4
   */

  public static void method9(int n) {
    for (int i = 0; i < n; i++) {
      if (i % 2 == 0) {
        for (int j = 0; j < i; j++) {
          System.out.println("Hi");
        }
      }
    }
  }




}
