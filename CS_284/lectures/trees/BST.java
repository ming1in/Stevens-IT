package lectures.trees;

// E extends Comparable<E>: means that BST can be any type that supports Comparable
public class BST<E extends Comparable<E>> extends BTree<E> {

  // constructors
  BST() {
    super();
  }

  BST(E item) {
    super(item);
  }
  
  BST(E item, BTree<E> left, BTree<E> right) {
    super(item, left, right);
  }          


  
  /**
   * @param item, element that is being searched for
   * @return true if item exist, false otherwise
   */
  public boolean find(E item) {
    return findUtil(item, this.root);
  }

  private boolean findUtil(E item, Node<E> currentNode) {
    if (currentNode == null) {
      return false;
    }

    if (item.compareTo(currentNode.data) == 0) {
      return true;
    }

    // if item is less than the currentNode, check left subtree
    if (item.compareTo(currentNode.data) < 0) { // a.compareTo(b) returns 0 if a == b || -1 if a < b || 1 if a > b
      return findUtil(item, currentNode.left);
    } else { // if item is greater than the currentNode, check right subtree
      return findUtil(item, currentNode.right);
    }
  }



  /**
   * Adds a new item to the BST
   * @param item Item to be added
   * @throws IllegalStateException if the item is already in the tree
   */
  public void add(E item) {
    this.root = addUtil(item, this.root);
  }
  
  private Node<E> addUtil(E item, Node<E> currentNode) {
    // the spot where the item should be added 
    if (currentNode == null) {
      return new Node<E>(item);
    }

    int compare = item.compareTo(currentNode.data);

    if (compare == 0) { // duplicate item
      throw new IllegalStateException("add: item already exist");
    } else if (compare < 0) {// item less than currentNode, check left side
      currentNode.left = addUtil(item, currentNode.left);
    } else {
      currentNode.right = addUtil(item, currentNode.right);
    }

    return currentNode;
  }



  /**
   * Remove item from the BST
   * @param item Item to be removed 
   * @throws IllegalArgumentException if item does not exist in the BST
   */
  public void remove(E item) {
    this.root = removeUtil(item, this.root);
  }
  
  // private Node<E> removeUtil(E item, Node<E> currentNode) {
  //   if (currentNode == null) {
  //     throw new IllegalArgumentException("remove: item does not exist in BST");
  //   }

  //   int compare = item.compareTo(currentNode.data);

  //   if(compare<0){
  //     currentNode.left = removeUtil(item, currentNode.left);
  //     return currentNode.left;
  //   } else if (compare > 0){
  //     currentNode.right = removeUtil(item, currentNode.right);
  //     return currentNode.right;
  //   }

  //   //in the event that item is found (compare = 1)

  //   if(currentNode.left == null && currentNode.right == null ){//if item is a leaf, simply remove it
  //     return null;
  //   }
  //   if( currentNode.left == null ){ // if node w/ item has only right child, replace node with right child
  //     return currentNode.right;
  //   }
  //   if(currentNode.right == null){ // if node w/ item has only left child, replace node with left child
  //     return currentNode.left;
  //   }

  //   // if node w/ item has two children
  //   if (currentNode.left.right == null) {
  //     currentNode.left.right = currentNode.right;
  //     return currentNode.left;
  //   }
    
  //   //TODO: edge case where node w/ item has two children

  // }
  
  public static void main(String[] args) {
    BST<Integer> t1 = new BST<>(4);
    BST<Integer> t2 = new BST<>(33, new BST<>(24), new BST<>(44));
    BST<Integer> t = new BST<>(12, t1, t2);

    System.out.println(t);
    System.out.println("Find(4): " + t.find(4));
    System.out.println("Find(30): " + t.find(30));

    t.add(32);

    System.out.println(t);

  }
}
