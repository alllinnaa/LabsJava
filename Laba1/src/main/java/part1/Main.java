package part1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        System.out.println("Enter the numbers:");
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        Numbers numbersObj = new Numbers();
        int evenCount = numbersObj.countEvenNumbers(numbers);
        int numbersWithEqualEvenOddDigits = numbersObj.countNumbersWithEqualEvenOddDigits(numbers);

        System.out.println("Number of even numbers: " + evenCount);
        System.out.println("Number of numbers with equal even and odd digits: " + numbersWithEqualEvenOddDigits);
    }
}
