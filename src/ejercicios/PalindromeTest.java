package ejercicios;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PalindromeTest {

	
	/*
	 * Falla si es no palíndromo
	 * */
	@Test
	public void notAPalindromeTest() {
		String nonPalindromeWord = "hola"; 
		assertFalse(Palindromes.isPalindromeSimple(nonPalindromeWord));
	}
	
	/*
	 * Prueba si es palindromo una palabra de longitud par
	 * */
	@Test
	public void palindromeEvenTest() {
		String evenPalindrome = "aaaa";
		assertTrue(Palindromes.isPalindromeSimple(evenPalindrome));
	}
	
	/*
	 * Prueba si es palindromo un palínbdromo sin tener encuenta mayúsculas
	 * */
	@Test
	public void isCaseInsensitiveTest() {
		String palindrome = "Neuquen";
		assertTrue(Palindromes.isPalindromeCaseInsensitive(palindrome));
	}
	
	/*
	 * Prueba si es palindromo un palínbdromo sin tener encuenta caracteres no alfabéticos
	 * */
	@Test
	public void isSpaceInsensitiveTest() {
		String palindrome = "anita lava la tina";
		assertTrue(Palindromes.isPalindromeIgnoreNonAlphabetic(palindrome));
	}
	
	/*
	 * Prueba si es palindromo utilizando el reverse de StringBuffer
	 * */
	@Test
	public void isPalindromeEasytest () {
		String palindrome = "Neuquen";
		assertTrue(Palindromes.isPalindromeEasy(palindrome));
	}
	
	/*
	 * Prueba si es palindromo un palínbdromo sin tener encuenta caracteres no alfabéticos
	 * */
	@Test
	public void isNonAlphabeticInsensitiveTest() {
		String specialCharsPhrase = "madam, i'm adam";
		assertTrue(Palindromes.isPalindromeIgnoreNonAlphabetic(specialCharsPhrase));
	}
	
	/*
	 * Prueba si es palindromo revursivmamente
	 * */
	
	@Test
	public void recursiveIsPalindromeTest() {
		String palindrome = "neuquen";
		assertTrue(Palindromes.isPalindromeRecursive(palindrome));
	}
}
