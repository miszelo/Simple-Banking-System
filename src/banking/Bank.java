package banking;


public class Bank {
    private final DatabaseQuery query;

    public Bank(DatabaseQuery query) {
        this.query = query;
    }


    public Card createCard() {
        String cardNumber = CardGenerator.generateCardNumber();
        String PIN = CardGenerator.generateRandomPin();

        OutputPrinter.printCardCreatedMessage();
        OutputPrinter.printCardInfo(cardNumber, PIN);

        return new Card(cardNumber, PIN);
    }

    public String login() {
        OutputPrinter.printEnterCardNumberMessage();
        String cardNumber = InputReader.readCardNumber();

        OutputPrinter.printEnterPINMessage();
        String PIN = InputReader.readPIN();

        if (query.isPossibleToLogin(cardNumber, PIN)) {
            OutputPrinter.printSuccessfulLogin();
            return cardNumber;
        }
        OutputPrinter.printUnsuccessfulLogin();
        return "";
    }
}
