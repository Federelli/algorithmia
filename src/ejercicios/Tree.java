package ejercicios;

public class Tree<T extends Comparable<T>> {

	private Node<T> root;

	public void add(T item) {
		Node<T> node = new Node<T>(item);
		if (root == null) {
			root = node;
		} else {
			root.addNode(node);
		}
	}

	public Node<T> root() {
		return root;
	}
	
	public int height() {
		return heightRecursive(root);
	}
	
	private int heightRecursive(Node<T> node) {
		if (node == null || node.isLeaf()) {
			return 0;
		} else {
			return 1 + Math.max(heightRecursive(node.left()), heightRecursive(node.right()));
		}
	}
	
	public boolean isBalanced() {
		return Math.abs(heightRecursive(root.left()) - heightRecursive(root.right())) <= 1;
	}
}
