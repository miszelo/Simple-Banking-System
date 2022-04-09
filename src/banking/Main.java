package banking;

public class Main {

    public static void main(String[] args) {
        //String url = "jdbc:sqlite:" + args[1];
        String url = "jdbc:sqlite:card.s3db";
        UI ui = new UI(url);

        ui.showMenu();
    }
}
