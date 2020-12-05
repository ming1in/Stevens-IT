package lectures.sorting;

public class SelectionSort {

  public static <E extends Comparable<E>> void sort(E[] table) {
    int n = table.length;
    for (int fill = 0; fill < n - 1; fill++) {
      // Invariant: table[0...fill-1] is sorted.
      int posMin = fill;
      for (int next = fill + 1; next < n; next++) {
        // Invariant: table[posMin] is the smallest item in
        // table[fill...next-1].
        if (table[next].compareTo(table[posMin]) < 0) {
          posMin = next;
        }
      }
      // Exchange table[fill] and table[posMin].
      E temp = table[fill];
      table[fill] = table[posMin];
      table[posMin] = temp;
    }
  }
  
  public static void main(String[] args) {
    
    
  }
}
