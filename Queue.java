/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          P4 Research Geneology
// FILE:             Queue.java
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
 * This class creates and uses a Queue data structure. Items are added to the 
 * end of a queue and removed from the front. We implement this queue using a 
 * LinkedList.
 *
 * @author Matt P'ng
 */
public class Queue<T> implements QueueADT<T> {
	
	public LinkedList<T> q;
	
//	Constructor 
	public Queue(){
		q = new LinkedList<T>();
	}
	
	/**
	 * @return true if the Queue is empty
	 */
	public boolean isEmpty() {
		if (q.size()==0){
			return true;
		}
		return false;
	}

	/**
	 * Adds an item to the back of a queue
	 * 
	 * @return true if the Queue is empty
	 * @throws IllegalArgumentException if data is null
	 */
	public void enqueue(T data) throws IllegalArgumentException {
		if (data == null){
			throw new IllegalArgumentException();
		}
		q.add(data);
	}

	/**
	 * Return and remove the next item (from the front) of the queue,
	 *
     * @throws EmptyQueueException if queue is empty
     */
	public T dequeue() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		return q.remove(0);
	}

	/**
	 * Get and do not remove the next item in the queue,
     *
     * @throws EmptyQueueException if queue is empty 
     */
	public T element() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		return q.get(0);
	}
}
