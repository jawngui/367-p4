/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          p2
// FILE:             LinkedListIterator.java
//
// TEAM:    Individual
// Authors: Jasper Nelson
// Author1: jnelson27@wisc.edu
// 
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons:CSLC Tutor Sam
// 
// 
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The iterator implementation for LinkedList.  The constructor for this
 * class requires that a reference to a Listnode with the first data
 * item is passed in.
 * 
 * If the Listnode reference used to create the LinkedListIterator is null,
 * that implies there is no data in the LinkedList and this iterator
 * should handle that case correctly.
 * 
 * COMPLETE THIS CLASS AND HAND IN THIS FILE
 */
public class LinkedListIterator<T> implements Iterator<T> {
	
	private Listnode<T> curr;

	/**
	 * Constructs a LinkedListIterator when given the first node
	 * with data for a chain of nodes.
	 * 
	 * Tip: do not construct with a "blank" header node.
	 * 
	 * @param a reference to a List node with data. 
	 */
	public LinkedListIterator(Listnode<T> head) 
	{
		if(head.getData() == null)
		{
			curr = head.getNext();
		}
		else
		{
			curr = head;
		}
	}
	
	/**
	 * Returns the next element in the iteration and then advances the
	 * iteration reference.
	 * 
	 * @return the next data item in the iteration that has not yet been returned 
	 * @throws NoSuchElementException if the iteration has no more elements 
	 */
	@Override
	public T next() 
	{
		if(curr == null) throw new java.util.NoSuchElementException();
		Listnode<T> tmp = curr;
		curr = curr.getNext();
		return tmp.getData();
	}
	
	/**
	 * Returns true if there are more data items to iterate through 
	 * for this list.
	 * 
	 * @return true if there are any remaining data items to iterator through
	 */
	@Override
	public boolean hasNext() 
	{
		return curr != null;
	}
       
    /**
     * The remove operation is not supported by this iterator
     * @throws UnsupportedOperationException if the remove operation is not 
     * supported by this iterator
     */
    @Override
	public void remove() {
	  throw new UnsupportedOperationException();
	}

}
