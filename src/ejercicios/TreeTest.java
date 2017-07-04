package ejercicios;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TreeTest {

	@Test
	public void buildTree() {
		Tree<Integer> tree = new Tree<>();
		tree.addNode(new Node<Integer>(5));
		tree.addNode(new Node<Integer>(6));
		tree.addNode(new Node<Integer>(4));
		System.out.println(tree.root().data());
		assertTrue(true);
	}

}
