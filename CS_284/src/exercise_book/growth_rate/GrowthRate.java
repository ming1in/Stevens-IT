package src.exercise_book.growth_rate;

public class GrowthRate {


 /**
  * time complexity: T(n) = n
  * 
  *  i  | T
  * ----------
  *  0  | 0
  *  1  | 1
  *  2  | 2 
  *  4  | 4
  *  n  | n
  */
 public void exercise1(int n) {
   for (int i = 0; i < n; i++) {
     for (int j = i; j > 0; j--) {
       System.out.println(i + " " + j);
     }
   }
 }
  
 public void exercise2(int n) {
   
 }
  
}
