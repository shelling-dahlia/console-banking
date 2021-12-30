import bank_data.BankManager;
import ultility.MyMenu;

import java.io.IOException;

import static ultility.MyScanner.getString;

public class SecretBankExecutor {

    private static BankManager bankManager;

    static {
        try {
            bankManager = new BankManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        goToLoginMenu();
    }

    public static void goToLoginMenu() throws Exception {

        MyMenu loginMenu = new MyMenu("THE SECRET BANK");
        loginMenu.add("Create new account");
        loginMenu.add("Login");
        loginMenu.add("Quit");

        while (true) {
            loginMenu.showMenu();
            int userchoice = loginMenu.getUserChoice();
            switch (userchoice) {
                case 1 : {
                    notify("Create new account");
                    bankManager.createAccount();
                    break;
                }

                case 2 : {
                    notify("Login");
                    if (bankManager.login()) {
                        System.out.println("Login successfully !!!");
                        goToMainMenu();
                    }
                    break;
                }
                case 3 : {
                    bankManager.saveData();
                    System.exit(0);
                    return;
                }
            }
        }
    }

    public static void goToMainMenu() throws Exception {


        MyMenu mainMenu = new MyMenu("Main menu");
        mainMenu.add("Withdraw money");
        mainMenu.add("Deposit money");
        mainMenu.add("Transfer money");
        mainMenu.add("Remove account");
        mainMenu.add("Show account information");
        mainMenu.add("Back");

        do {
            mainMenu.showMenu();
            int userChoice = mainMenu.getUserChoice();
            switch (userChoice) {
                case 1 : {
                    notify("Withdraw money");
                    bankManager.withdrawMoney();
                    break;
                }

                case 2 : {
                    notify("Deposit money");
                    bankManager.depositMoney();
                    break;
                }

                case 3 : {
                    notify("Transfer money");
                    bankManager.transferMoney();
                    break;
                }

                case 4 : {
                    notify("Remove account");
                    boolean removeSuccess = bankManager.removeAccount();
                    if (removeSuccess) {
                        goToLoginMenu();
                    }
                    break;
                }

                case 5 : {
                    notify("Show current account information");
                    bankManager.showCurrentAccountInformation();
                    break;
                }

                case 6 : {
                    String logOut = getString("You want to quit ? (Y / N): ", "Y", "N", "y", "n");

                    if (logOut.equalsIgnoreCase("y")) {
                        bankManager.logOut();
                        goToLoginMenu();
                        return;

                    } else {
                        break;
                    }
                }
            }

        } while (true);
    }

    public static void notify(String message) {
        System.out.println();
        System.out.println("===[ " + message + " ]===");
    }
}
