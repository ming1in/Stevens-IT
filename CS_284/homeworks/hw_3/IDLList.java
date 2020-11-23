package homeworks.hw_3;

/**
 * @author Ming Lin(ming1in)
 * 
 * I pledge my honor that I have abided by the Stevens Honor Code.
 */

import java.util.ArrayList;

public class IDLList<E> { //Indexed Doubly Linked List

  public static class Node<E> {
    // data fields
    E data;
    Node<E> next; // ref to next node
    Node<E> prev; //ref to prev node

    // constructors
    Node(E elem) {
      this.data = elem;
      this.next = null;
      this.prev = null;
    }

    Node(E elem, Node<E> prev, Node<E> next) {
      this.data = elem;
      this.next = next;
      this.prev = prev;
    }
  }
  
  //data fields
  private Node<E> head;
  private Node<E> tail;
  private int size;
  private ArrayList<Node<E>> indices;

  /**
   * method that creates an empty double-linked list
   */
  public IDLList() {
    head = null;
    tail = null;
    size = 0;
    indices = new ArrayList<Node<E>>();
  }

  /**
   * method that adds elem at position index(counting from wherever head is). It uses
   * the index for fast access. It always returns true.
   */
  public boolean add(int index, E elem) {
    if (index < 0 || index > size) { // checks if index is available
      throw new IllegalArgumentException("add: Index out of bounds");
    }

    Node<E> leftNode = indices.get(index - 1);
    Node<E> rightNode = indices.get(index);
    Node<E> newNode = new Node<E>(elem, leftNode, rightNode);

    leftNode.next = newNode;
    rightNode.prev = newNode;
    
    indices.add(index, newNode);
    size++;
    return true;
  }
  
  /**
   * method that adds elem at the head (i.e. it becomes the first element of the
   * list). It always returns true.
   */
  public boolean add(E elem) {
    Node<E> newNode = new Node<E>(elem, null, head);

    //if list not empty
    if (head != null) {
      head.prev = newNode;
    }

    //set head to new Node
    head = newNode;
    indices.add(0, head);
    size++;
    return true;
  }
  
  /**
   * method that adds elem as the new last element of the list (i.e. at the tail). It
   * always returns true.
   */
  public boolean append(E elem) {
    Node<E> newNode = new Node<E>(elem);
    Node<E> currentTail = indices.get(indices.size()-1);

    //is list is empty, just set new Node to head
    if (head == null) {
      head = newNode;
      return true;
    }

    //set next of last Node to new Node, then set prev of new node to old last Node
    currentTail.next = newNode;
    newNode.prev = currentTail;
    currentTail = newNode;
    indices.add(currentTail);
    size++;
    return true;
  }
  
  /**
   * method that returns the object at position index from the head. It uses the
   * index for fast access. Indexing starts from 0, thus get(0)returns the head
   * element of the list.
   */
  public E get (int index){
    return indices.get(index).data;
  }
  
  /**
   * method returns the object at the head.
   */
  public E getHead() {
    return head.data;
  }
  
  /**
   * method that returns the object at the tail.
   */
  public E getLast() {
    return tail.data;
  }
  
  /**
   * method that returns the list size.
   */
  public int size() {
    return size;
  }
  
  /**
   * method that removes and returns the element at the head. Should throw
   * an IllegalStateException if there is no such element.
   */
  public E remove() {
    if (head == null) {
      throw new IllegalStateException("remove: list is empty");
    }

    E temp = head.data;
    head = head.next;
    head.prev = null;
    size--;
    indices.remove(0);
    return temp;
  }
  
  /**
   * method that removes and returns the element at the tail. Should
   * throw an IllegalStateException if there is no such element.
   */
  public E removeLast() {
    if (head == null) {
      throw new IllegalStateException("removeLast: list is empty");
    }

    E removedNode;
    if (head.next == null) { // list has one item
      removedNode = head.data;
      head = null;
    } else {
      Node<E> newTail = indices.get(indices.size() - 2);
      removedNode = newTail.next.data;
      newTail.next.prev = null;
      newTail.next = null;
    }

    indices.remove(indices.size() - 1);
    size--;
    return removedNode;
  }
  
  /**
   * method that removes and returns the element at the index index.Use the index
   * for fast access. Should throw an IllegalStateException if there is no
   * such element.
   */
  public E removeAt(int index) {
    if (index < 0 || index > size) { // checks if index is available
      throw new IllegalArgumentException("removeAt: Index out of bounds");
    }

    Node<E> leftNode = indices.get(index - 1);
    Node<E> selectedNode = indices.get(index);
    Node<E> rightNode = indices.get(index + 1);

    leftNode.next = rightNode;
    rightNode.prev = leftNode;
    selectedNode.next = null;
    selectedNode.prev = null;

    indices.remove(index);
    size--;

    return selectedNode.data;
  }
  
  /**
   * method that removes the first occurrence of elem in the list and returns true.
   * Return false if elem was not in the list.
   */
  public boolean remove(E elem) {
    for (Node<E> node : indices) {
      if (node.data == elem) {
        removeAt(indices.indexOf(node));
        return true;
      }
    }
    return false;
  }
  
  /**
   * method that presents a string representation of the list.
   */
  @Override
  public String toString() {
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
    IDLList<Integer> l = new IDLList<Integer>();

    l.add(1);
    l.add(2);
    l.append(3);
    l.add(0);

    System.out.println(l);
    l.add(2, 2);

    System.out.println(l);
    System.out.println(l.remove(5));
    System.out.println(l);

  }


}