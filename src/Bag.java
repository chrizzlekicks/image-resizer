import java.util.Iterator;

/**
 *The {@code Bag} class represents a bag (or multiset) of generic items.
 * @param <E>
 */
public class Bag<E> implements Iterable<E>
{
  private Node head;
  private int N;
 
  private class Node
  { E item;
    Node next;
  }

  /**
   * @return size of the bag
   */
  public int size()        { return N; }
  
  /**
   * @return whether the bag is empty
   */
  public boolean isEmpty() { return N == 0; }  
  
  /**
   * Adds the item to the bag and sets a new head.
   * @param item the to be added item
   */
  public void add(E item) {
    Node tmp = head;
    head = new Node();
    head.item = item;
    head.next = tmp;
    N++;
  }
  
   /**
    * Returns an iterator that iterates over the items in this bag in arbitrary order.
    *
    * @return an iterator that iterates over the items in this bag in arbitrary order
    */
  public Iterator<E> iterator()
  { return new ListIterator();
  }

  /**
  * This class implement an iterator
  */
  public class ListIterator implements Iterator<E>
  {
    private Node current = head;

    public boolean hasNext() { return current != null; }
    public void remove()     { }  // kein remove
    public E next()
    { E item = current.item;
      current = current.next;
      return item;
    }
  }

  public String toString()
  {
    String str = "";
    for (E e : this)
      str += e + ", ";
    return str;
  }
}

