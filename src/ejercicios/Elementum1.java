package ejercicios;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Elementum1 {

	/*
	 * Implement a class with the following interface void add(int i); boolean
	 * sum(int i)
	 * 
	 *  add(3);
	 *  add(5); 
	 *  add(6); 
	 *  sum(3); <- false 
	 *  sum(7); <- false 
	 *  sum(8); <- true
	 */

	// Solución O(n^2) el cálculo de la suma, O(1) el add, O(n) espacio
	private List<Integer> list1 = new LinkedList<>();

	public void add1(int num) {
		list1.add(num);
	}

	public boolean sum1(int sum) {
		for (Integer integer1 : list1) {
			for (Integer integer2 : list1) {
				if (integer1 != integer2) {
					if (integer1 + integer2 == sum) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// Y si quiero que el sum sea en O(1)? Qué garcha haces?

	//Agregás un HashMap e insertás en el la suma del elemento actual con cada uno de los otros en la lista
	
	private List<Integer> list = new LinkedList<>();
	private Map<Integer, Integer> map = new HashMap<>();
	
	public void add(int num) {
		list.add(num);
		for (Integer existingNum : list) {
			if(existingNum != num) {
				map.put(existingNum + num, null);
			}
		}
	}
	
	public void sum(int sum) {
		map.containsKey(sum);
	}

}
