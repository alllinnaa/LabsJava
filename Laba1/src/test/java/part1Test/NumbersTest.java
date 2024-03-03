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
        assertEquals(3, EvenNumbers.countEvenNumbers(numbers));
    }

    @Test
    public void testHasEqualEvenOddDigits() {
        assertTrue(EvenNumbers.hasEqualEvenOddDigits(246));
        assertFalse(EvenNumbers.hasEqualEvenOddDigits(2468));
    }

    @Test
    public void testCountNumbersWithEqualEvenOddDigits() {
        int[] numbers = {12, 24, 36, 123, 246, 135, 2468};
        assertEquals(3, EvenNumbers.countNumbersWithEqualEvenOddDigits(numbers));
    }
}
