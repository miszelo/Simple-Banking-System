package banking;

import java.util.Scanner;

public class InputReader {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readUserChoice() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                OutputPrinter.printErrorMessage();
            }
        }
    }

    public static double readIncome() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                OutputPrinter.printWrongValueMessage();
                OutputPrinter.printEnterIncomeMessage();
            }
        }
    }

    public static String readCardNumber() {
        return scanner.nextLine();
    }

    public static String readPIN() {
        return scanner.nextLine();
    }

    public static String readCardNumberToTransfer() {
        return scanner.nextLine();
    }

    public static double readTransferValue() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                OutputPrinter.printWrongValueMessage();
                OutputPrinter.printTransferValueMessage();
            }
        }
    }
}
