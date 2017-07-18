package ejercicios;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class StringsTests {

	/*
	 * Falla si es no pal�ndromo
	 */
	@Test
	public void notAPalindromeTest() {
		String nonPalindromeWord = "hola";
		assertFalse(Strings.isPalindromeAllInOne(nonPalindromeWord));
	}

	/*
	 * Prueba si es palindromo una palabra de longitud par
	 */
	@Test
	public void palindromeEvenTest() {
		String evenPalindrome = "aaaa";
		assertTrue(Strings.isPalindromeAllInOne(evenPalindrome));
	}

	/*
	 * Prueba si es palindromo un palínbdromo sin tener encuenta mayúsculas
	 */
	@Test
	public void isCaseInsensitiveTest() {
		String palindrome = "Neuquen";
		assertTrue(Strings.isPalindromeAllInOne(palindrome));
	}

	/*
	 * Prueba si es palindromo un palínbdromo sin tener encuenta caracteres no
	 * alfabéticos
	 */
	@Test
	public void isSpaceInsensitiveTest() {
		String palindrome = "anita lava la tina";
		assertTrue(Strings.isPalindromeAllInOne(palindrome));
	}

	/*
	 * Prueba si es palindromo utilizando el reverse de StringBuffer
	 */
	@Test
	public void isPalindromeEasytest() {
		String palindrome = "Neuquen";
		assertTrue(Strings.isPalindromeEasy(palindrome));
	}

	/*
	 * Prueba si es palindromo un palínbdromo sin tener encuenta caracteres no
	 * alfabéticos
	 */
	@Test
	public void isNonAlphabeticInsensitiveTest() {
		String specialCharsPhrase = "madam, i'm adam";
		assertTrue(Strings.isPalindromeAllInOne(specialCharsPhrase));
	}

	/*
	 * Prueba si es palindromo revursivmamente
	 */

	@Test
	public void recursiveIsPalindromeTest() {
		String palindrome = "neuquen";
		assertTrue(Strings.isPalindromeRecursive(palindrome));
	}

	@Test
	public void removeDupestest() {
		String duped = "neuquen";
		String unDuped = Strings.removeDuplicates(duped);
		boolean hasDupes = false;
		Set<Character> set = new HashSet<>();
		for (int i = 0; i < unDuped.length() - 1; i++) {
			char c = unDuped.charAt(i);
			if (set.contains(unDuped.charAt(i))) {
				hasDupes = true;
			} else {
				set.add(c);
			}
		}
		assertFalse(hasDupes);
	}
}
