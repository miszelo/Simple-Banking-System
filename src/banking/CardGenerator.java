package banking;

import java.util.Random;


public class CardGenerator {
    private static final String BIN = "400000";
    private static final Random random = new Random();

    public static String generateCardNumber() {
        StringBuilder number = new StringBuilder(BIN);
        for (int i = 0; i < 9; i++) {
            number.append(random.nextInt(10));
        }
        String checkSum = String.valueOf(validCheckSum(number.toString()));
        number.append(checkSum);
        return number.toString();
    }

    public static String generateRandomPin() {
        StringBuilder pin = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            pin.append(random.nextInt(10));
        }
        return pin.toString();
    }

    public static int validCheckSum(String cardNumber) {
        int sum = 0;
        for (int i = 0; i < cardNumber.length(); i++) {
            int digit = Integer.parseInt(cardNumber.substring(i, (i + 1)));
            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }
        int mod = sum % 10;
        return ((mod == 0) ? 0 : 10 - mod);
    }

    public static boolean checkIfCardNumberIsValid(String cardNumber) {
        if (cardNumber.length() < 16) {
            return false;
        }
        String cardNumberToCheck = cardNumber.substring(0, 15);
        return validCheckSum(cardNumberToCheck) == Integer.parseInt(String.valueOf(cardNumber.charAt(15)));
    }
}
