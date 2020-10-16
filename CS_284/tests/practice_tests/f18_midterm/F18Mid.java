public class F18Mid {

  /**
   * T(n) =
   * 
   *  n  | T
   * ----------
   *  2  | n-2=0
   *  3  | n-2=1
   *  4  | n-2=2
   *  n  | n-2
   */
  public void problem1a(int n) {
    for (int i = 0; i < n; i++) {
      for (int j = n; j > i + 2; j--) {
        System.out.println(i + " " + j);
      }
    }
  }

  /**
   * T(n) =
   * 
   * 
   */
  public void problem1b(int n) {
    for (int i = 1; i < n; i = i * 2) {
      for (int j = 0; j < n; j++) {
        System.out.println(i + " " + j);
      }
    }
  }

}
