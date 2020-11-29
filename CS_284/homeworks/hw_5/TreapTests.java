/**
 * @author Ming Lin
 * 
 * I pledge my honor that I have abided by the Stevens Honor Code.
 */

package homeworks.hw_5;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TreapTests {
  void test() {
    Treap<Integer> testTree = new Treap<Integer>();
    assertTrue(testTree.add(4, 19));
    assertTrue(testTree.add(2, 31));
    assertTrue(testTree.add(6, 70));
    assertTrue(testTree.add(1, 84));
    assertTrue(testTree.add(3, 12));
    assertTrue(testTree.add(5, 83));
    assertTrue(testTree.add(7, 26));

    assertFalse(testTree.find(83));
    assertFalse(testTree.find(100));
    assertTrue(testTree.find(1));
    assertTrue(testTree.find(2));

    assertFalse(testTree.delete(83));
    assertFalse(testTree.delete(100));
    assertTrue(testTree.delete(1));
    assertTrue(testTree.delete(2));
  }
}
