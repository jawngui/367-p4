
public class Queue<T> implements QueueADT<T> {
	
	public LinkedList<T> q;
	
	public Queue(){
		q = new LinkedList<T>();
	}
	

	public boolean isEmpty() {
		if (q.size()==0){
			return true;
		}
		return false;
	}


	public void enqueue(T data) throws IllegalArgumentException {
		if (data == null){
			throw new IllegalArgumentException();
		}
		q.add(data);
	}

	
	public T dequeue() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
	
		return q.remove(0);
	}

	
	public T element() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}

		return q.get(0);
	}

	
}
