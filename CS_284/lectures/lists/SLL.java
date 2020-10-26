package lists;

public class SLL<E> {

  // Inner class
  public static class Node<F> {
    // data fields
    private F data;
    private Node<F> next; // ref to next node

    // constructor
    Node(F data) {
      this.data = data;
      this.next = null;
    }

    // constructor
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

  // Methods - adds item at the beginning of the LL
  public E addFirst(E item) {
    head = new Node<E>(item, head); // create new node and set it next to the current head
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

    Node<E> current = head; // the list is not empty

    while (current.next != null) { // traverse through linked list until you get to the last index
      current = current.next;
    }

    current.next = new Node<E>(item); // set the previous last node's next property to the new Node

    size++;
    return item;

  }

  // add item at given index
  public void add(E item, int index) {
    if (index < 0 || index > size) { // checks if index is available
      throw new IllegalArgumentException("Index out of bounds");
    }

    if (head == null) { // if list is empty just add the item
      addFirst(item);
    }

    // the list is not empty
    Node<E> current = head;
    for (int i = 0; i < index - 1; i++) { // traverse LL until you get to that index
      current = current.next;
    }
    // set Node left of the idx to new item and the next of the new item to the next
    // of left node
    current.next = new Node<E>(item, current.next);
    size++;
  }

  /***
   * Removes and returns the first item from the list
   * 
   * @return The element that was removed
   * @throws IllegalStateException is the list is empty
   * 
   *                               Time complexity = O(1)
   */
  public E removeFirst() {
    if (head == null) {
      throw new IllegalStateException("removeFirst: list is empty");
    }

    E temp = head.data; // store head so we can return after its been deleted
    head = head.next; // set head to next node, removing current head
    size--;
    return temp;
  }

  /***
   * Removes and returns the last item from the list
   * 
   * @return The element that was removed
   * @throws IllegalStateException is the list is empty
   */
  public E removeLast() {
    if (head == null) {
      throw new IllegalStateException("removeFirst: list is empty");
    }

    if (head.next == null) { // list has one item
      E temp = head.data;
      head = null;
      size--;
      return temp;
    }

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
    if (index < 0 || index > size) { // checks if index is available
      throw new IllegalArgumentException("Index out of bounds");
    }

    if (head == null) {
      throw new IllegalStateException("remove: list is empty");
    }
    // list is non-empty and index is in range
    if (index == 0) {
      return removeFirst();
    }
    // list is non-empty and index is in range and larger than 0
    Node<E> current = head;
    for (int i = 0; i < index - 1; i++) { // traverse LL until you reach node before index
      current = current.next;
    }
    E temp = current.next.data; // store removed node data to return later
    current.next = current.next.next; // set the next in the node before index to node after index
    size--;
    return temp;
  }

  /**
   * Time Complexity = O(n), linear time operation
   * 
   * creates a clone of a linked list
   */
  public SLL<E> clone() {
    SLL<E> result = new SLL<E>();

    Node<E> current = head;
    Node<E> copy = new Node<E>(current.data);
    Node<E> newhead = copy;

    while (current != null) {
      copy.next = new Node<E>(current.data);
      current = current.next;
      copy = copy.next;
    }

    result.head = newhead.next;
    result.size = size;
    return result;
  }

  /**
   * checks if item exist in linked list
   */
  public boolean mem(E item) {
    boolean found = false;
    Node<E> current = head;

    while (!found && current != null) {
      found = found || current.data.equals(item);
      current = current.next;
    }

    return found;
  }

  // public boolean hasDuplicates() {

  // }

  // public void append(SLL<E> anotherList) {

  // }

  // public void removeNullElements() {

  // }

  // public void reverse() {

  // }
  
  // public void removeAdjacentDuplicates() {

  // }
  
  @Override // prints linked list
  public String toString() {
    // StringBuilder is conventional way to build large strings
    StringBuilder result = new StringBuilder();
    Node<E> current = head;

    result.append("[");
    while (current != null) {
      result.append(current.data.toString() + ",");
      current = current.next;
    }
    result.append("]");

    return result.toString();
  }

  public static void main(String[] args) {
    SLL<String> l = new SLL<String>(); // creates SLL of strings

    l.addFirst("hello");
    l.addFirst("hey");
    l.addFirst("howdy");
    l.add("bye");
    l.add("cya", 2);

    System.out.println(l);

    l.removeFirst();
    System.out.println(l);

  }
}