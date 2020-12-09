package recitation;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class HashtableOpen<K, V> extends AbstractMap<K, V> {
  private Entry<K, V>[] table;
  private static final int START_CAPACITY = 101;
  private double LOAD_THRESHOLD = 0.75;
  private int numKeys;
  private int numDeletes;
  private final Entry<K, V> DELETED = new Entry<K, V>(null, null);

  public static class Entry<K, V> implements Map.Entry<K, V> {
    private K key;
    private V value;
    private Entry<K, V> next;

    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
      this.next = null;
    }

    @Override
    public K getKey() {
      return key;
    }

    @Override
    public V getValue() {
      return value;
    }

    @Override
    public V setValue(V val) {
      V oldVal = value;
      value = val;
      return oldVal;
    }

    @Override
    public String toString() {
      return key.toString() + "=" + value.toString();
    }
  }

  public HashtableOpen() {
    this.table = new Entry[START_CAPACITY];
  }

  /***
   * Finds either the target key or the first empty slot in the search chain using
   * linear probing.
   * 
   * @pre The table is not full.
   * @param key The key of the target object
   * @return Position of the target or the first empty slot if the target is not
   *         in the table.
   */
  private int find(Object key) {
    int index = key.hashCode() % table.length;
    if (index < 0) {
      index += table.length; // Make it positive.
    }
    if (table[index] != null) {
      Entry<K, V> current = table[index];
      while (current != null && current.key != key)
        current = current.next;

      if (current == null) {
        throw new IllegalArgumentException("Key not in hash table");
      }
    }

    return index;
  }

  @Override
  public V remove(Object key) {
    int index = find(key);
    if (table[index] == null) {
      return null;
    }
    V oldValue = table[index].value;
    table[index] = DELETED;
    numKeys--;
    return oldValue;
  }

  @Override
  public Set<Map.Entry<K, V>> entrySet() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * retrieves the value of the key given returns null if key is not found
   */
  public V get(Object key) {
    int index = find(key);
    if (table[index] == null)
      return null;
    return table[index].value;
  }

  public ArrayList<V> getAll(Object key) {
    int index = find(key);
    if (table[index] == null)
      return null;

    ArrayList<V> values = new ArrayList<V>();
    Entry<K, V> current = table[index];
    while (current != null) {
      values.add(current.value);
    }
    return values;
  }

  /*
   * adds the value associated with key in the hashmap
   * 
   * if the key is not in the table, insert a new entry containing the key and
   * value and return val
   */
  public V put(K key, V val) {
    int index = find(key);

    Entry<K, V> current = table[index];
    if (current == null) {
      table[index] = new Entry<K, V>(key, val);
    } else {
      while (current.next != null && current.value != val) {
        current = current.next;
      }
      if (current.value != val) {
        current.next = new Entry<K, V>(key, val);
      }
    }
    return val;
  }

  public static void main(String args[]) {

  }

}