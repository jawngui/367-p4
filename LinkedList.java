/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          p2
// FILE:             LinkedList.java
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
	

	public LinkedList()
	{
		head = new Listnode<E>(null);
	}

	
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
		}
		for(int h = pos; h > 0; h--)
		{
			curr = curr.getNext();
		}
		
		curr.setNext(new Listnode<E>(item, curr.getNext()));
		
	}
	
	
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
	
	
	public boolean isEmpty()
	{
		if(head.getNext() == null) return true;
		else return false;
	}
	
	
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
	 *returns the size of the list, not including the null header node
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
	
	
	public Listnode<E> getHeaderNode() 
	{
		return head;
	}

	
	public LinkedListIterator<E> iterator() 
	{
		return new LinkedListIterator<E>(head);
	}
}
