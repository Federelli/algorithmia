package ejercicios;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Search {

	@Test
	public void findSortedNotUsingLengthBrute() {
		// O(n)
		int wantedNumber = 5;
		Integer[] nums = { 1, 5, 9, 12, 21 };
		int index = 0;
		boolean found = false;
		try {
			while (true) {
				if (nums[index] == wantedNumber) {
					found = true;
					break;
				}
				index++;
			}
			assertTrue(found);
		} catch (Exception e) {
			return;
		}
	}

	// @Test
	// public void findSortedNotUsingLengthSmart() {
	// int wantedNumber = 40;
	// Integer[] nums = { 1, 5, 9, 12, 21, 27, 32, 40, 53, 60, 72 };
	// boolean found = false;
	// // caso 0 y 1
	// try {
	// if (nums[0] == wantedNumber || nums[1] == wantedNumber) {
	// found = true;
	// return;
	// }
	// } catch (Exception e) {
	// // not found
	// return;
	// }
	// // resto
	// int step = 2;
	// int previousStep = step;
	// int bound = Integer.MAX_VALUE;
	// while (true) {
	// try {
	// if (nums[step] == wantedNumber) {
	// found = true;
	// break;
	// } else {
	// if (wantedNumber > nums[step]) {
	// previousStep = step;
	// if (step <= bound) {
	// step *= 2;
	// } else {
	// step = (step + bound) / 2;
	// }
	// bound = step;
	// } else {
	// bound = step;
	// step = (int) Math.floor((step + previousStep) / 2);
	// }
	// }
	// } catch (Exception e) {
	// // return;
	// }
	// }
	// assertTrue(found);
	// }

	@Test
	public void findSortedNotUsingLengthSmart2() {
		int wantedNumber = 60;
		Integer[] nums = { 1, 5, 9, 12, 21, 27, 32, 40, 53, 60, 72 };
		boolean found = false;
		// caso 0 y 1
		try {
			if (nums[0] == wantedNumber) {
				found = true;
				return;
			}
		} catch (Exception e) {
			// not found
			return;
		}
		int initialStep = 1;
		int initialIndex = 0;
		found = findSortedWithIndex(wantedNumber, nums, initialStep, initialIndex);
		assertTrue(found);
	}

	private boolean findSortedWithIndex(int wantedNumber, Integer[] nums, int step, int index) {
		try {
			int currentNumber = nums[index + step];
			if (currentNumber == wantedNumber) {
				return true;
			} else {
				while (wantedNumber >= currentNumber) {
					if (currentNumber == wantedNumber) {
						return true;
					}
					step *= 2;
					try {
						currentNumber = nums[index + step];
					} catch (Exception e) {
						return findSortedWithIndex(wantedNumber, nums, 1, (step / 2) - 1);
					}
				}
				return findSortedWithIndex(wantedNumber, nums, 1, (step / 2) - 1);
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Test
	public void findIfSumOfTwoExistsBrute() {
		// O(n^2)
		int wantedNumber = 5;
		Integer[] nums = { 8, 6, 3, 7, 2 };
		boolean sumUp = false;
		for (Integer num1 : nums) {
			for (Integer num2 : nums) {
				if (num1 != num2 && num1 + num2 == wantedNumber) {
					sumUp = true;
					return;
				}
			}
		}
		assertTrue(sumUp);
	}

	@Test
	public void findIfSumOfTwoExistsHash() {
		// O(n)
		int wantedNumber = 5;
		Integer[] nums = { 8, 6, 3, 7, 2 };
		boolean sumUp = false;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
		}
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(wantedNumber - nums[i])) {
				sumUp = true;
				return;
			}
		}
		assertTrue(sumUp);
	}

	// Se puede hacer en O(1) el canBeObtained()?

	private Integer[] nums = { 8, 6, 3, 7, 2 };
	private int wantedNumber = 5;

	public Map<Integer, Integer> hashize() {
		// O(n^2)
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
		// O(1)
		assertTrue(hashize().containsKey(wantedNumber));
	}
}
