package quizzes.quiz_8;

public class BTree<E> {

	public static class Node<F>{
		// Data fields
		protected F data;
		protected Node<F> left;
		protected Node<F> right;
		
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
	protected Node<E> root;
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
	
	private StringBuilder toString(Node<E> current, int level) {
		 StringBuilder r = new StringBuilder();
		 
		 for (int i=0; i<level; i++) {
			 r.append("--");
		 }
		 if (current==null) {
			 r.append("null\n");
			 return r;
		 }
		 r.append(current.data.toString()+"\n");
		 r.append(toString(current.left,level+1));
		 r.append(toString(current.right,level+1));
		 return r;
		
	}
	public String toString() {
		return toString(root,0).toString();
		
	}
	
	private int height(Node<E> current) {
		return current==null ? 0 : 1+Math.max(height(current.left), height(current.right));
	}
	
	public int height() {
		return height(root);
	}
	
	private int nodes(Node<E> current) {
		return current==null ? 0 : 1 + nodes(current.left) + nodes(current.right);
	}
	
	public int nodes() {
		return nodes(root);
	}
	
	private boolean isFull(Node<E> current) {
		if (current==null ||  (current.left==null && current.right==null)) {
			return true;
		}
		return current.left!=null && current.right!=null && isFull(current.left) && isFull(current.right); 
	}
	
	private boolean isFull2(Node<E> current) {
		return (current==null ||  (current.left==null && current.right==null)) || (current.left!=null && current.right!=null && isFull2(current.left) && isFull2(current.right)); 
	}
	
	public boolean isFull() {
		return isFull2(root);
	}

	public void pruneHelper(int pruneLevel, int currentLevel, Node<E> currentNode) {
		if (currentLevel >= pruneLevel) {
			currentNode.data = null;
			currentNode.right = null;
			currentNode.left = null;
		}

		if (currentNode.left != null) {
			pruneHelper(pruneLevel, currentLevel + 1, currentNode.left);
		}

		if (currentNode.right != null) {
			pruneHelper(pruneLevel, currentLevel + 1, currentNode.right);
		}
	}

	public void prune(int level) {
		if (level < 0) {
			throw new IllegalArgumentException("prune(): level can not be negative");
		}

		pruneHelper(level, 0, root);
	}

	public boolean isomorphicHelper(Node<E> n1, Node<E> n2) {
		if (n1 == null && n2 == null) {
			return true;
		}
		if (n1 == null || n2 == null) {
			return false;
		}
		if (n1.data != n2.data) {
			return false;
		}

		return (isomorphicHelper(n1.left, n2.left) && isomorphicHelper(n1.right, n2.right))
				|| (isomorphicHelper(n1.left, n2.right) && isomorphicHelper(n1.right, n2.left));
	}

	public boolean isomorphic(BTree<E> t2) {
		return isomorphicHelper(root, t2.root);
	}
	
	public static void main(String[] args) {
		BTree<Integer> t1 = new BTree<>(4);
		
		BTree<Integer> t2 = new BTree<>(33,new BTree<>(24), new BTree<>(44));
				
		BTree<Integer> t = new BTree<>(12, t1, t2);
		// BTree<Integer> t4 = new BTree<>(12, t2, t1);

		t.prune(2);
				
		System.out.println(t);
		System.out.println("Height: "+t.height());
		System.out.println("Nodes: "+t.nodes());
		System.out.println("IsFull: "+t.isFull());
		
	}
	
	
	
	
}
