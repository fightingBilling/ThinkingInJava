package chapter15;

class LinkedStack<T> {
	private class Node {
		T item;
		Node next;

		public Node() {
			this.item = null;
			this.next = null;
		}

		public Node(T item, Node next) {
			this.item = item;
			this.next = next;
		}

		boolean end() {
			return item == null && next == null;
		}
	}

	// end sentinel
	private Node top = new Node();

	public void push(T item) {
		top = new Node(item, top);
	}

	public T pop() {
		T result = top.item;
		if (!top.end()) {
			top = top.next;
		}

		return result;
	}

}

public class Exercise5 {
	public static void main(String[] args) {
		LinkedStack<String> lStack = new LinkedStack<String>();

		for (String s : "I AM COME FROM ALIBABA YUNOS".split(" ")) {
			lStack.push(s);
		}

		String s;

		while ((s = lStack.pop()) != null) {
			System.out.println(s);
		}

	}
}
