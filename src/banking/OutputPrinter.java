package banking;

public class OutputPrinter {

    public static void printWelcomeMenu() {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
    }

    public static void printLoginMenu() {
        System.out.println("1. Balance");
        System.out.println("2. Add income");
        System.out.println("3. Do transfer");
        System.out.println("4. Close account");
        System.out.println("5. Log out");
        System.out.println("0. Exit");
    }

    public static void printEnterIncomeMessage() {
        System.out.println("\nEnter Income: ");
    }

    public static void printTransferValueMessage() {
        System.out.println("\nEnter how much money you want to transfer: ");
    }

    public static void printMistakeInCardNumber() {
        System.out.println("Probably you made a mistake in the card number. Please try again!\n");
    }

    public static void printCanNotTransfer() {
        System.out.println("You can't transfer money to the same account!\n");
    }

    public static void printNotExistingCard() {
        System.out.println("Such a card does not exist.\n");
    }

    public static void printBalanceMessage(double balance) {
        System.out.println("\nBalance: " + balance + "\n");
    }

    public static void printTransferToCardMessage() {
        System.out.println("\nTransfer");
        System.out.println("Enter card number: ");
    }

    public static void printErrorMessage() {
        System.out.println("\nWrong number. You should enter a number from digits listed above!\n");
    }

    public static void printWrongValueMessage() {
        System.out.println("\nYou should enter a number!\n");
    }

    public static void printCardInfo(String cardNumber, String pin) {
        System.out.println("Your card number:\n" + cardNumber);
        System.out.println("Your card PIN:\n" + pin + "\n");
    }

    public static void printAccountClosedMessage() {
        System.out.println("\nThe account has been closed!\n");
    }

    public static void printCardCreatedMessage() {
        System.out.println("\nYour card has been created");
    }

    public static void printEnterCardNumberMessage() {
        System.out.println("\nEnter your card number:");
    }

    public static void printEnterPINMessage() {
        System.out.println("Enter your PIN:");
    }

    public static void printSuccessfulLogin() {
        System.out.println("\nYou have successfully logged in!\n");
    }

    public static void printUnsuccessfulLogin() {
        System.out.println("\nCard number or PIN is not correct\n");
    }


    public static void printByeMessage() {
        System.out.println("\nBye!");
    }

    public static void printSuccessfulLogout() {
        System.out.println("\nYou have successfully log out!\n");
    }

    public static void printSuccessfulTransfer() {
        System.out.println("Success!\n");
    }

    public static void printIncomeAdd() {
        System.out.println("Income was added!\n");
    }

    public static void printExceptionMsg() {
        System.out.println("Something gone wrong.");
    }
}

