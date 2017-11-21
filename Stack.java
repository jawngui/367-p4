/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          P4
// FILE:             Stack.java
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

import java.util.EmptyStackException;

public class Stack<T> implements StackADT<T> {

	public LinkedList<T> stack;
	
//	Constructor
	public Stack(){
		stack = new LinkedList<T>();
	}
	
	/**
	 * @return true if this Stack is empty
	 * */
	public boolean isEmpty() {
		if (stack.size()==0){
			return true;
		}
		return false;
	}
	
	   /**
     * Add the data item to top of the Stack.
     * @throws IllegalArgumentException if data is null
     */
	public void push(T data) throws IllegalArgumentException {
		if (data == null){
			throw new IllegalArgumentException();
		}
		stack.add(0, data);
	}

	 /**
     * Returns the element from the top of Stack,
     * without removing it from the stack.
     *
     * @throws java.util.EmptyStackException if the stack is empty
     */
	public T peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		
		return stack.get(0);
	}

	 /**
     * Returns and removes the element from the top of Stack,
     *
     * @throws java.util.EmptyStackException if the stack is empty
     */
	public T pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		
		return stack.remove(0);
	}

	/**
	 * Creates a new Stack with the items of this stack
	 * in the reverse order.
	 * The items in this stack remain the same order.
	 *
	 * If this stack is empty, the reverse order stack is also empty.
	 */
	public StackADT<T> reverse() {
		Stack<T> rev = new Stack<T>();
		
		for(int i=0; i<stack.size() ; i++){
			rev.push(stack.get(i));
		}
			
		return rev;
	}
}

