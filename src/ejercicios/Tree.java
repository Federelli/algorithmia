package ejercicios;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

	public boolean bfsSearch(T obj) {
		return bfsSearch(obj, root);
	}

	private boolean bfsSearch(T obj, Node<T> root) {
		Queue<Node<T>> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node<T> current = queue.poll();
			if (current.data().equals(obj)) {
				return true;
			} else {
				if (current.hasLeft()) {
					queue.add(current.left());
				}
				if (current.hasRight()) {
					queue.add(current.right());
				}
			}
		}
		return false;
	}

	public boolean bfsSearchR(T obj) {
		return bfsSearchR(obj, root);
	}

	private boolean bfsSearchR(T obj, Node<T> node) {
		return false;
	}

	public boolean dfsSearch(T obj) {
		return dfsSearch(obj, root);
	}

	private boolean dfsSearch(T obj, Node<T> root) {
		Stack<Node<T>> stack = new Stack<Node<T>>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node<T> current = stack.pop();
			if (current.data().equals(obj)) {
				return true;
			} else {
				stack.push(current.left());
				stack.push(current.right());
			}
		}
		return false;
	}

	public boolean dfsSearchR(T obj) {
		return dfsSearchR(obj, root);
	}

	private boolean dfsSearchR(T obj, Node<T> node) {
		if (node == null) {
			return false;
		} else {
			return node.data().equals(obj) || dfsSearchR(obj, node.left()) || dfsSearchR(obj, node.right());
		}
	}
}
