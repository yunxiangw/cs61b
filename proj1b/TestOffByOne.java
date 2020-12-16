import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    // Your tests go here.
    public void testEqualCharsLower() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('z', 'y'));
        assertFalse(offByOne.equalChars('c', 'k'));
        assertFalse(offByOne.equalChars('g', 't'));
    }

    @Test
    public void testEqualCharsUpper() {
        assertTrue(offByOne.equalChars('A', 'B'));
        assertTrue(offByOne.equalChars('F', 'E'));
        assertFalse(offByOne.equalChars('G', 'P'));
        assertFalse(offByOne.equalChars('V', 'Q'));
    }

    @Test
    public void testEqualCharsMixed() {
        assertFalse(offByOne.equalChars('G', 'f'));
        assertFalse(offByOne.equalChars('t', 'U'));
        assertFalse(offByOne.equalChars('C', 'c'));
        assertFalse(offByOne.equalChars('M', 'a'));
    }

    @Test
    public void testEqualCharsSymbol() {
        assertTrue(offByOne.equalChars('%', '&'));
        assertFalse(offByOne.equalChars('(', '1'));
        assertFalse(offByOne.equalChars('$', 'c'));
        assertFalse(offByOne.equalChars('*', 'B'));
    }
}
