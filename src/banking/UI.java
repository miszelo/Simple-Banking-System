package banking;

import java.sql.SQLException;

public class UI {
    private final DatabaseQuery query;
    private final Bank bank;

    public UI(String url) {
        Database db = new Database(url);
        this.query = new DatabaseQuery(db);
        this.bank = new Bank(query);
    }

    public void showMenu() {
        while (true) {
            OutputPrinter.printWelcomeMenu();
            int choice = InputReader.readUserChoice();
            switch (choice) {
                case 0:
                    OutputPrinter.printByeMessage();
                    return;
                case 1:
                    createAccount();
                    break;
                case 2:
                    loginIntoAccount();
                    break;
                default:
                    OutputPrinter.printErrorMessage();
                    break;
            }
        }
    }

    private void loginMenu(String cardNumber) {
        while (true) {
            OutputPrinter.printLoginMenu();
            int choice = InputReader.readUserChoice();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    checkBalance(cardNumber);
                    break;
                case 2:
                    addIncome(cardNumber);
                    break;
                case 3:
                    doTransfer(cardNumber);
                    break;
                case 4:
                    closeAccount(cardNumber);
                    return;
                case 5:
                    logOut();
                    return;
                default:
                    OutputPrinter.printErrorMessage();
                    break;
            }
        }
    }

    private void logOut() {
        OutputPrinter.printSuccessfulLogout();
    }

    private void loginIntoAccount() {
        String cardNumber = bank.login();
        try {
            if (query.getCardNumber(cardNumber)) {
                loginMenu(cardNumber);
            }
        } catch (SQLException e) {
            OutputPrinter.printExceptionMsg();
        }
    }

    private void checkBalance(String cardNumber) {
        try {
            OutputPrinter.printBalanceMessage(query.getBalance(cardNumber));
        } catch (SQLException e) {
            OutputPrinter.printExceptionMsg();
        }
    }

    private void addIncome(String cardNumber) {
        OutputPrinter.printEnterIncomeMessage();
        try {
            query.addIncome(cardNumber, InputReader.readIncome());
            OutputPrinter.printIncomeAdd();
        } catch (Exception e) {
            OutputPrinter.printExceptionMsg();
        }
    }

    private void doTransfer(String cardNumber) {
        OutputPrinter.printTransferToCardMessage();
        String cardNumberToTransfer = InputReader.readCardNumberToTransfer();
        try {
            if (TransferValidator.PossibilityToTransfer(cardNumber, cardNumberToTransfer)) {
                OutputPrinter.printTransferValueMessage();
                query.doTransfer(cardNumber, cardNumberToTransfer, InputReader.readTransferValue());
            }
        } catch (SQLException e) {
            OutputPrinter.printExceptionMsg();
        }
    }

    private void closeAccount(String cardNumber) {
        OutputPrinter.printAccountClosedMessage();
        try {
            query.closeAccount(cardNumber);
        } catch (SQLException e) {
            OutputPrinter.printExceptionMsg();
        }
    }

    private void createAccount() {
        Card card = bank.createCard();
        query.insert(card);
    }
}