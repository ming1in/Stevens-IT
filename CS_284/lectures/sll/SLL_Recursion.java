package lectures.sll;

import lectures.basics.Pair;
public class SLL_Recursion<E> {

	public static class Node<F> {
		// data fields
		private F data;
		private Node<F> next;

		Node(F data) {
			this.data = data;
			this.next = null;
		}

		Node(F data, Node<F> next) {
			this.data = data;
			this.next = next;
		}

	}

	// Data fields
	private Node<E> head;
	private int size;

	// Constructor
	SLL_Recursion() {
		head = null;
		size = 0;
	}

	// Methods
	public E addFirst(E item) {
		head = new Node<E>(item, head);
		size++;
		return item;
	}

	// add item at the end of the list
	public E add(E item) {

		if (head == null) { // the list is empty
			head = new Node<E>(item);
			size++;
			return item;
		}
		// the list is not empty
		Node<E> current = head;

		while (current.next != null) {
			current = current.next;
		}
		current.next = new Node<E>(item);
		size++;
		return item;

	}



	private Node<E> addRecurUtil(E item, Node<E> current) {
		if (current == null) {
			return new Node<E>(item);
		}

		current.next = addRecurUtil(item, current.next); //recur through rest of list
		return current;
	}

	//recursively add item to the list
	public E addRecur(E item) {

		this.head = addRecurUtil(item, this.head);
		size++;
		return item;
}




private Node<E> removeLastRecurUtil(Node<E> current ) {
	if (current.next == null) {
		return null;
	}

	current.next = removeLastRecurUtil(current.next);
	return current;
}
/**
 * remove last element recursively
 */
public void removeLastRecur() {
	if (this.head == null) {
		throw new IllegalStateException("removeLastRecur: List is empty");
	}	
	head = removeLastRecurUtil(this.head);
	size--;
	}

	// add item at given index
	public E add(E item, int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("Index out of bounds");
		}
		if (head == null) {
			addFirst(item);
			return item;
		}
		// the list is not empty
		Node<E> current = head;
		for (int i = 0; i < index - 1; i++) {
			current = current.next;
		}
		current.next = new Node<E>(item, current.next);
		size++;
		return item;

	}

	/**
	 * Removes and returns the first item from the list
	 * 
	 * @return The element that was removed
	 * @throws IllegalStateException is the list is empty
	 */
	public E removeFirst() {
		if (head == null) {
			throw new IllegalStateException("removeFirst: list is empty");
		}
		E temp = head.data;
		head = head.next;
		size--;
		return temp;
	}

	/**
	 * Removes and returns the last item from the list
	 * 
	 * @return The element that was removed
	 * @throws IllegalStateException is the list is empty
	 */
	public E removeLast() {
		if (head == null) { // list is empty
			throw new IllegalStateException("removeLast: list is empty");
		}
		if (head.next == null) { // list is a singleton list
			E temp = head.data;
			head = null;
			size--;
			return temp;
		}
		// list has two or more elements
		Node<E> current = head;

		while (current.next.next != null) {
			current = current.next;
		}
		E temp = current.next.data;
		current.next = null;
		size--;
		return temp;

	}

	/**
	 * Removes and returns the item located at index index from the list
	 * 
	 * @param index Index of the item to be removed
	 * @return The element that was removed
	 * @throws IllegalArgumentException if the index does not exist
	 */
	public E remove(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("remove: index out of bounds");
		}
		if (head == null) {
			throw new IllegalStateException("remove: list is empty");
		}
		// list is non-empty and index is in range
		if (index == 0) {
			return removeFirst();
		}
		// list is non-empty, the index is in range and larger than 0

		Node<E> current = head;
		for (int i = 0; i < index - 1; i++) {
			current = current.next;
		}
		E temp = current.next.data;
		current.next = current.next.next;
		size--;
		return temp;

	}

	public SLL_Recursion<E> clone() {
		SLL_Recursion<E> result = new SLL_Recursion<E>();

		Node<E> current = head;
		Node<E> copy = new Node<E>(null);
		Node<E> newhead = copy;

		while (current != null) {
			copy.next = new Node<E>(current.data);
			current = current.next;
			copy = copy.next;
		}

		result.head = newhead.next;
		result.size = size;
		return result;

	}

	private boolean memRecurUtil(E item, Node<E> current) { //this should be private because class node should not be publicly accessible
		if (current == null) { //base case: reach end of list
			return false;
		}

		if (current.data.equals(item)) { //base case: found duplicate item
			return true;
		} else {
			return memRecurUtil(item, current.next);
		}
	}


	/**
	 * recursively checks if item exist in linked list
	 */
	public boolean memRecur(E item) {
		return memRecurUtil(item, this.head);
	}
	
	/**
	 * checks if item exist in linked list
	 */
	public boolean mem(E item) {
		boolean found = false;
		Node<E> current = head;

		while (!found && current != null) {
			found = found || current.data.equals(item);
			current = current.next;
		}

		return found;

	}

	public boolean mem2(E item) {
		Node<E> current = head;

		while (current != null) {
			if (current.data.equals(item)) {
				return true;
			}
			current = current.next;
		}

		return false;

	}

	// public boolean hasDuplicates() {
	//
	//
	// }
	////
	// public void append(SLL<E> anotherList) {
	//
	//
	// }
	//
	// public void removeNullElements() {
	//
	// }
	//
	// public void reverse() {
	//
	// }
	//
	// public void removeAdjacentDuplicates() {
	//
	// }

	// @Override
	// public String toString() {
	// StringBuilder result = new StringBuilder();
	// Node<E> current=head;
	//
	// result.append("[");
	// while (current!=null) {
	// result.append(current.data.toString()+",");
	// current = current.next;
	// }
	// result.append("]");
	//
	// return result.toString();
	//
	//
	// }

	private StringBuilder toString(Node<E> current) {
		StringBuilder result = new StringBuilder();

		if (current == null) {
			return result;
		}

		result.append(current.data.toString() +','+ toString(current.next));
		return result;
	}

	/**
	 * recursive toString
	 */
	@Override
	public String toString() {
		return "[" + toString(head).toString() + "]";
	}

	private static void incList(Node<Integer> l) {
		// TODO
		if (l == null) {
			return;
		} else {
			l.data++;
			incList(l.next);
		}
	}

	public static void incList(SLL_Recursion<Integer> l) {
		incList(l.head);
	}

	private Node<E> mergeInto(Node<E> l1, Node<E> l2) {

		Node<E> c1 = l1;
		Node<E> c2 = l2;
		Node<E> newHead = l1;

		while (c1 != null && c2 != null) {
			Node<E> temp = c1.next;
			Node<E> temp2 = c2.next;
			c1.next = c2;
			c2.next = temp;
			c1 = temp;
			c2 = temp2;
		}

		return newHead;

	}

	public void mergeLists(SLL_Recursion<E> l2) {
		head = mergeInto(head, l2.head);
		size = size + Math.min(size, l2.size);

	}

	/**
	 * Exercise 7: removeAll.
	 * 
	 * @param item
	 */
	public void removeAll(E item) {
		while (head != null && head.data.equals(item)) {
			head = head.next;
			size--;
		}
		if (head == null) {
			return;
		}

		Node<E> current = head;
		while (current.next != null) {
			if (current.next.data.equals(item)) {
				current.next = current.next.next;
				size--;
			} else {
				current = current.next;
			}
		}

	}

	private int itemCount(E item, Node<E> head) {
		int result = 0;
		Node<E> current = head;
		while (current != null) {
			result = current.data.equals(item) ? result + 1 : result;
			current = current.next;
		}
		return result;

	}

	public Integer itemCount(E item) {
		return itemCount(item, head);
	}

	public SLL_Recursion<Pair<E, Integer>> histogram() {
		SLL_Recursion<Pair<E, Integer>> result = new SLL_Recursion<>();
		SLL_Recursion<E> chk = new SLL_Recursion<E>();
		Node<E> current = head;
		while (current != null) {
			if (!chk.mem(current.data)) {
				chk.add(current.data);
				// result.add(new Pair<E>(current.data, itemCount(current.data)));
			}
			current = current.next;

		}
		return result;

	}

	public static void main(String[] args) {
		SLL_Recursion<Integer> l = new SLL_Recursion<>();

	          for (int i=0; i<10; i++) {
	        	  l.add(i);
	          }
	      
	          System.out.println(l);
	          l.addRecur(33);
	          l.addRecur(74);
	          System.out.println(l);
	          l.removeLastRecur();
	          System.out.println(l);
	          
	          
//	          SLL<Pair<Integer,Integer>> l2 = l.histogram();
//	          System.out.println(l2);
//		
//		SLL<Integer> l1 = new SLL<>();
//		
//		l1.add(2);
//		l1.add(9);
////		l.add(5);
////		l.add(4);
////		l.add(7);
////		l.add(14);
//		System.out.println(l1);
//		
//		SLL<Integer> l2 = new SLL<>();
//		
//		l2.add(21);
//		l2.add(91);
//		l2.add(51);
//		l2.add(41);
////		l2.add(71);
////		l2.add(81);
//		System.out.println(l2);
//		
////		l1.mergeLists(l2);
//		
//		System.out.println(l1);
//
//		
	          }



}
