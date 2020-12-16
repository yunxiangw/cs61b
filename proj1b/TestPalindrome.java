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
    public void testIsPalindromeZero() {
        assertTrue(palindrome.isPalindrome(" "));
    }

    @Test
    public void testIsPalindromeOne() {
        assertTrue(palindrome.isPalindrome("k"));
    }

    @Test
    public void testIsPalindromeLower() {
        assertTrue(palindrome.isPalindrome("mom"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertFalse(palindrome.isPalindrome("car"));
        assertFalse(palindrome.isPalindrome("door"));
    }

    @Test
    public void testIsPalindromeUpper() {
        assertTrue(palindrome.isPalindrome("MOM"));
        assertTrue(palindrome.isPalindrome("NOON"));
        assertFalse(palindrome.isPalindrome("CAR"));
        assertFalse(palindrome.isPalindrome("DOOR"));
    }

    @Test
    public void testIsPalindromeMixed() {
        assertFalse(palindrome.isPalindrome("MOm"));
        assertFalse(palindrome.isPalindrome("NoOn"));
        assertFalse(palindrome.isPalindrome("CAr"));
        assertFalse(palindrome.isPalindrome("DOOr"));
    }

    @Test
    public void testIsPalindromeSymbol() {
        assertTrue(palindrome.isPalindrome("%&%"));
        assertTrue(palindrome.isPalindrome("^&a&^"));
        assertFalse(palindrome.isPalindrome("ca!"));
        assertFalse(palindrome.isPalindrome("goo^"));
    }

    @Test
    public void testIsPalindromeOffByOneZero() {
        OffByOne cc = new OffByOne();
        assertTrue(palindrome.isPalindrome(" ", cc));
    }

    @Test
    public void testIsPalindromeOffByOneOne() {
        OffByOne cc = new OffByOne();

        assertTrue(palindrome.isPalindrome("t", cc));
    }

    @Test
    public void testIsPalindromeOffByOneLower() {
        OffByOne cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("ab", cc));
        assertTrue(palindrome.isPalindrome("flake", cc));
        assertFalse(palindrome.isPalindrome("seat", cc));
        assertFalse(palindrome.isPalindrome("monday", cc));
    }

    @Test
    public void testIsPalindromeOffByOneUpper() {
        OffByOne cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("AB", cc));
        assertTrue(palindrome.isPalindrome("FLAKE", cc));
        assertFalse(palindrome.isPalindrome("SEAT", cc));
        assertFalse(palindrome.isPalindrome("MONDAY", cc));
    }

    @Test
    public void testIsPalindromeOffByOneMixed() {
        OffByOne cc = new OffByOne();
        assertFalse(palindrome.isPalindrome("Ab", cc));
        assertFalse(palindrome.isPalindrome("FlAke", cc));
        assertFalse(palindrome.isPalindrome("SeAt", cc));
        assertFalse(palindrome.isPalindrome("moNDAY", cc));
    }

    @Test
    public void testIsPalindromeOffByOneSymbol() {
        OffByOne cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("%&", cc));
        assertTrue(palindrome.isPalindrome("%^&", cc));
        assertFalse(palindrome.isPalindrome("%(", cc));
        assertFalse(palindrome.isPalindrome("%%**", cc));
    }
}
