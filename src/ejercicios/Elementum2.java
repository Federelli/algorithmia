package ejercicios;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class Elementum2 {

	/*Make a method that prints the tree in level order
	 *      b
	 *    /   \
	 *   a     f
	 *       /   \
	 *      d     h
	 *     / \   / \
	 *    c   e g   i
	 *   
	 *   Result:
	 * b
	 * 
	 * a f
	 * 
	 * d h
	 * 
	 * c e g i
	 * */
	
	//Idea hacer un BFS iterativo
	
	public void print(Tree tree) {
		Node root = tree.root();
		if (root == null) {
			System.out.println("No tree to print");
		} else {
			Queue<Node> queue = new LinkedList<>();
			queue.offer(root);
			while (!queue.isEmpty()) {
				Node currentNode = queue.poll();
				System.out.print(currentNode.data() + " ");
				if(queue.isEmpty()) {
					System.out.println(); //falta resolver donde poner bien el salto de línea, ver solución abajo
				}
				if (currentNode.hasLeft()) {
					queue.offer(currentNode.left());
				}
				if (currentNode.hasRight()) {
					queue.offer(currentNode.right());
				}
			}
		}
	}
	
	//Esta solución resuelve el problema pero a la vez es un poco más dificil de seguir
	
	public void print1(Tree tree) {
		Node root = tree.root();
		if (root == null) {
			System.out.println("No tree to print");
		} else {
			Queue<Node> queue = new LinkedList<>();
			queue.offer(root);
			while (true) {
				int nodeCount = queue.size(); //nodes at current level
				if (nodeCount == 0) {
					break;
				}
				while(nodeCount > 0) {
					Node currentNode = queue.poll();
					System.out.print(currentNode.data() + " ");
					if (currentNode.hasLeft()) {
						queue.offer(currentNode.left());
					}
					if (currentNode.hasRight()) {
						queue.offer(currentNode.right());
					}
					nodeCount--;
				}
				System.out.println();
			}
		}
	}
	
	@Test
	public void printTest() {
		Tree<String> tree = new Tree<>();
		tree.add("b");
		tree.add("a");
		tree.add("f");
		tree.add("d");
		tree.add("h");
		tree.add("c");
		tree.add("e");
		tree.add("g");
		tree.add("i");
		print1(tree);
	}
}
