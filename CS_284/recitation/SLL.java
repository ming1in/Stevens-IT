package recitation;

public class SLL<E> {

  public static class Node<F> {
    // data fields
    private F data;
    private Node<F> next;

    Node(F data) {
      this.data = data;
      this.next = null;
    }

    Node(F data, Node<F> next) {
      this.data = data;
      this.next = next;
    }

  }

  // Data fields
  private Node<E> head;
  private int size;

  // Constructor
  SLL() {
    head = null;
    size = 0;
  }

  // Methods
  public E addFirst(E item) {
    head = new Node<E>(item, head);
    size++;
    return item;
  }

  // add item at the end of the list
  public E add(E item) {

    if (head == null) { // the list is empty
      head = new Node<E>(item);
      size++;
      return item;
    }
    // the list is not empty
    Node<E> current = head;

    while (current.next != null) {
      current = current.next;
    }
    current.next = new Node<E>(item);
    size++;
    return item;

  }

  // add item at given index
  public E add(E item, int index) {
    if (index < 0 || index > size) {
      throw new IllegalArgumentException("Index out of bounds");
    }
    if (head == null) {
      addFirst(item);
      return item;
    }
    // the list is not empty
    Node<E> current = head;
    for (int i = 0; i < index - 1; i++) {
      current = current.next;
    }
    current.next = new Node<E>(item, current.next);
    size++;
    return item;

  }

  /**
   * Removes and returns the first item from the list
   * 
   * @return The element that was removed
   * @throws IllegalStateException is the list is empty
   */
  public E removeFirst() {
    if (head == null) {
      throw new IllegalStateException("removeFirst: list is empty");
    }
    E temp = head.data;
    head = head.next;
    size--;
    return temp;
  }

  /**
   * Removes and returns the last item from the list
   * 
   * @return The element that was removed
   * @throws IllegalStateException is the list is empty
   */
  public E removeLast() {
    if (head == null) { // list is empty
      throw new IllegalStateException("removeLast: list is empty");
    }
    if (head.next == null) { // list is a singleton list
      E temp = head.data;
      head = null;
      size--;
      return temp;
    }
    // list has two or more elements
    Node<E> current = head;

    while (current.next.next != null) {
      current = current.next;
    }
    E temp = current.next.data;
    current.next = null;
    size--;
    return temp;

  }

  /**
   * Removes and returns the item located at index index from the list
   * 
   * @param index Index of the item to be removed
   * @return The element that was removed
   * @throws IllegalArgumentException if the index does not exist
   */
  public E remove(int index) {
    if (index < 0 || index > size) {
      throw new IllegalArgumentException("remove: index out of bounds");
    }
    if (head == null) {
      throw new IllegalStateException("remove: list is empty");
    }
    // list is non-empty and index is in range
    if (index == 0) {
      return removeFirst();
    }
    // list is non-empty, the index is in range and larger than 0

    // TODO

    return null;

  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    Node<E> current = head;

    result.append("[");
    while (current != null) {
      if (current.data != null) {
        result.append(current.data.toString() + ",");
      } else {
        result.append(current.data + ",");
      }
      current = current.next;
    }
    result.append("]");

    return result.toString();

  }

  public SLL<E> clone() {
    SLL<E> l = new SLL<E>();
    Node<E> current = head;
    Node<E> temp = new Node<E>(null);
    Node<E> newCopy = temp;
    while (current != null) {
      // l.append(current);
      temp.next = new Node<E>(current.data);
      current = current.next;
      temp = temp.next;
    }
    l.head = newCopy.next;
    l.size = size;
    return l;
  }

  public boolean mem(E item) {
    boolean found = false;
    Node<E> current = head;

    while (!found && current != null) {
      found = found || current.data.equals(item);
      current = current.next;
    }
    return found;

  }

  public boolean hasDuplicates() {
    // no items or 1 item
    if (head == null || head.next == null) {
      return false;
    }
    // general case - multiple of the same item, more than 2 items
    else {
      Node<E> current = head;
      Node<E> curr2 = current.next;
      while (current.next != null) {
        while (curr2 != null) {
          // compare
          // is duplicate
          if (curr2.data == current.data) {
            return true;
          }
          // is not duplicate
          else {
            curr2 = curr2.next;
          }
        }
        current = current.next;
        curr2 = current.next;
      }
    }
    return false;
  }

  public boolean hasDuplicates2() {
    if (head == null || head.next == null) {
      return false;
    } else {
      Node<E> current = head;
      Node<E> curr2 = current.next;
      while (current.next != null) {
        while (curr2 != null) {
          if (current.data == curr2.data) {
            return true;
          } else {
            curr2 = curr2.next;
          }
        }
        current = current.next;
        curr2 = current.next;
      }
    }
    return false;
  }

  public boolean hasDupesClone() {
    if (head == null || head.next == null) {
      return false;
    }

    SLL<E> newList = this.clone();
    E compa = newList.head.data;

    while (newList.head.next != null) {
      newList.head = newList.head.next;
      if (newList.mem(compa)) {
        return true;
      } else {
        compa = newList.head.data;
      }
    }
    return false;
  }

  public void removeAdjacentDuplicates() {
    if (head == null || head.next == null) {
      return;
    } else {
      Node<E> current = head;
      while (current.next != null) {
        if (current.data == current.next.data) {
          size--;
          current.next = current.next.next;
        } else {
          current = current.next;
        }
      }

    }
  }

  public void removeAdjacentDuplicates2() {
    if (head == null || head.next == null) {
      return;
    }

    Node<E> current = head;
    while (current.next != null) {
      if (current.data == current.next.data) {
        current.next = current.next.next;
        size--;
      } else {
        current = current.next;
      }
    }
  }

  public void reverse() {
    Node<E> current = head;
    SLL<E> newList = new SLL<E>();

    while (current != null) {
      newList.addFirst(current.data);
      current = current.next;
    }

    this.head = newList.head;
  }

  public void reverse2() {
    Node<E> current = head;
    SLL<E> newList = new SLL<E>();

    while (current != null) {
      newList.addFirst(current.data);
      current = current.next;
    }
    this.head = newList.head;
  }

  public void stutterNL(int n) {
    Node<E> current = head;
    E currdata;
    while (current != null) {
      currdata = current.data;
      for (int i = 0; i < n - 1; i++) {
        current.next = new Node<E>(currdata, current.next);
        current = current.next;
        size++;
      }
      current = current.next;
    }
  }

  public void stutter2(int n) {
    Node<E> current = head;
    while (current != null) {
      for (int i = 0; i < n - 1; i++) {
        current.next = new Node<E>(current.data, current.next);
        current = current.next;
        size++;
      }
      current = current.next;
    }
  }

  public void append(SLL<E> anotherList) {

  }

  public void removeNullElements() {

  }

  public static void main(String[] args) {
    SLL<Integer> l = new SLL<Integer>();
    SLL<Integer> l2 = new SLL<Integer>();

    boolean is10 = l.mem(10);

    SLL<Integer> lclone = l.clone();

    l.addFirst(6);
    l.addFirst(5);
    l.addFirst(4);
    l.addFirst(3);
    l.addFirst(2);
    System.out.println(l);

    l2.addFirst(2);
    l2.addFirst(5);
    l2.addFirst(5);
    l2.addFirst(2);
    l2.addFirst(2);

    System.out.println(l2);
    System.out.println(l.hasDuplicates2());
    System.out.println(l2.hasDuplicates2());
    System.out.println(l.hasDupesClone());
    System.out.println(l2.hasDupesClone());

    l2.removeAdjacentDuplicates();
    l.reverse();
    System.out.println(l);

    l.stutter2(3);
    System.out.println(l);
    l.removeAdjacentDuplicates2();
    System.out.println(l);

  }

}
