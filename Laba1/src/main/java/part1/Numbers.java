package part1;
import java.util.Scanner;

public class Numbers {
    public int countEvenNumbers(int[] numbers) {
        int count = 0;
        for (int num : numbers) {
            if (num % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    public boolean hasEqualEvenOddDigits(int number) {
        int evenCount = 0, oddCount = 0;
        while (number != 0) {
            int digit = number % 10;
            if (digit % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
            number /= 10;
        }
        return evenCount == oddCount;
    }

    public int countNumbersWithEqualEvenOddDigits(int[] numbers) {
        int count = 0;
        for (int num : numbers) {
            if (hasEqualEvenOddDigits(num)) {
                count++;
            }
        }
        return count;
    }

}
