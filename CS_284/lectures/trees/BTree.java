package lectures.trees;

public class BTree<E>{
  public static class Node<F> {
    private F data;
    private Node<F> left;
    private Node<F> right;

    public Node(F data, Node<F> left, Node<F> right) {
      super();
      this.data = data;
      this.left = left;
      this.right = right;
    }

    public Node(F data) {
      super();
      this.data = data;
    }
  }

  private Node<E> root;
  private int size;

  BTree() {
    this.root = null;
    this.size = 0;
  }

  BTree(E item) {
    this.root = new Node<E>(item);
    this.size = 1;
  }

  BTree(E item, BTree<E> left, BTree<E> right) {
    root = new Node<E>(item, left.root, right.root);
    size = left.size + right.size + 1;
  }

  public static void main(String[] args) {
    BTree<Integer> t1 = new BTree<>(4);
    
    BTree<Integer> t2 = new BTree<>(33, new BTree<>(24), new BTree<>(44));
    BTree<Integer> t = new BTree<>(12, t1, t2);

    System.out.println(t);

   }
}
