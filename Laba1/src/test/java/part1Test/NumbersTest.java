package part1Test;
import org.junit.Test;
import part1.Numbers;

import static org.junit.Assert.*;
//import part1;

public class NumbersTest {
    private Numbers EvenNumbers;

    @Test
    public void testCountEvenNumbers() {
        int[] numbers = {1, 2, 3, 4, 5, 6};
        assertEquals(3, Numbers.countEvenNumbers(numbers));
    }

    @Test
    public void testHasEqualEvenOddDigits() {
        assertTrue(Numbers.hasEqualEvenOddDigits(23));
        assertFalse(Numbers.hasEqualEvenOddDigits(2468));
    }

    @Test
    public void testCountNumbersWithEqualEvenOddDigits() {
        int[] numbers = {12, 24, 36, 122, 246, 134, 2158};
        assertEquals(3, Numbers.countNumbersWithEqualEvenOddDigits(numbers));
    }
}
