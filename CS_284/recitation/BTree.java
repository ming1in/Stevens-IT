package recitation;

public class BTree<E> {

  public static class Node<F> {
    // Data fields
    F data;
    Node<F> left;
    Node<F> right;

    // Constructors
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

    public boolean isLeaf() {
      return left == null && right == null;
    }

  }

  // Data fields
  protected Node<E> root;
  protected int size;

  // Constructors
  BTree() {
    root = null;
    size = 0;
  }

  BTree(E item) {
    root = new Node<E>(item);
    size = 1;
  }

  BTree(E item, BTree<E> left, BTree<E> right) {
    root = new Node<E>(item, left.root, right.root);
    size = 1 + left.size + right.size;
  }

  private StringBuilder toString_helper(Node<E> current, int level) {
    StringBuilder r = new StringBuilder();
    for (int i = 0; i < level; i++) {
      r.append("-");
    }
    if (current == null) {
      r.append("null\n");
    } else {
      r.append(current.data.toString() + "\n");
      r.append(toString_helper(current.left, level + 1));
      r.append(toString_helper(current.right, level + 1));
    }
    return r;
  }

  public String toString() {
    return toString_helper(root, 1).toString();
  }

  private int height(Node<E> current) {
    if (current == null)
      return 0;
    if (current.left == null && current.right == null)
      return 1;

    return Math.max(height(current.left), height(current.right)) + 1;
  }

  public int height() {
    return height(root);
  }

  // every node has 0 or 2 children
  private boolean isFull(Node<E> current) {
    if (current == null)
      return false;

    // if(current.left == null && current.right == null)
    if (current.isLeaf())
      return true;

    return isFull(current.left) && isFull(current.right);
  }

  public boolean isFull() {
    return isFull(root);
  }

  // every layer is completely filled
  // tree must have 2^n - 1 nodes, n is height

  public int numNodes(Node<E> current) {
    if (current == null)
      return 0;

    else
      return 1 + numNodes(current.left) + numNodes(current.right);
  }

  public boolean isPerfect() {
    int nodeCount = numNodes(root);
    int expected = (int) (Math.pow(2, this.height()) - 1);

    return nodeCount == expected;
  }

  // every layer is filled except the leaf layer
  // public boolean isComplete()
  // {}

  // binary search tree
  public Boolean isBST(Node<Integer> current) {
    if (current == null) {
      return true;
    }

    if (current.isLeaf()) {
      return true;
    }
    if (current.left == null) {
      return (current.data < minTree(current.right)) && (isBST(current.right));
    }
    if (current.right == null) {
      return (current.data > maxTree(current.left)) && (isBST(current.left));
    }

    return (current.data > maxTree(current.left)) && (current.data < minTree(current.right)) && (isBST(current.left))
        && (isBST(current.right));
  }

  private int maxTree(Node<Integer> current) {
    while (current.right != null) {
      current = current.right;
    }
    return current.data;
  }

  private int minTree(Node<Integer> current) {
    while (current.left != null) {
      current = current.left;
    }
    return current.data;
  }

  public static void main(String[] args) {
    BTree<Integer> t1 = new BTree<>(4);

    BTree<Integer> t2 = new BTree<>(33, new BTree<>(24), new BTree<>(44));

    BTree<Integer> t = new BTree<>(12, t1, t2);

    System.out.println(t);
    System.out.println(t.height());
    System.out.println(t.numNodes(t.root));
    System.out.println(t.isPerfect());
  }

}
