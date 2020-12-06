package exercise_book.lists;

import java.util.HashSet;

public class SingleLL<E> {
  
  private static class Node<F> {
    private F data;
    private Node<F> next;

    Node(F data) {
      this.data = data;
    }

    Node(F data, Node<F> next) {
      this.data = data;
      this.next = next;
    }
  }

  private Node<E> head;
  private int size;

  SingleLL() {
    this.head = null;
    this.size = 0;
  }

  /**
   * this method add a new element to the start of the list
   */
  public E addFirst(E element) {
    Node<E> newNode = new Node<E>(element);
    newNode.next = this.head;
    this.head = newNode;
    this.size++;
    
    return element;
  }

  /**
   * this method add a new element to the end of the list
   */
  public E add(E element) {
    Node<E> newNode = new Node<E>(element);
        
    if (this.head == null) {
      this.head = newNode;
      this.size++;
      return element;
    }

    Node<E> current = this.head;
    while (current.next != null) {
      current = current.next;
    }
    current.next = newNode;
    this.size++;

    return element;
  }

  public E add(E element, int index) {

    while (index != 0) {
      
    }
  }
  

  /**
   * @return a boolean indicating whether the recipient list is a singleton list
   * or not.
   */
  public boolean isSingleton() {
    if (this.head.next != null) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * @return a boolean indicating whether all the elements in the list are non-null
   */
  public boolean allNonNull() {
    Node<E> current = this.head;

    while (current.next != null) {
      if (current.data == null) {
        return false;
      }
      current = current.next;
    }

    return true;
  }
  
  /**
   * @return a boolean indication whether el belongs to the list
   */
  public boolean mem(E el) {
    Node<E> current = this.head;

    while (current.next != null) {
      if (el == current.data) {
        return true;
      }
      current = current.next;
    }
    return false;
  }
  
  /**
   * @return a boolean indicating whether the list has duplicates or not
   */
  public boolean nonDuplicates() {
    HashSet<E> values = new HashSet<E>();

    Node<E> current = this.head;

    while (current != null) {
      if (values.contains(current.data)) {
        return false;
      }

      values.add(current.data);
      current = current.next;
    }

    return true;
  }

  /**
   * creates a copy of the list
   */
  public SingleLL<E> clone() {
    SingleLL<E> newSingleLL = new SingleLL<E>();

    Node<E> current = this.head;

    while (current != null) {
      newSingleLL.add(current.data);
      current = current.next;
    }

    return newSingleLL;
  }
  
  /**
   * @param list2 List to be appended
   * @return
   */
  public SingleLL<E> append(SingleLL<E> list2) {
    SingleLL<E> newList = new SingleLL<E>();

    Node<E> current1 = this.head;
    Node<E> current2 = list2.head;

    while (current1 != null) {
      newList.add(current1.data);
      current1 = current1.next;
    }

    while(current2 != null){
      newList.add(current2.data);
      current2 = current2.next;
    }

    return newList;
  }

  /**
   * @return a new list where the elements are reversed
   */
  public SingleLL<E> reverse() {
    SingleLL<E> reverseList = new SingleLL<E>();

    Node<E> current = this.head;

    while (current != null) {
      reverseList.addFirst(current.data);
      current = current.next;
    }

    return reverseList;
  }
  
  /**
   * reverse the list in-place, not creating new node
   */
  public void reverseInplace() {
    Node<E> current = this.head;
    Node<E> prev = null;

    while (current != null) {
      Node<E> next = current.next;
      current.next = prev;
      prev = current;

      current = next;
    }

    this.head = prev;
  }

  public SingleLL<Boolean> areNull() {
    SingleLL<Boolean> boolList = new SingleLL<Boolean>();

    Node<E> current = this.head;

    while (current != null) {
      if (current.data != null) {
        boolList.add(false);
      } else {
        boolList.add(true);
      }

      current = current.next;
    }

    return boolList;
  }
  
  /**
   * @return a new list in which n copies of the original list have been juxtaposed,
   * Eg. Given the list[1, 2, 3]and the number 3 it should return[1, 2, 3, 1, 2,
   * 3, 1, 2, 3].
   */
  public SingleLL<E> repeatLN(Integer n) {
    SingleLL<E> juxtaposedList = new SingleLL<E>();

    for (int i = 0; i < n; i++) {
      juxtaposedList = juxtaposedList.append(this);
    }

    return juxtaposedList;
  }
  
  /**
   * repeats each element in the list n times. Eg. Given[1, 2, 3] and the number 3, it
   * should return[1, 1, 1, 2, 2, 2, 3, 3, 3].
   */
  public SingleLL<E> stutterNL(Integer n) {
    SingleLL<E> stutterList = new SingleLL<E>();

    Node<E> current = this.head;

    while (current != null) {
      for (int i = 0; i < n; i++) {
        stutterList.add(current.data);
      }

      current = current.next;
    }

    return stutterList;
  }

  /**
   * this methods removes any elements who have duplicate adjacent elements
   */
  public void removeAdjacentDuplicates() {
    Node<E> current = this.head;
    Node<E> prev = null;

    while (current.next != null) {
      if (prev != null) {
        if (current.data == prev.data) {
          prev.next = current.next;
        }
      }

      prev = current;
      current = current.next;
    }
  }
  
  /**
   * removes all null elements. Eg. Given[1,null,3,null,5]it should return[1,3,5].
   */
  public void filterNonNull() {
    Node<E> current = this.head;

    while (current.next != null) {
      if (current.next.data == null) {
        current.next = current.next.next;
      }

      current = current.next;
    }
  }

  /**
   * Given :[1, 3, 5]and[2, 4, 6]), it should return[1, 2, 3, 4,5, 6]. Provide a
   * solution in which a new list is constructed. Then provide another solution
   * where the two given lists are “weaved” appropriately.
   */
  public SingleLL<E> zipList(SingleLL<E> list2) {
    SingleLL<E> zippedList = new SingleLL<E>();

    Node<E> current1 = this.head;
    Node<E> current2 = list2.head;

    while (current1 != null && current2 != null) {
      if ((int)(current1.data) < (int)(current2.data)) {
        zippedList.add(current1.data);
        current1 = current1.next;
      } else {
        zippedList.add(current2.data);
        current2 = current2.next;
      }
    }
    
    return zippedList;
  }
  
  public void weaveList(SingleLL<E> list2) {
    Node<E> current1 = this.head;
    Node<E> current2 = list2.head;

    while (current1 != null && current2 != null) {
      if(((int) current1.data) < ((int) current2.data) ){

      }
    }
  }
  
  @Override
  public String toString() {
    StringBuilder list = new StringBuilder();

    Node<E> current = this.head;
    list.append("[ ");
    while (current != null) {
      if (current.data == null) {
        list.append(null + ", ");
      } else {
      list.append(current.data.toString() + ", ");
      }
      current = current.next;
    }
    list.append("]");

    return list.toString();
  }
  
  public static void main(String[] args) {
    SingleLL<Integer> list1 = new SingleLL<Integer>();
    list1.add(1);
    list1.add(2);
    list1.add(3);
    list1.add(4);
    list1.add(5);
    list1.add(null);
    list1.add(3);

    System.out.println(list1);
    System.out.println("isSingleton(): " + list1.isSingleton()); 
    System.out.println("allNonNull(): " + list1.allNonNull());
    System.out.println("mem(): " + list1.mem(8)); 
    System.out.println("nonDuplicates(): " + list1.nonDuplicates()); 

    SingleLL<Integer> list2 = list1.clone();
    list2.add(10);
    System.out.println("clone(): " + list2);

    System.out.println("append(): " + list1.append(list2));

    list1.addFirst(22);
    System.out.println("addFirst(): " + list1);

    System.out.println("reverse(): " + list1.reverse());

    list1.reverseInplace();
    System.out.println("reverseInplace(): " + list1);

    System.out.println("areNull(): " + list1.areNull());
    System.out.println("repeatLN(): " + list1.repeatLN(3));
    System.out.println("stutterNL(): " + list1.stutterNL(2));

    list1.removeAdjacentDuplicates();
    System.out.println("removeAdjacentDuplicates(): " + list1);

    list1.filterNonNull();
    System.out.println("filterNonNull(): " + list1);

    SingleLL<Integer> list3 = new SingleLL<Integer>();
    list3.add(1);
    list3.add(3);
    list3.add(5);

    SingleLL<Integer> list4 = new SingleLL<Integer>();
    list4.add(2);
    list4.add(4);
    list4.add(6);

    System.out.println("zipList():" + list3.zipList(list4));

    

  }
}
