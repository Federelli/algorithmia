package ejercicios;

public class Palindromes {

	/**
	 * Implementacion simple de isPalindrome, recorriendo ambos extremos
	 **/
	public static boolean isPalindromeSimple(String phrase) {
		int firstIndex = 0;
		int lastIndex = phrase.length() - 1;
		while (firstIndex < lastIndex) {
			System.out.println(phrase.charAt(firstIndex) + " == " + phrase.charAt(lastIndex) + "?");
			if (phrase.charAt(firstIndex) != phrase.charAt(lastIndex)) {
				return false;
			}
			firstIndex++;
			lastIndex--;
		}
		return true;
	}
	
	/**
	 * Palindrome pero case insensitive
	 **/
	public static boolean isPalindromeCaseInsensitive(String phrase) {
		return isPalindromeSimple(phrase.toLowerCase());
	}

	/**
	 * En una pasada eliminar cualquier caracter que no sea alfabetico y dice si es palindromo.
	 **/
	public static boolean isPalindromeIgnoreNonAlphabetic(String phrase) {
		for (int index = 0; index < phrase.length(); index++) {
			if (!Character.isAlphabetic(phrase.charAt(index))) {
				phrase = phrase.replaceAll(String.valueOf(phrase.charAt(index)), "");
			}
		}
		System.out.println(phrase);
		return isPalindromeCaseInsensitive(phrase);
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
		} else if (phrase.charAt(0) == phrase.charAt(phrase.length() - 1)) {
			return isPalindromeRecursive(phrase.substring(1, phrase.length() - 1));
		} else {
			return false;
		}
	}
}

