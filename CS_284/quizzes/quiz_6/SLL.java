package quizzes.quiz_6;

// import basics.Pair;

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
	public E addFirst(E item) {
		head = new Node<E>(item,head);
		size++;
		return item;
	}

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
	
	private Node<E> addR(E item, Node<E> current) {
		if (current==null) {
			return new Node<E>(item);
		}
		current.next = addR(item,current.next);
		return current;
		
	}
	
	public E addR(E item) {

		head = addR(item,head);
		size++;
		return item;

	}	
	
	
	private Node<E> removeLastR(Node<E> current) {
		if (current.next==null) {   
			return null;
		} else {
			current.next = removeLastR(current.next);
			return current;		
		}
	}
	
	public void removeLastR() {
		if (head==null) {
			throw new IllegalStateException("removeLastR: list is empty");
		}
		head = removeLastR(head);
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

	public void addR(E item, int index, Node<E> current) {

		if (index == 1 ) {
			current.next = new Node<E>(item, current.next);
			size++;
		} else {
			addR(item, index - 1, current.next);
		}
		
	}




	//-----------------------------------------------------------------------

	//TODO

	//Ming's Response
	public void addRecur(E item, int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("Index out of bounds");
		}
		if (head == null) {
			addFirst(item);
		}

		// the list is not empty
		addR(item, index, head);
	}

	//Bonelli's Response

	private Node<E> addRecurBonUtil(E item, int index, Node<E> current ){
		if (index == 0) { // if we reach the given index 
			return new Node<E>(item, current); //set the new Node and it next Node to to the current
		} else {
			current.next = addRecurBonUtil(item, index - 1, current.next);
			return current;
		}

	}

	public void addRecurBon(E item, int index) {
		if (index < 0 || index > this.size) {
			throw new IllegalArgumentException("addRecurBon: index out of bounds");
		}

		this.head = addRecurBonUtil(item, index, this.head);
		size++;
	}


	// -----------------------------------------------------------------------





	
	/** 
	 * Removes and returns the first item from the list
	 *  
	 * @return The element that was removed
	 * @throws IllegalStateException is the list is empty
	 */
	public E removeFirst() {
		if (head==null) {
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
		 if (head==null) { // list is empty
			 throw new IllegalStateException("removeLast: list is empty");
		 }
		 if (head.next==null) { // list is a singleton list
			 E temp = head.data;
			 head = null;
			 size--;
			 return temp;
		 }
		 // list has two or more elements
		 Node<E> current = head;
		 
		 while (current.next.next!=null) {
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
		if (index<0 || index>size) {
			throw new IllegalArgumentException("remove: index out of bounds");
		}
		if (head==null) {
			throw new IllegalStateException("remove: list is empty");
		}
		// list is non-empty and index is in range
		if (index==0) {
			return removeFirst();
		}
		// list is non-empty, the index is in range and larger than 0
		
		Node<E> current=head;
		for (int i=0; i<index-1;i++) {
			current=current.next;
		}
		E temp = current.next.data;
		current.next = current.next.next;
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
	


	// -----------------------------------------------------------------------

	//TODO

	//Ming's Response 
	public SLL<E> cloneR() {
		SLL<E> result = new SLL<E>();


		result = cloneHelper(result, head);
		return result;

	}

	public SLL<E> cloneHelper(SLL<E> resultList, Node<E> n) {
		if (n == null) {
			return resultList;
		} else {
			resultList.add(n.data);
			return cloneHelper(resultList, n.next);
		}
	}

	//Bonelli's Response
	private Node<E> cloneRecurUtil(Node<E> current) {
		if (current == null) {
			return null;
		} else {
			return new Node<E>(current.data, cloneRecurUtil(current.next));
		}
	}

	public SLL<E> cloneRecur() {
		SLL<E> result = new SLL<E>();
		result.head = cloneRecurUtil(this.head);
		result.size = this.size;
		return result;
	}

	// -----------------------------------------------------------------------




	
	private boolean memR(E item, Node<E> current) {
		if (current==null) {
			return false;
		}
		// current is not null
		if (current.data.equals(item)) {
			return true;
		} else { 		// current is not null and current.data is not the item I am looking for.
		    return memR(item,current.next);
		}
		
	}
	
	public boolean memR(E item) {
		return memR(item,head);
	}
	
	public boolean mem(E item) {
		boolean found = false;
		Node<E> current=head;
		
		while (!found && current!=null) {
			found = found || current.data.equals(item);
			current = current.next;
		}
		
		return found;
		
	}
	
	public boolean mem2(E item) {
		Node<E> current=head;
		
		while (current!=null) {
			if (current.data.equals(item)) {
				return true;
			}
			current = current.next;
		}
		
		return false;
		
	}

	
//	public boolean hasDuplicates() {
//		
//		
//	}
////	
//	public void append(SLL<E> anotherList) {
//		
//		
//	}
//	
//	public void removeNullElements() {
//		
//	}
//	
//	public void reverse() {
//		
//	}
//	
//	public void removeAdjacentDuplicates() {
//		
//	}




//	@Override
//	public String toString() {
//		StringBuilder result = new StringBuilder();
//		Node<E> current=head;
//
//		result.append("[");
//		while (current!=null) {
//			result.append(current.data.toString()+",");
//			current = current.next;
//		}
//		result.append("]");
//
//		return result.toString();
//
//
//	}
	
	private StringBuilder toString(Node<E> current) {
		StringBuilder result = new StringBuilder();
		
		if (current==null) {
			return result;
		}
		result.append(current.data.toString()+",");
		result.append(toString(current.next));
		return result;
	}
	
	@Override
	public String toString() {
		return "["+toString(head).toString()+"]";
	}
	
	 private static void incList(Node<Integer> l) {
		  // TODO
		 if (l==null) {
			 return ;
		 } else {
			 l.data++;
			 incList(l.next);
		 }
	    }


	public static void incList(SLL<Integer> l) {
		incList(l.head);
	}

private Node<E> mergeInto(Node<E> l1, Node<E> l2) {
		
		Node<E> c1=l1;
		Node<E> c2=l2;
		Node<E> newHead = l1;
		
		while(c1!=null && c2!=null) {
			Node<E> temp = c1.next;
			Node<E> temp2 = c2.next;
			c1.next = c2;
			c2.next = temp;
			c1=temp;
			c2=temp2;
		}
		
		return newHead;
		
	}
	
	public void mergeLists(SLL<E> l2) {
		head = mergeInto(head,l2.head);
		size = size+Math.min(size,l2.size);
		
	}
	
	/**
	 * Exercise 7: removeAll.
	 * @param item
	 */
	public void removeAll(E item) {
		while (head!=null && head.data.equals(item)) {
			head=head.next;
			size--;
		}
		if (head==null) {
			return ;
		}
		
		Node<E> current=head;
		while (current.next!=null) {
			if (current.next.data.equals(item)) {
				current.next = current.next.next;
				size--;
			} else {
				current = current.next;
			}
		}
		
		
		
	}

	
	private int itemCount(E item,Node<E> head) {
		int result=0;
		Node<E> current = head;
		while (current!=null) {
			result = current.data.equals(item)?result+1:result;
			current=current.next;
		}
		return result;
		
	}
	
	public Integer itemCount(E item) {
		return itemCount(item,head);
	}
	
	// public SLL<Pair<E,Integer>> histogram() {
	// 	SLL<Pair<E,Integer>> result = new SLL<>();
	// 	SLL<E> chk = new SLL<E>();  
	// 	Node<E> current = head;
	// 	while (current!=null) {
	// 		if (!chk.mem(current.data)) {
	// 			chk.add(current.data);
	// 			result.add(new Pair<E,Integer>(current.data,itemCount(current.data)));
	// 		}
	// 		current=current.next;
			
	// 	}
	// 	return result;
		
		
		
	// }

	public static void main(String[] args) {
		   SLL<Integer> l = new SLL<>();

	          for (int i=0; i<10; i++) {
	        	  l.add(i);
	          }
	      
	          System.out.println(l);
	          l.addR(33);
						l.addR(74);
						l.addRecur(10, 4);
	          System.out.println(l);
	          l.removeLastR();
						System.out.println(l);
						
						SLL<Integer> l2 = l.clone();

						System.out.println(l2);
	          
	          
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
