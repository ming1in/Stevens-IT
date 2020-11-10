package binary_trees;

public class BTree<E>{

	public static class Node<F>{
		// Data fields
		private F data;
		private Node<F> left;
		private Node<F> right;
		
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
		};
		
	
	}
	// Data fields
	private Node<E> root;
	private int size;
	
	// Constructors
	BTree() {
		root=null;
		size=0;
	}
	
	BTree(E item) {
		root = new Node<E>(item);
		size = 1;
	}
	
	BTree(E item, BTree<E> left, BTree<E> right) {
		root = new Node<E>(item,left.root,right.root);
		size = 1 + left.size+ right.size;
	}
	
	
	
	public static void main(String[] args) {
		BTree<Integer> t1 = new BTree<>(4);
		
		BTree<Integer> t2 = new BTree<>(33,new BTree<>(24), new BTree<>(44));
				
		BTree<Integer> t = new BTree<>(12,t1,t2);
		
		System.out.println(t);
		
	}
	
	
	
	
}
