package recitation;

import recitation.BTree.Node;

public class BST<E extends Comparable<E>> extends BTree<E> {

  // Constructors
  BST() {
    super();
  }

  BST(E item) {
    super(item);
  }

  BST(E item, BTree<E> left, BTree<E> right) {
    super(item, left, right);
  }

  private boolean find(E item, Node<E> current) {
    if (current == null) {
      return false;
    }
    // Current is non-null
    if (item.compareTo(current.data) == 0) {
      return true;
    }
    if (item.compareTo(current.data) < 0) {
      return find(item, current.left);
    } else {
      return find(item, current.right);
    }

  }

  public boolean find(E item) {
    return find(item, root);
  }

  private Node<E> add(E item, Node<E> current) {
    if (current == null) {
      return new Node<E>(item);
    }
    int i = item.compareTo(current.data);
    if (i == 0) {
      throw new IllegalStateException("add: duplicate item");
    }
    if (i < 0) {
      current.left = add(item, current.left);
    } else {
      current.right = add(item, current.right);
    }
    return current;
  }

  /**
   * Adds a new item to the BST.
   * 
   * @param item Item to be added
   * @throws IllegalStateException if the item is already in the tree
   */
  public void add(E item) {
    root = add(item, root);
    size++;
  }

  private Node<E> remove(E item, Node<E> current) {
    if (current == null) {
      throw new IllegalArgumentException("remove: item not in tree");
    }
    int i = item.compareTo(current.data);
    if (i < 0) {
      current.left = remove(item, current.left);
      return current;
    }
    if (i > 0) {
      current.right = remove(item, current.right);
      return current;
    }
    // item is equal to current.data (i=0)
    // I found the item I have to remove
    if (current.left == null && current.right == null) { // node to remove is a leaf
      return null;
    }
    if (current.left == null) {
      return current.right;
    }
    if (current.right == null) {
      return current.left;
    }
    // the node to be removed has two children
    if (current.left.right == null) {
      current.left.right = current.right;
      return current.left;
    } else {
      current.data = findMax(current.left);
      return current;
    }

  }

  private E findMax(Node<E> parent) {
    if (parent.right == null) {
      throw new IllegalStateException();
    }

    if (parent.right.right == null) {
      E item = parent.right.data;
      parent.right = parent.right.left;
      return item;
    } else {
      return findMax(parent.right);
    }
  }

  private E getMax(Node<E> current) {
    if (current == null) {
      throw new IllegalStateException("Tree is empty");
    }
    if (current.right == null) {
      return current.data;
    } else {
      return getMax(current.right);
    }
  }

  public E getMax() {
    return getMax(root);
  }

  /**
   * Remove item from the BST
   * 
   * @param item
   * @throws IllegalArgumentException if item is not in the tree
   */
  public void remove(E item) {
    root = remove(item, root);
    size--;
  }

  public static void main(String[] args) {
    BST<Integer> t1 = new BST<>(4);

    BST<Integer> t2 = new BST<>(33, new BST<>(24), new BST<>(44));

    BST<Integer> t = new BST<>(12, t1, t2);

    System.out.println(t);
    t.add(55);
    t.add(3);
    t.add(7);
    System.out.println(t);
    t.remove(12);
    System.out.println(t);

  }

}