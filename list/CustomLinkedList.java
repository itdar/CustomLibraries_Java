package libraries.list;


/***
 * 
 * @author Hyungjin, Oh
 * thesockerr@gmail.com
 * @param <T>
 */

public class CustomLinkedList<T extends Comparable<T>> implements CustomList<T> {
	
	private class Node { // inner Class
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
	
	public Node makeNode() {
		Node tempNode = new Node();
		return tempNode;		
	}
	public Node makeNode(T object) {
		Node tempNode = new Node(object);
		return tempNode;
	}
	public Node copyNode(Node node) {
		Node tempNode = new Node(node.previous, node.object, node.next);
		return tempNode;
	}
	
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
		Node sourcePrevious = source.head;
		Node sourceIt = sourcePrevious.next;
		Node newNode;
		Node newPrevious;
		
		newNode = new Node(sourcePrevious.object);
		this.current = newNode;
		this.head = this.current;
		this.tail = this.current;
		newPrevious = this.head;
		
		while (sourcePrevious != source.current
				&& sourcePrevious != sourceIt) {
			newNode = new Node(newPrevious, sourceIt.object);
			newPrevious.next = newNode;
			newPrevious = newNode;
			this.current = newNode;
			
			sourcePrevious = sourceIt;
			sourceIt = sourceIt.next;
		}
		while (sourcePrevious != sourceIt) {
			newNode = new Node(newPrevious, sourceIt.object);
			newPrevious.next = newNode;
			newPrevious = newNode;
			this.current = newNode;
			
			sourcePrevious = sourceIt;
			sourceIt = sourceIt.next;
		}
		this.tail = newNode;
		this.length = source.length;
	}
	
	
	public Node appendToHead(T object) {
		if (this.head != null) {
			this.current = new Node(object, this.head);
		}
		else {
			this.current = new Node(object);
		}
		if (this.head != null) {
			this.head.previous = this.current;
		}
		else {
			this.tail = this.current;
		}
		this.head = this.current;
		this.length++;
		
		return this.current;
	}
	
	
	public static void main(String args[]) {
		CustomLinkedList<Integer> test1 = new CustomLinkedList<Integer>();
		test1.appendToHead(5);
		test1.appendToHead(4);
		test1.appendToHead(3);
		test1.appendToHead(2);
		
//		CustomLinkedList.Node index = null;
//		CustomLinkedList.Node nextIndex = test1.head;
//		while (index != nextIndex) {
//			System.out.println(nextIndex.object);
//			index = nextIndex;
//			nextIndex = nextIndex.next;
//		}
		
		CustomLinkedList<Integer> test2 = new CustomLinkedList<Integer>(test1);
		
		CustomLinkedList.Node index = null;
		CustomLinkedList.Node nextIndex = test2.head;
		while (index != nextIndex) {
			System.out.println(nextIndex.object);
			index = nextIndex;
			nextIndex = nextIndex.next;
		}
		
	}
}



