package libraries.list;


/***
 * 
 * @author Hyungjin, Oh
 * thesockerr@gmail.com
 * @param <T>
 */

public class CustomLinkedList<T extends Comparable<T>> implements CustomList<T> {
	
	public class Node { // inner Class
		private Node previous;
		private T object;
		private Node next;
		
		public Node() {
			this.previous = this;
			this.next = this;
		}
		public Node(T object) {
			this.previous = this;
			this.object = object;
			this.next = this;
		}
		public Node(Node previous, T object) {
			this.previous = previous;
			this.object = object;
			this.next = this;
		}
		public Node(T object, Node next) {
			this.previous = this;
			this.object = object;
			this.next = next;
		}
		public Node(Node previous, T object, Node next) {
			this.previous = previous;
			this.object = object;
			this.next = next;
		}
		public Node(final Node source) {
			this.previous = source.previous;
			this.object = source.object;
			this.next = source.next;
		}
		public T getObject() {
			return this.object;
		}
	}
	private Node head;
	private Node tail;
	private int length;
	private Node current;
	
	public int getLength() {
		return this.length;
	}
	public Node getCurrent() {
		return this.current;
	}
	
	public CustomLinkedList() {
		this.head = null;
		this.tail = null;
		this.length = 0;
		this.current = null;
	}
	public CustomLinkedList(final CustomLinkedList<T> source) {
		
	}
	
	public static void main(String args[]) {
		//CustomLinkedList<Integer> test1 = new CustomLinkedList<Integer>();
		
		
	}
}



