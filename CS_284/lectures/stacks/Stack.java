package lectures.stacks;

public class Stack<E> {

  public static class Node<F> {
    private F data;
    private Node<F> next;

    public Node(F data, Node<F> next) {
      super();
      this.data = data;
      this.next = next;
    }

    public Node(F data) {
      super();
      this.data = data;
      this.next = null;
    }
  }

  //Stack data fields
  private Node<E> top;
  private int size;

  //Stack constructor
  Stack() {
    this.top = null;
    this.size = 0;
  }

  //Stack Methods

  /**
   * @implNote This method add a node to the Stack
   * @return the item being added to the Stack
   */
  public E push(E item) {
    //replace the current top node and set the current top to next
    top = new Node<>(item, top);
    size++;
    return item;
  }

  public E pop() {
    if (top == null) {
      throw new IllegalStateException("pop(): empty stack ");
    }
    E temp = top.data;
    top = top.next;
    size--;
    return temp;
  }

  public E top() {
    if (top == null) {
      throw new IllegalStateException("top(): empty stack");
    }

    return top.data;
  }

  public boolean isEmpty() {
    return top == null;
  }

  public static void main(String[] args) {
    
  }
}
