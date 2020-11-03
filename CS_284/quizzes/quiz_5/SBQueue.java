public class SBQueue<E extends Comparable<E>> {


	public static class MyStack<E> {

		public static class Node<F> {
			// Data fields
			private F data;
			private Node<F> next;

			public Node(F data, Node<F> next) {
				super();
				this.data = data;
				this.next = next;
			}

			public Node(F data) {
				super();
				this.data = data;
				this.next = null;
			}

		}
		// Data fields
		private Node<E> top;
		private int size;

		// Constructor
		MyStack() {
			top=null;
			size=0;				
		}

		// Methods

		public E push(E item) {
			top = new Node<>(item,top);
			size++;
			return item;
		}

		public E pop() {
			if (top==null) {
				throw new IllegalStateException("pop: empty stack");
			}
			E temp = top.data;
			top = top.next;
			size--;
			return temp;		
		}

		public E top() {
			if (top==null) {
				throw new IllegalStateException("top: empty stack");
			}
			return top.data;
		}

		public boolean isEmpty() {
			return top==null;
		}

		public String toString() {

			Node<E> current = top;
			StringBuilder r = new StringBuilder();

			r.append("<--");

			while (current != null) {
				r.append(current.data.toString() + "<--");
				current = current.next;
			}

			return r.toString();
		}
	}
	// Data fields
	private MyStack<E> a;
	private MyStack<E> b;

	public SBQueue() {
		this.a = new MyStack<E>();
		this.b = new MyStack<E>();
	}

	public int size() {
		return this.a.size + this.b.size;
	}

	public boolean isEmpty() {
		return a.isEmpty() && b.isEmpty();
	}

	/**
	 * adds element to queue
	 * 
	 * @param item
	 */
	public void offer(E data) {
			//TODO
			
			// Move all elements from a to b
			while (!a.isEmpty()) {
				b.push(a.pop());
				// a.pop();
			}

			// Push item into a
			a.push(data);

			// Push everything back to a
			while (!b.isEmpty()) {
				a.push(b.pop());
				// b.pop();
			}

	}

	/**
	 * @return element removed from queue
	 */
	public E poll() {
		// TODO
		// if first stack is empty
		if (a.isEmpty()) {
			System.out.println("Q is Empty");
			System.exit(0);
		}

		// Return top of a
		return a.pop();
	}

	public String toString() {
		return (a.toString().length() > b.toString().length()) ? a.toString() : b.toString();
	}

	public static void main(String[] args) {

		Queue<Integer> nums = new Queue<>();
		for (int i = 1; i < 10; i++) {
			System.out.println("Offering: " + i);
			nums.offer(i);
		}
		System.out.println(nums);
		System.out.println("Offering: 11");
		nums.offer(11);
		System.out.println(nums);
		System.out.println("Polling: " + nums.poll().toString());
		System.out.println(nums);
		System.out.println("Polling: " + nums.poll().toString());
		System.out.println(nums);
	}

// Expected  output
//
// Offering: 1
// Offering: 2
// Offering: 3
// Offering: 4
// Offering: 5
// Offering: 6
// Offering: 7
// Offering: 8
// Offering: 9
// <--1<--2<--3<--4<--5<--6<--7<--8<--9<--
// Offering: 11
// <--1<--2<--3<--4<--5<--6<--7<--8<--9<--11<--
// Polling: 1
// <--2<--3<--4<--5<--6<--7<--8<--9<--11<--
// Polling: 2
// <--3<--4<--5<--6<--7<--8<--9<--11<--

}
