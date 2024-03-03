package part1;
import java.util.Scanner;
public class Numbers {
    public static int countEvenNumbers(int[] numbers) {
        int count = 0;
        for (int num : numbers) {
            if (num % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    public static boolean hasEqualEvenOddDigits(int number) {
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

    public static int countNumbersWithEqualEvenOddDigits(int[] numbers) {
        int count = 0;
        for (int num : numbers) {
            if (hasEqualEvenOddDigits(num)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        System.out.println("Enter the numbers:");
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        int evenCount = countEvenNumbers(numbers);
        int numbersWithEqualEvenOddDigits = countNumbersWithEqualEvenOddDigits(numbers);

        System.out.println("Number of even numbers: " + evenCount);
        System.out.println("Number of numbers with equal even and odd digits: " + numbersWithEqualEvenOddDigits);

        scanner.close();
    }
}
