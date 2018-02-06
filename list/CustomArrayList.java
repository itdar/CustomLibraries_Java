package libraries.list;

/***
 * 
 * @author Hyungjin, Oh
 * thesockerr@gmail.com
 * @param <T>
 */

public class CustomArrayList<T extends Comparable<T>> implements CustomList<T> {
	private T[] front;
	private int capacity;
	private int length;

	public CustomArrayList() {
		//System.out.println("Basic Constructor");
		this.front = null;
		this.capacity = 0;
		this.length = 0;
	}
	public CustomArrayList(int _capacity) {
		//System.out.println("Capacity Constructor");
		this.front = (T[]) new Comparable[_capacity];
		this.capacity = _capacity;
		this.length = 0;
	}
	//copy constructor, but don't need to make like this in java. It's c++ style. Second one below.
//	public CustomArrayList(final CustomArrayList<T> source) {
//		//System.out.println("Copy Constructor");
//		this.front = source.front;
//		int i = 0;
//		while (i < source.length) {
//			this.front[i] = source.front[i];
//			i++;
//		}
//		this.capacity = source.capacity;
//		this.length = source.length;
//	}
	public CustomArrayList(CustomArrayList<T> source) {
		this.front = (T[]) source.front;
		this.length = source.length;
		this.capacity = source.capacity;
	}
	protected void finalize() {
		System.out.println("Finalize");
	}
	
	public int append(int index, T object) {
		this.front[index] = object;
		this.length++;
		
		return index;
	}
	public int insert(int index, T object) {
		T[] temp = (T[])new Comparable[this.capacity + 1];
		int i = 0;
		int j = 0;
		while (i < index) {
			temp[j] = this.front[i];
			j++;
			i++;
		}
		j++;
		while (i < this.length) {
			temp[j] = this.front[i];
			j++;
			i++;
		}
		if (this.front != null) {
			// delete original array??
			this.front = null;
		}
		this.front = temp;
		this.capacity++;
		this.front[index] = object;
		this.length++;
		
		return index;
	}
	public int appendToFirst(T object) {
		int index = 0;
		T[] temp = (T[])new Comparable[this.capacity + 1];
		int i = 0;
		int j = 1;
		
		while (i < this.length) {
			temp[j] = this.front[i];
			j++;
			i++;
		}
		if (this.front != null) {
			// delete original array??
			this.front = null;
		}
		this.front = temp;
		this.front[index] = object;
		this.capacity++;
		this.length++;
		
		return index;
	}
	public int appendToEnd(T object) {
		T[] temp = (T[])new Comparable[this.capacity + 1];
		int i = 0;
		while (i < this.length) {
			temp[i] = this.front[i];
			i++;
		}
		if (this.front != null) {
			// delete original array??
			this.front = null;
		}
		this.front = temp;
		int index = this.length;
		this.front[index] = object;
		this.capacity++;
		this.length++;
		
		return index;
	}
	public boolean delete(int index) { // need to edit
		boolean isDeleted = false;
		if (index >= 0 && index < this.length) {
			T[] temp = null;
			int i = 0;
			int j = 0;
			if (this.capacity > 1) {
				temp = (T[])new Comparable[this.capacity - 1];
			}
			while (i < index) {
				temp[j] = this.front[i];
				j++;
				i++;
			}
			i++;
			while (i < this.length) {
				temp[j] = this.front[i];
				j++;
				i++;
			}
			if (this.front != null) {
				//delete original array??
				this.front = null;
			}
			if (this.capacity > 1) {
				this.front = temp;
			}
			this.length--;
			this.capacity--;
			isDeleted = true;
		}
		
		return isDeleted;
	}
	public boolean deleteFirst() {
		boolean isDeleted = false;
		if (this.length > 0) {
			T[] temp = null;
			int i = 1;
			int j = 0;
			if (this.capacity > 1) {
				temp = (T[])new Comparable[this.capacity-1];
			}
			while (i < this.length) {
				temp[j] = this.front[i];
				j++;
				i++;
			}
			if (this.front != null) {
				//delete original array?
				this.front = null;
			}
			if (this.capacity > 1) {
				this.front = temp;
			}
			this.capacity--;
			this.length--;
			isDeleted = true;
		}
		return isDeleted;
	}
	public boolean deleteEnd() {
		boolean isDeleted = false;
		if (this.length > 0) {
			T[] temp = null;
			int i = 0;
			if (this.capacity > 1) {
				temp = (T[])new Comparable[this.capacity - 1];
			}
			while (i < this.capacity - 1) {
				temp[i] = this.front[i];
				i++;
			}
			if (this.front != null) {
				// delete original array?
				this.front = null;
			}
			if (this.capacity > 1) {
				this.front = temp;
			}
			this.capacity--;
			this.length--;
			isDeleted = true;
		}
		return isDeleted;
	}
	public void clear() {
		if (this.front != null) {
			// delete array?
			this.front = null;
		}
		this.capacity = 0;
		this.length = 0;
	}
	public int modify(int index, T object) {
		this.front[index] = object;
		return index;
	}
	public int linearSearchUnique(T key) {
		int index = -1;
		int i = 0;
		while (i < this.length && !(this.front[i].equals(key))) {
			i++;
		}
		if (i < this.length) {
			index = i;
		}
		return index;
	}
	//TODO need to check count[0]
	public void linearSearchMultiple(T key, Integer[] indexes, Integer[] count) {
		int i = 0;
		int j = 0;
		count[0] = 0;
		while (i < this.length) {
			if (this.front[i].equals(key)) {
				indexes[j] = i;
				count[0]++;
				j++;
			}
			i++;
		}
		//System.out.println(count);
	}
	public int binarySearchUnique(T key) {
		int index = -1;
		int max = this.length - 1;
		int min = 0;
		int center = (max + min) / 2;
		while (max >= min && !this.front[center].equals(key)) {
			//TODO need to check Comparable vs Object
			if (this.front[center].compareTo(key) < 0) {
				min = center + 1;
			}
			else {
				max = center - 1;
			}
			center = (max + min) / 2;
		}
		if (max >= min) {
			index = center;
		}
		return index;
	}
	//Only you can use in sorted array
	public void binarySearchMultiples(T key, Integer[] indexes, Integer[] count) {
		int i = 0;
		int min = 0;
		count[0] = 0;
		int max = this.length - 1;
		int center = (max + min) / 2;
		
		while (max >= min && !this.front[center].equals(key)) {
			if (this.front[center].compareTo(key) < 0) {
				min = center + 1;
			}
			else {
				max = center - 1;
			}
			center = (max + min) / 2;
		}
		min = center - 1;
		while (min >= 0 && this.front[min].equals(key)) {
			min--;
		}
		min++;
		while (min <= max && this.front[min].equals(key)) {
			indexes[i] = min;
			count[0]++;
			i++;
			min++;
		}
	}
	public void bubbleSort() {
		int i = 0;
		int j;
		T temp;
		while (i < this.length - 1) {
			j = 0;
			while (j < this.length - i - 1) {
				if (this.front[j].compareTo(this.front[j + 1]) > 0) {
					temp = this.front[j + 1];
					this.front[j + 1] = this.front[j];
					this.front[j] = temp;
				}
				j++;
			}
			i++;
		}
	}
	public void selectionSort() {
		int i = 0;
		int j;
		T temp;
		while (i < this.length - 1) {
			j = i + 1;
			while (j < this.length) {
				if (this.front[i].compareTo(this.front[j]) > 0) {
					temp = this.front[i];
					this.front[i] = this.front[j];
					this.front[j] = temp;
				}
				j++;
			}
			i++;
		}
	}
	public void insertionSort() {
		int i = 1;
		int j;
		T temp;
		while (i < this.length) {
			temp = this.front[i];
			j = i - 1;
			while (j >= 0 && this.front[j].compareTo(temp) > 0) {
				this.front[j+1] = this.front[j];
				j--;
			}
			this.front[j+1] = temp;
			i++;
		}
	}
	// you need to use when both arrays are sorted
	public void mergeSort(final CustomArrayList<T> one, final CustomArrayList<T> other) {
		int i = 0;
		int j = 0;
		int k = 0;
		if (this.front != null) {
			// need to delete??
			this.front = null;
		}
		this.front = (T[])new Comparable[one.length + other.length];
		this.capacity = one.length + other.length;
		this.length = 0;
		while (i < one.length && j < other.length) {
			if (one.front[i].compareTo(other.front[j]) < 0) {
				this.front[k] = one.front[i];
				this.length++;
				k++;
				i++;
			}
			else {
				this.front[k] = other.front[j];
				this.length++;
				k++;
				j++;
			}
		}
		while (i < one.length) {
			this.front[k] = one.front[i];
			this.length++;
			k++;
			i++;
		}
		while (j < other.length) {
			this.front[k] = other.front[j];
			this.length++;
			k++;
			j++;
		}
	}
	
	// =
	public CustomArrayList<T> operatorIs (final CustomArrayList<T> source) {
		if (this.front != null) {
			// delete?
			this.front = null;
		}
		int i = 0;
		this.front = (T[])new Comparable[source.capacity];
		while (i < source.length) {
			this.front[i] = source.front[i];
			i++;
		}
		this.capacity = source.capacity;
		this.length = source.length;
		
		return this;
	}
	//TODO need to check operator [], +
	
	public T getFrom(int index) {
		return this.front[index];
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	public int getLength() {
		return this.length;
	}
	
	
	
	
	public static void main(String[] args) {
		int i = 0;
		
		CustomArrayList<Integer> test1 = new CustomArrayList<Integer>(7);
		
		test1.append(test1.length, 4);
		test1.append(test1.length, 5);
		test1.append(test1.length, 1);
		test1.append(test1.length, 19);
		test1.append(test1.length, 15);
		test1.append(test1.length, 101);
		test1.append(test1.length, 32);
		test1.insert(0, 201);
		test1.appendToEnd(101);
		test1.appendToFirst(101);
		while (i < test1.length) {
			System.out.println("test1 " + i + " >> " + test1.getFrom(i));
			i++;
		}
		System.out.println("===========================================1");
		Integer[] indexes = new Integer[test1.length];
		Integer[] count = new Integer[1];
		test1.linearSearchMultiple(101, indexes, count);
		i = 0;
		while (i < count[0]) {
			System.out.println("test1 " + indexes[i] + " >> " + test1.getFrom(indexes[i]));
			i++;
		}
		System.out.println("===========================================22");
		
		test1.deleteEnd();
		test1.deleteFirst();
		int index = test1.linearSearchUnique(101);
		System.out.println(test1.getFrom(index));
		System.out.println("===========================================333");
		test1.delete(index);
		
		CustomArrayList<Integer> test2 = new CustomArrayList<Integer>(test1);
		
		test1.bubbleSort();
		index = test1.binarySearchUnique(19);
		test1.modify(index, 1999);
		i = 0;
		while (i < test1.length) {
			System.out.println("test1 " + i + " >> " + test1.getFrom(i));
			i++;
		}
		System.out.println("===========================================4444");
		test1.clear();
		test1.appendToFirst(2248);
		test1.appendToEnd(35931);
		test1.appendToFirst(8429);
		test1.appendToEnd(4999);
		
		test1.selectionSort();
		test2.insertionSort();
		i = 0;
		while (i < test1.length) {
			System.out.println("test1 " + i + " >> " + test1.getFrom(i));
			i++;
		}
		System.out.println("===========================================55555");
		i = 0;
		while (i < test2.length) {
			System.out.println("test2 " + i + " >> " + test2.getFrom(i));
			i++;
		}
		System.out.println("===========================================666666");
		
		CustomArrayList<Integer> test3 = new CustomArrayList<Integer>();
		test3.mergeSort(test2, test1);
		i = 0;
		while (i < test3.length) {
			System.out.println("test3 " + i + " >> " + test3.getFrom(i));
			i++;
		}
		System.out.println("===========================================7777777");
		
		index = test3.binarySearchUnique(4999);
		System.out.println(test3.getFrom(index));
		System.out.println("===========================================88888888");
		test3.modify(6, 201);
		test3.modify(7, 201);
		
		Integer[] indexes2 = new Integer[test3.length];
		Integer[] count2 = new Integer[1];
		test3.binarySearchMultiples(201, indexes2, count2);
		i = 0;
		while (i < count2[0]) {
			System.out.println("test3 " + indexes2[i] + " >> " + test3.getFrom(indexes2[i]));
			i++;
		}
		System.out.println("===========================================9999999999");
	}
}
