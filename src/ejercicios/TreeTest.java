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

	// Given a sorted (increasing order) array, write an algorithm to create a
	// binary tree with minimal height.
	@Test
	public void sortedArrayToBSTTest() {
		// O(n)
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		Tree<Integer> bst = new Tree<>();
		sortedArrayToBST(array, 0, array.length - 1, bst);
		assertTrue(bst.isBalanced());
	}

	private void sortedArrayToBST(int[] array, int start, int end, Tree<Integer> bst) {
		if (start == end) {
			bst.add(array[start]);
			return;
		} else if (start > end) {
			return;
		}
		int middle = (start + end) / 2;
		bst.add(array[middle]);
		sortedArrayToBST(array, start, middle - 1, bst);
		sortedArrayToBST(array, middle + 1, end, bst);
	};
	
	@Test
	public void bfsSearchTest() {
		Tree<Integer> tree = new Tree<>();
		tree.add(4);
		tree.add(2);
		tree.add(1);
		tree.add(5);
		tree.add(3);
		tree.add(6);
		assertTrue(tree.bfsSearch(5));
	}
	
	@Test
	public void bfsSearchRTest() {
		Tree<Integer> tree = new Tree<>();
		tree.add(4);
		tree.add(2);
		tree.add(1);
		tree.add(5);
		tree.add(3);
		tree.add(6);
		assertTrue(tree.bfsSearchR(5));
	}
	
	@Test
	public void dfsSearchTest() {
		Tree<Integer> tree = new Tree<>();
		tree.add(3);
		tree.add(1);
		tree.add(2);
		tree.add(5);
		tree.add(4);
		tree.add(6);
		assertTrue(tree.dfsSearch(6));
	}
}
