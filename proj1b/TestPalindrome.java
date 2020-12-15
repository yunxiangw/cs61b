import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome(" "));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("%&*&%"));
        assertTrue(palindrome.isPalindrome("Aaa"));

        OffByOne comparator = new OffByOne();

        assertTrue(palindrome.isPalindrome("flake", comparator));
        assertTrue(palindrome.isPalindrome("fae", comparator));
        assertTrue(palindrome.isPalindrome("&a%", comparator));
        assertFalse(palindrome.isPalindrome("good", comparator));

    }
}
