package exams.practice_tests.f18_midterm;

/**
 * classSingleLLP which is just like the class SingleLL except that nodes also
 * have a priority
 */
public class SingleLLP<E> {
  private static class NodeP<E> {
    private E data;
    private int priority;
    private NodeP<E> next;

    public NodeP(E data, int priority, NodeP<E> next) {
      this.data = data;
      this.priority = priority;
      this.next = next;
    }

    public NodeP(E data, int priority) {
      this(data, priority, null);
    }
  }

  private NodeP<E> head;
  private int size;

  public E addFirst(E item, int priority) {
    head = new NodeP<E>(item, priority, head); // create new node and set it next to the current head
    size++;
    return item;
  }

  // add item at the end of the list
  public E add(E item, int priority) {
    if (head == null) { // the list is empty
      head = new NodeP<E>(item, priority);
      size++;
      return item;
    }

    NodeP<E> current = head; // the list is not empty

    while (current.next != null) { // traverse through linked list until you get to the last index
      current = current.next;
    }

    current.next = new NodeP<E>(item, priority); // set the previous last node's next property to the new Node

    size++;
    return item;

  }

  /**
   * @return the priority of the node in the list that has the highest priority
   */
  public int top_priority() {
    if (head == null) {
      throw new IllegalArgumentException("list is empty");
    }

    NodeP<E> current = head;
    NodeP<E> topPriorityNode = head;
    while (current != null) {
      if (current.priority > topPriorityNode.priority) {
        topPriorityNode = current;
      }
      current = current.next;
    }

    return topPriorityNode.priority;
  }

  /**
   * time complexity = O(n)
   * 
   * increments in one unit the priority of every node whose current priority
   * is lower_bound or more
   */
  public void bump_if(int lower_bound) {
    if (head == null) {
      throw new IllegalArgumentException("list is empty");
    }

    NodeP<E> current = head;

    while (current != null) {
      if (current.priority >= lower_bound) {
        current.priority++;
      }
      current = current.next;
    }
  }

  @Override // prints linked list
  public String toString() {
    // StringBuilder is conventional way to build large strings
    StringBuilder result = new StringBuilder();
    NodeP<E> current = head;

    result.append("[");
    while (current != null) {
      result.append(current.data.toString() + ",");
      current = current.next;
    }
    result.append("]");

    return result.toString();
  }

  public static void main(String[] args) {
    SingleLLP<Integer> l = new SingleLLP<Integer>();
    l.addFirst(10, 10);
    l.addFirst(1, 1);
    l.addFirst(3, 3);
    l.addFirst(2, 2);

    System.out.println(l.top_priority());
    l.bump_if(8);
    System.out.println(l.top_priority());

  }
}
