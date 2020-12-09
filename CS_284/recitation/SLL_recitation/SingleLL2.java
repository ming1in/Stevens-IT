package recitation.SLL_recitation;

public class SingleLL2<E>
	{
	
	
	private class Node<F>
		{
		public F data;
		public Node<F> next;
		
		public Node(F data, Node<F> next)
			{
			this.data = data;
			this.next = next;
			}
		
		public Node(F data)
			{
			this.data = data;
			this.next = null;
			}
			
		}
	
	//Data Fields
	private Node<E> head;
	private int size;
	
	public SingleLL2()
		{
		this.head = null;
		this.size = 0;
		}
	
	//puts data at the end of the list
	public boolean add(E data)
		{
		if(head == null)
			{head = new Node<E>(data);}
		else
			{
			Node<E> current = head;
			
			while(current.next != null)
				{
				current = current.next;
				}
			current.next = new Node<E>(data);
			}
		
		return true;
		}
	
	public boolean deleteLast()
		{			
		//empty list?
		if(head == null)
			{throw new IllegalStateException("List is empty, cannot delete");}
		//single item list?
		if(head.next == null)
			{head = null;}
		//general case, multi item list
		else
			{
			Node<E> current = head;
			
			while(current.next.next != null)
				{
				current = current.next;
				}
			current.next = null;
			}
		return true;
		}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		Node<E> current = head;
		s.append("[");
		
		while (current!=null) {
			s.append(current.data.toString()+",");
			current = current.next;
		}
		
		s.append("]");
		return s.toString();
	}
	
	
	public static void main(String args[])
		{
		SingleLL2<Integer> l1 = new SingleLL2<Integer>();
		
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);

		System.out.println(l1);
		l1.deleteLast();
		System.out.println(l1);
		}
	
	}















