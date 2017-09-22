
public class ArrayStack implements Stack {
	
	Object[] arr;
	int index;
	
	public ArrayStack() {
		arr = new Object[10];
		index = -1;
	}
	
	public void grow() {
		Object[] a = new Object[arr.length * 2];
		System.arraycopy(arr, 0, a, 0, arr.length);
		arr = a;
	}
	
	@Override
	public void push(Object item) {
		if (arr.length - 1 == index) {
			// the array is full, double the size
			grow();
		}
		arr[++index] = item;
	}

	@Override
	public Object pop() {
		return arr[index--];
	}

	@Override
	public Object peek() {
		return arr[index];
	}

	@Override
	public boolean empty() {
		return index == -1;
	}

}
