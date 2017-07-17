package ejercicios;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import junit.framework.AssertionFailedError;

public class TreeTest {

	@Test
	public void buildTree() {
		Tree<Integer> tree = new Tree<>();
		tree.add(5);
		tree.add(6);
		tree.add(4);
		assertTrue(true);
	}

	@Test
	public void heightTest() {
		Tree<Integer> tree = new Tree<>();
		tree.add(6);
		tree.add(5);
		tree.add(4);
		tree.add(3);
		assertTrue(tree.height() == 3);
	}
	
	@Test
	public void isNotBalancedTest() {
		Tree<Integer> tree = new Tree<>();
		tree.add(6);
		tree.add(5);
		tree.add(4);
		tree.add(3);
		assertFalse(tree.isBalanced());
	}
	
	@Test
	public void isBalancedTest() {
		Tree<Integer> tree = new Tree<>();
		tree.add(5);
		tree.add(6);
		tree.add(4);
		tree.add(3);
		assertTrue(tree.isBalanced());
	}
}
