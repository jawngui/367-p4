import java.util.EmptyStackException;

public class Stack<T> implements StackADT<T> {

	public LinkedList<T> stack;
	
	public Stack(){
		stack = new LinkedList<T>();
	}
	
	public boolean isEmpty() {
		if (stack.size()==0){
			return true;
		}
		return false;
	}

	public void push(T data) throws IllegalArgumentException {
		if (data == null){
			throw new IllegalArgumentException();
		}
		stack.add(0, data);
	}

	public T peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		
		return stack.get(0);
	}

	public T pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		
		return stack.remove(0);
	}

	public StackADT<T> reverse() {
		Stack<T> rev = new Stack<T>();
		
		
		for(int i=0; i<stack.size() ; i++){
			rev.push(stack.get(i));
		}
			
		return rev;
	}

}
