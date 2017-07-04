package ejercicios;

public class Node<T extends Comparable<T>> {

	private Node<T> left;
	private Node<T> right;
	private T data;
	
	public Node(T data) {
		this.data = data;
	}
	
	public void addNode(Node<T> node) {
		if (data == null) {
			this.data = node.data;
		}
		if (node.data.compareTo(data) >= 0) {
			left.addNode(node);
		} else {
			right.addNode(node);
		}
	}
	
	public T data() {
		return data;
	}
	
	public Node<T> left() {
		return left;
	}
	
	public Node<T> right() {
		return right;
	}
}
