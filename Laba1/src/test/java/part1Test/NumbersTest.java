package part1Test;

import org.junit.Test;
import part1.Numbers;

import static org.junit.Assert.*;

public class NumbersTest {
    private Numbers evenNumbers = new Numbers();

    @Test
    public void testCountEvenNumbers() {
        int[] numbers = {1, 2, 3, 4, 5, 6};
        assertEquals(3, evenNumbers.countEvenNumbers(numbers));
    }

    @Test
    public void testHasEqualEvenOddDigits() {
        assertTrue(evenNumbers.hasEqualEvenOddDigits(23));
        assertFalse(evenNumbers.hasEqualEvenOddDigits(2468));
    }

    @Test
    public void testCountNumbersWithEqualEvenOddDigits() {
        int[] numbers = {12, 24, 36, 122, 246, 134, 2158};
        assertEquals(3, evenNumbers.countNumbersWithEqualEvenOddDigits(numbers));
    }
}