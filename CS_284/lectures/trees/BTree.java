package lectures.trees;

public class BTree<E>{
  public static class Node<F> {
    protected F data;
    protected Node<F> left;
    protected Node<F> right;

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

  protected Node<E> root;
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

  private int heightUtil(Node<E> currentNode) {
    if (currentNode == null) {
      return 0;
    } else {
      return Math.max(heightUtil(currentNode.left), heightUtil(currentNode.right)) + 1;
    }
  }
  /**
   * @return the height of the tree
   */
  public int height() {
    return heightUtil(this.root);
  }
  
  private int nodesUtil(Node<E> currentNode) {
    if(currentNode == null){
      return 0;
    } else {
      return 1 + nodesUtil(currentNode.left) + nodesUtil(currentNode.right);
    }
  }

  /**
   * @return the number of nodes in a tree
   */
  public int nodes() {
    return nodesUtil(this.root);
  }
  
  private boolean isFullUtil(Node<E> currentNode) {
    if (currentNode == null) {
      return true;
    }

    //if currentNode is a leaf, a node with no children
    if(currentNode.left == null && currentNode.right == null){
      return true;
    }

     //check if nodes have either 0 or 2 nodes
      return (currentNode.left != null && currentNode.right != null && isFullUtil(currentNode.left) && isFullUtil(currentNode.right));
    
  }

  /**
   * methods that checks if BT is full, where every node has 0 or 2 children nodes
   */
  public boolean isFull() {
    return isFullUtil(this.root);
  }

  private StringBuilder toStringUtil(Node<E> current, int level) {
    StringBuilder results = new StringBuilder();

    //add hyphens to show level
    for(int i = 0; i < level; i++){
      results.append("--");
    }

    if (current == null) {
      results.append("null\n");
      return results;
    }

    results.append(current.data.toString()+ "\n");
    results.append(toStringUtil(current.left, level + 1));
    results.append(toStringUtil(current.right, level + 1));
    return results;
  }

  @Override
  public String toString() {
    return toStringUtil(root, 0).toString();
  }

  public static void main(String[] args) {
    BTree<Integer> t1 = new BTree<>(4);
    
    BTree<Integer> t2 = new BTree<>(33, new BTree<>(24), new BTree<>(44));
    BTree<Integer> t = new BTree<>(12, t1, t2);

    System.out.println("Heights: " + t.height());
    System.out.println("# of nodes: " + t.nodes());
    System.out.println("Full Tree?: " + t.isFull());

   }
}
