package ejercicios;

public class Palindromes {

	/**
	 * Implementacion simple de isPalindrome, recorriendo ambos extremos
	 **/
	public static boolean isPalindromeAllInOne(String phrase) {
		int firstIndex = 0;
		int lastIndex = phrase.length() - 1;
		while (firstIndex < lastIndex) {
			while(!Character.isAlphabetic(phrase.charAt(firstIndex))) {
				firstIndex++;
			}
			while(!Character.isAlphabetic(phrase.charAt(lastIndex))) {
				lastIndex--;
			}
			System.out.println(Character.toLowerCase(phrase.charAt(firstIndex)) + " == " + phrase.charAt(lastIndex) + "?");
			if (Character.toLowerCase(phrase.charAt(firstIndex)) != Character.toLowerCase(phrase.charAt(lastIndex))) {
				return false;
			}
			firstIndex++;
			lastIndex--;
		}
		return true;
	}
	
	/**
	 * isPalindrome usando java libs
	 **/
	public static boolean isPalindromeEasy(String phrase) {
		return phrase.toLowerCase().equals(new StringBuffer(phrase.toLowerCase()).reverse().toString());
	}
	
	public static boolean isPalindromeRecursive(String phrase) {
		if (phrase.length() == 0 || phrase.length() == 1) {
			return true;
		} else if (Character.toLowerCase(phrase.charAt(0)) == Character.toLowerCase(phrase.charAt(phrase.length() - 1))) {
			return isPalindromeRecursive(phrase.substring(1, phrase.length() - 1));
		} else {
			return false;
		}
	}
}

