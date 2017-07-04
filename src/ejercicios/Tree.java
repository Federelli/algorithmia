package ejercicios;

public class Tree<T extends Comparable<T>> {

	private Node<T> root;

	public void addNode(Node<T> node) {
		if (root == null) {
			root = node;
		} else {
			if (root.data().compareTo(node.data()) > 0) {
				root.addNode(root.right());
			} else {
				root.addNode(root.left());
			}
		}
	}

	public Node<T> root() {
		return root;
	}
}
