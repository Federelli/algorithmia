package ejercicios;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Search {

	@Test
	public void findSortedNotUsingLength() {
		int number = 5;
		Integer[] nums = { 1, 5, 9, 12, 21 };
		int index = 0;
		boolean found = false;
		while (nums[index] != null) {
			if(nums[index] == number) {
				found = true;
				return;
			} 
			index++;
		}
		assertTrue(found);
	}
	
	@Test
	public void findIfSumOfTwoExist() {
		int number = 5;
		Integer[] nums = {8, 6, 3, 7, 2};
		boolean sumUp = false;
		for (Integer num1 : nums) {
			for (Integer num2 : nums) {
				if(num1 + num2 == number) {
					sumUp = true;
					return;
				}
			}
		}
		assertTrue(sumUp);
	}
}
