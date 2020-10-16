
/**
 * @author Ming Lin(ming1in)
 * 
 * I pledge my honor that I have abided by the Stevens Honor Code.
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IDLListTest {

  @Test
  void testin() {
    IDLList<Integer> one = new IDLList<Integer>();
    assertThrows(IndexOutOfBoundsException.class, () -> one.add(2, 1));

    assertEquals(one.add(0, 1), true);
    assertEquals(one.toString(), "[1,]");

    assertEquals(one.add(0, 4), true);
    assertEquals(one.toString(), "[4,1,]");

    assertEquals(one.add(1, 3), true);
    assertEquals(one.toString(), "[4,3,1,]");

    assertEquals(one.add(3, 6), true);
    assertEquals(one.toString(), "[4,3,1,6,]");

    assertEquals(one.get(0), "4");

    assertEquals(one.append(2), "[4,3,1,6,2,]");
    assertEquals(one.getHead(), "4");
    assertEquals(one.getLast(), "2");
    assertEquals(one.size(), 5);
    assertEquals(one.remove(), "4");
    assertEquals(one.removeLast(), "2");
    assertEquals(one.removeAt(0), "3");
    assertEquals(one.remove(0), true);

    assertThrows(IllegalArgumentException.class, () -> one.add(5, 2));
    assertThrows(IllegalArgumentException.class, () -> one.add(-1, 2));

  }
}