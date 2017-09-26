
public class ArrayQueue implements Queue {
	Object[] arr;
	int head;
	int tail;
	
	public ArrayQueue() {
		arr = new Object[10];
		head = 0;
		tail = 0;
	}
	
	public void grow() {
		Object[] a = new Object[arr.length * 2];
		if (head > tail) {
			System.arraycopy(arr, 0, a, 0, tail);
			System.arraycopy(arr, head, a, arr.length + head, arr.length - head);
			head += arr.length;
		} else {
			System.arraycopy(arr, head, a, head, tail - head + 1);
		}
		arr = a;
	}
	
	@Override
	public Object dequeue() {
		if (empty())
			return null;
		if (head == arr.length - 1) {
			head = 0;
			return arr[arr.length - 1];
		}
		return arr[head++];
	}

	@Override
	public void enqueue(Object item) {
		if ((tail + 1) % arr.length == head) {
			grow();
		}
		arr[tail++] = item;
		tail %= arr.length; 
	}

	@Override
	public boolean empty() {
		return head == tail;
	}

}
