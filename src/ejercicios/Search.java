package ejercicios;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Search {

	@Test
	public void findSortedNotUsingLength() {
		//O(n)
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
	public void findIfSumOfTwoExistsBrute() {
		//O(n^2)
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
	
	
	
	@Test
	public void findIfSumOfTwoExistsHash() {
		//O(n)
		int number = 5;
		Integer[] nums = {8, 6, 3, 7, 2};
		boolean sumUp = false;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length ;i++) {
			map.put(nums[i], i);
		}
		for (int i = 0; i < nums.length; i++) {
			if(map.containsKey(number - nums[i])) {
				sumUp = true;
				return;
			}
		}
		assertTrue(sumUp);
	}
	
	//Se puede hacer en O(1) el canBeObtained()?
	
	private Integer[] nums = {8, 6, 3, 7, 2};
	private int number = 5;
	
	public Map<Integer, Integer> hashize() {
		//O(n^2)
		Map<Integer, Integer> map = new HashMap<>();
		for (Integer integer1 : nums) {
			for (Integer integer2 : nums) {
				if (integer1 != integer2) {
					map.put(integer1 + integer2, 0);
				}
			}
		}
		return map;
	}
	
	@Test
	public void canBeObtained() {
		//O(1)
		assertTrue(hashize().containsKey(number));
	}
}
