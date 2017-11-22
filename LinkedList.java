/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          P4 Research Geneology
// FILE:             LinkedList.java
//
// TEAM:    P4 Pair 32
// Authors: Matt P'ng, Jasper Nelson
// Author1: Matt P'ng, mpng@wisc.edu, mpng, 002
// Author2: Jasper Nelson, jnelson27@wisc.edu, jnelson27, 002
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: NA
// 
// Online sources: NA
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * An Iterable list that is implemented using a singly-linked chain of nodes
 * with a header node and without a tail reference.
 * 
 * The "header node" is a node without a data reference that will 
 * reference the first node with data once data has been added to list.
 * 
 * The iterator returned is a LinkedListIterator constructed by passing 
 * the first node with data.
 * 
 * CAUTION: the chain of nodes in this class can be changed without
 * calling the add and remove methods in this class.  So, the size()
 * method must be implemented by counting nodes.  This counting must
 * occur each time the size method is called.  DO NOT USE a numItems field.
 * 
 * COMPLETE THIS CLASS AND HAND IN THIS FILE
 */
public class LinkedList<E> implements ListADT<E> 
{
	private Listnode<E> head;
	
	/**
	 * LinkedList constructor, initializes head and curr node references to the header node of the list.
	 * 
	 * @author Matt P'ng Jasper Nelson
	 */
	public LinkedList()
	{
		head = new Listnode<E>(null);
	}

	/**
	 * Adds a data item to the end of the List.
	 * 
	 * @param item the item to add
	 * @throws IllegalArgumentException if item is null 
	 */
	public void add(E item)
	{
		if(item == null) throw new java.lang.IllegalArgumentException();
		
		Listnode<E> curr = head;
		
		while(curr.getNext() != null)
		{
			curr = curr.getNext();
		}
		
		curr.setNext(new Listnode<E>(item));
	}
	
	/**
	 * Adds a data item at position pos in the List, moving the items originally 
	 * in positions pos through size() - 1 one place to the right to make room.
	 * 
	 * @param pos the position at which to add the item
	 * @param item the item to add
	 * @throws IllegalArgumentException if item is null 
	 * @throws IndexOutOfBoundsException if pos is less than 0 or greater 
	 * than size()
	 */
	public void add(int pos, E item)
	{
		if(item == null) throw new java.lang.IllegalArgumentException();
		if(pos == 0 && this.size() == 0)
		{
			this.add(item);
			return;
		}
		if(pos < 0 || pos >= this.size()) throw new java.lang.IndexOutOfBoundsException();
	
		Listnode<E> curr = head;
		if(pos == 0)
		{
			head.setNext(new Listnode<E>(item, head.getNext()));
			return;
		}
		for(int h = pos; h > 0; h--)
		{
			curr = curr.getNext();
		}
		
		curr.setNext(new Listnode<E>(item, curr.getNext()));
		
	}
	
	/**
	 * Returns true iff item is in the List (i.e., there is an item x in the List 
	 * such that x.equals(item))
	 * 
	 * @param item the item to check
	 * @return true if item is in the List, false otherwise
	 */	
	public boolean contains(E item)
	{
		if(item == null) return false;
		
		Listnode<E> curr = head;
		while(curr.getNext() != null)
		{
			if(curr.getNext().getData().equals(item)) 
			{
				return true;
			}
			curr = curr.getNext();
		}
		
		return false;

	}
	
	/**
	 * Returns the item at position pos in the List.
	 * 
	 * @param pos the position of the item to return
	 * @return the item at position pos
	 * @throws IndexOutOfBoundsException if pos is less than 0 or greater than
	 * or equal to size()
	 */
	public E get(int pos)
	{
		if(pos < 0 || pos >= this.size()) throw new java.lang.IndexOutOfBoundsException();
		
		Listnode<E> curr = head;
		for(int j = pos; j > 0; j--)
		{
			curr = curr.getNext();
		}
		return curr.getNext().getData();
	}
	
	/**
	 * Returns true iff the List is does not have any data items.
	 * 
	 * @return true if the List is empty, false otherwise
	 */
	public boolean isEmpty()
	{
		if(head.getNext() == null) return true;
		else return false;
	}
	
	/**
	 * Removes and returns the item at position pos in the List, moving the items 
	 * originally in positions pos+1 through size() - 1 one place to the left to 
	 * fill in the gap.
	 * 
	 * @param pos the position at which to remove the item
	 * @return the item at position pos
	 * @throws IndexOutOfBoundsException if pos is less than 0 or greater than
	 * or equal to size()
	 */
	public E remove(int pos)
	{
		if(pos < 0 || pos >= this.size()) throw new java.lang.IndexOutOfBoundsException();
		
		Listnode<E> curr = head;
		for(int i = pos; i > 0; i--)
		{
			curr = curr.getNext();
		}
		E ret = curr.getNext().getData();
		
		curr.setNext(curr.getNext().getNext());
		return ret;
		
		
	}
	/*
	 * Returns the number of items in the List not including the null header node
	 * 
	 * @return the number of items in the List
	 */
	public int size()
	{
		Listnode<E> curr = head;
		int count = 0;
		
		while(curr.getNext() != null)
		{
			curr = curr.getNext();
			count++;
		}
		return count;
	}
	
	/** 
	 * Returns a reference to the header node for this linked list.
	 * The header node is the first node in the chain and it does not 
	 * contain a data reference.  It does contain a reference to the 
	 * first node with data (next node in the chain). That node will exist 
	 * and contain a data reference if any data has been added to the list.
	 * 
	 * NOTE: Typically, a LinkedList would not provide direct access
	 * to the headerNode in this way to classes that use the linked list.  
	 * We are providing direct access to support testing and to 
	 * allow multiple nodes to be moved as a chain.
	 * 
	 * @return a reference to the header node of this list. 0
	 */
	public Listnode<E> getHeaderNode() 
	{
		return head;
	}

	/**
	 * returns a reference to a LinkedListIterator for this linked list
	 * 
	 * @return reference to the Iterator
	 */
	public LinkedListIterator<E> iterator() 
	{
		return new LinkedListIterator<E>(head);
	}
}
