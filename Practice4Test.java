
public class Practice4Test {
	
	protected Queue queue;
	protected Stack stack;
	protected final int size_of_test = 500;
	protected final String[] expressionList = {"level", "Anna", "A nut for a jar of tuna!"};
	protected final String[] wrongList = {"first", "Matter fact", "Landed on his hip and busted his lip"};
	
	
	public Practice4Test() {
		queue = new ArrayQueue();
		stack = new ArrayStack();
	}
	
	
	public void clearData() {
		while (!queue.empty()) {
			queue.dequeue();
		}
		while (!stack.empty()) {
			stack.pop();
		}
	}
	
	
	public boolean isPalindrome(String item) {
		clearData();
		for (int i = 0; i < item.length(); i++) {
			stack.push(item.substring(i, i+1));
			queue.enqueue(item.substring(i, i+1));
		}

		while (! stack.empty() && ! queue.empty()) {
			char s = stack.pop().toString().toLowerCase().charAt(0);
			char q = queue.dequeue().toString().toLowerCase().charAt(0);
			
			// each of them must be a letter
			while (! stack.empty() && (97 > s || 122 < s)) {
				s = stack.pop().toString().toLowerCase().charAt(0);
			}
			while (! queue.empty() && (97 > q || 122 < q)) {
				q = queue.dequeue().toString().toLowerCase().charAt(0);
			}
			
			if (s != q) {
				return false;
			}
		}
		
		// pop all the characters which are not letters.
		char s = 0;
		while (!stack.empty() && (97 > s || 122 < s)) {
			s = stack.pop().toString().toLowerCase().charAt(0);
		}
		char q = 0;
		while (!queue.empty() && (97 > q || 122 < q)) {
			q = queue.dequeue().toString().toLowerCase().charAt(0);
		}
		
		// At this point, the stack AND the queue should be empty.
		if (!stack.empty() || !queue.empty())
			return false;
		
		return true;
	}
	
	
	public void runTest() {
		// Theory: The Queue and Stack constructors have been called.
		int grade = 0;
		try {
			// If the stack and queue are empty (default), that's a milestone.
			if (stack.empty())
				grade += 5;
			if (queue.empty())
				grade += 5;
			System.out.println("[+" + grade + "%] Queue and Stack declared correctly.");
	
			// First tests: is the queue correct? Is the stack correct?
			String first = "first";
			String second = "second";
			queue.enqueue(first);
			queue.enqueue(second);
			if (queue.dequeue() == first && queue.dequeue() == second && queue.empty()) {
				System.out.println("[+10%] Queue functions appear correct.");
				grade += 10;
			} else {
				System.out.println("[ XX ] One or more Queue functions appear incorrect.");
			}
			
			stack.push(first);
			stack.push(second);
			if (stack.pop() == second && stack.pop() == first && stack.empty()) {
				System.out.println("[+10%] Stack functions appear correct.");
				grade += 10;
			} else {
				System.out.println("[ XX ] One or more Stack functions appear incorrect.");
			}
			
			// Additional sanity test: place a large number of items in the stack / queue to see if it resizes.
			for (int i = 0; i < size_of_test; i++) {
				queue.enqueue(Integer.MAX_VALUE);
				stack.push(Integer.MIN_VALUE);
			}
			boolean emptiedEarly = false;
			for (int i = 0; i < size_of_test; i++) {
				if ( queue.empty() || stack.empty()) {
					emptiedEarly = true;
				}
				queue.dequeue();
				stack.pop();
			}
			if (queue.empty() && stack.empty() && ! emptiedEarly) {
				System.out.println("[+10%] Stack and Queue appear to resize correctly.");
				grade += 10;
			} else {
				System.out.println("[ XX ] Stack or Queue do not resize correctly.");
			}
			
			System.out.println("====================");
	
			// Second tests: does it correctly find palindromes?
			for (String expression : expressionList) {
				if (isPalindrome(expression)) {
					System.out.println("[+10%] \"" + expression + "\" is a palindrome (correct).");
					grade += 10;
				}
				else {
					System.out.println("[ XX ] \"" + expression + "\" determined to be a non-palindrome (incorrect).");
				}
			}
			
			System.out.println("====================");
	
			// Third tests: can it correctly identify non-palindromes?
			for (String expression : wrongList) {
				// System.out.print(expression + " determined to be: ");
				if (isPalindrome(expression)) {
					System.out.println("[ XX ] \"" + expression + "\" determined to be a palindrome (incorrect).");
				}
				else {
					System.out.println("[+10%] \"" + expression + "\" is a non-palindrome (correct)");
					grade += 10;
				}
			}
		} catch (Exception e) {
			// Do nothing
		} finally {
			System.out.println("====================");
			System.out.println("Grade for this assignment: " + grade + "%");
		}
	}

	public static void main(String[] args) {
		Practice4Test test = new Practice4Test();
		test.runTest();
	}

}
