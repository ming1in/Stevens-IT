public class SLL<E> {

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
	SLL() {
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

	public SLL<E> clone() {
		SLL<E> result = new SLL<E>();

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

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		Node<E> current = head;

		result.append("[");
		while (current != null) {
			result.append(current.data.toString() + ",");
			current = current.next;
		}
		result.append("]");

		return result.toString();

	}

	private static void incList(Node<Integer> l) {
		while (l != null) {
			l.data = l.data + 1;
			l = l.next;
		}

	}

	public static void incList(SLL<Integer> l) {
		incList(l.head);
	}

	private Node<E> mergeInto(Node<E> l1, Node<E> l2) {
		//TODO

		while (l1 != null || l2 != null) {
			l2.next = l1.next;
			l1.next = l2;
		}

		return null;
	}

	public void mergeInto(SLL<E> l2) {
		head = mergeInto(head, l2.head);
		size = size + Math.min(size, l2.size);

	}

	public static void main(String[] args) {
		SLL<Integer> l0 = new SLL<>();

		l0.add(2);
		l0.add(9);
		l0.add(5);
		l0.add(4);
		incList(l0);
		System.out.println(l0);

		SLL<Integer> l1 = new SLL<>();

		l1.add(2);
		l1.add(9);
		// l.add(5);
		// l.add(4);
		// l.add(7);
		// l.add(14);
		System.out.println(l1);

		SLL<Integer> l2 = new SLL<>();

		l2.add(21);
		l2.add(91);
		l2.add(51);
		l2.add(41);
		// l2.add(71);
		// l2.add(81);
		System.out.println(l2);

		l1.mergeInto(l2);

		System.out.println(l1);

	}

}
