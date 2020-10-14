public class SLL<E> {

	public static class Node<F> {
		// data fields
		private F data;
		private Node<F> next;

		Node(F data) {
			this.data=data;
			this.next=null;
		}

		Node(F data, Node<F> next) {
			this.data=data;
			this.next=next;
		}

	}

	// Data fields
	private Node<E> head;
	private int size;

	// Constructor
	SLL() {
		head=null;
		size=0;
	}

	// Methods

    // add item at the end of the list
	public E add(E item) {

		if (head==null) { // the list is empty
			head = new Node<E>(item);
			size++;
			return item;
		} 
		// the list is not empty
		Node<E> current = head;

		while (current.next!=null) {
			current = current.next;
		}
		current.next = new Node<E>(item);
		size++;
		return item;

	}	

    
	public void removeAll(E item) {
		// TODO
		if (head == null) {
			throw new IllegalStateException("list is empty");
		}

		Node<E> current = head;
		while (current.next != null) {
			if (current.next.data == item) {
				current.next = current.next.next;
				size--;
			} else {
				current = current.next;
			}
		}
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

	public static void main(String[] args) {
		   SLL<Integer> l = new SLL<>();

	          l.add(1);
	          l.add(2);
	          l.add(2);
	          l.add(3);
	          l.add(9);
	          l.add(5);
	          l.add(2);
	          l.add(2);
	          l.add(2);
	          System.out.println(l);
	          l.removeAll(2);
	          System.out.println(l);

	          }

}
