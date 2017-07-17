package ejercicios;

public class Node<T extends Comparable<T>> {

	private Node<T> left;
	private Node<T> right;
	private T data;
	
	public Node(T data) {
		this.data = data;
	}
	
	public void addNode(Node<T> node) {
		if (node.data.compareTo(data) >= 0) {
			if (right == null) {
				right = node;
			} else {
				right.addNode(node);
			}
		} else {
			if( left == null) {
				left =node;
			} else {
				left.addNode(node);
			}
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
	
	public boolean isLeaf() {
		return left() == null && right() == null;
	}
}
