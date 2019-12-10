package task3;

public class HarshadOrNivenNumber {

	private static final int BASE_10_NUMBER_SYSTEM = 10;

	public static void main(String[] args) {
		long limit = 1000;
		for (int number = 1; number <= limit; number++) {
			if (number % getSumOfDigits(number) == 0) {
				System.out.println(number);
			}
		}
	}

	private static int getSumOfDigits(int number) {
		int sumOfDigits = 0;
		while (number != 0) {
            sumOfDigits += number % BASE_10_NUMBER_SYSTEM;
            number = number / BASE_10_NUMBER_SYSTEM;
        }
		return sumOfDigits;
	}

}
