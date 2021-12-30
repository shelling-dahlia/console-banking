package bank_data;

import ultility.Validation;

import java.io.IOException;

import static ultility.MyScanner.getDouble;
import static ultility.MyScanner.getString;

public class BankManager {

    private AccountListManager accountsManager = new AccountListManager();

    public BankManager() throws IOException {}

    public void createAccount () {

        String id;

        do {
            id = getString("Input id: ");
            if (!accountsManager.isAccount_ID_Legal(id)) {
                System.out.println("This id has existed. Please cho another one !!!");
            }

        } while (!accountsManager.isAccount_ID_Legal(id) || !Validation.validateID(id));

        String accountName = getString("Input name: ").toUpperCase();

        String password;
        do {
            password = getString("Input password: ");
        } while (! Validation.validatePassword(password));

        String confirmPassword;

        do {
            confirmPassword = getString("Input confirm password: ");
            if (! (password.equals(confirmPassword))) {
                System.out.println("2 passwords are not matched. Please check again");
            }
        } while (!password.equals(confirmPassword));

        Account newAccount = new Account(id, accountName, password, 0);
        accountsManager.addAccount(newAccount);
        System.out.println("Create new account successfully !!! Please login to use bank functions.");

    }

    public boolean login() throws Exception {
        String id;
        boolean accountExist;
        do {

            id = getString("Input id: ");

            accountExist = accountsManager.checkAccountExist(id);
            if ( ! accountExist) {
                String keepGoing = getString("Account does not exist. Re-input ? ( Y / N ): \n", "Y", "N", "y", "n");
                if (keepGoing.equalsIgnoreCase("Y")) {
                    continue;
                } else if (keepGoing.equalsIgnoreCase("N")){
                    return false;
                }
            }

        } while ( ! accountExist);


        String password;
        Account matchedAccount;
        do {
            password = getString("Input password: ");
            matchedAccount = accountsManager.getAccount(id, password);
            if (matchedAccount != null) {
                LogedInAccount.setInstance(matchedAccount);
                return true;
            } else {
                String keepGoing = getString("Wrong password. Re-input ? (Y / N): \n", "Y", "N", "y", "n");
                if (keepGoing.equalsIgnoreCase("Y")) {
                    continue;
                } else if (keepGoing.equalsIgnoreCase("N")){
                    return false;
                }
            }
        } while (matchedAccount == null);

        return false;
    }

    public void withdrawMoney () throws Exception {
        double currentBalance = LogedInAccount.getInstance().getBalance();

        if (currentBalance == 0) {
            System.out.println("Your current balance is 0. You can not withdraw money");
            return;
        }

        System.out.println("Your current balance: " + currentBalance + " vnd");
        while (true) {
            System.out.print("Input withdraw money: ");
            double withdrawalMoney = getDouble(false);

            if (withdrawalMoney > currentBalance) {
                System.out.println("You can only withdraw up to " + currentBalance + " vnd");
            } else {
                String password = getString("Input password: ");
                if (LogedInAccount.getInstance().withdraw(password, withdrawalMoney)) {
                    System.out.println("Withdraw " + withdrawalMoney + " successfully. Current balance " + LogedInAccount.getInstance().getBalance() + " vnd");
                }
                return;
            }

        }
    }

    public void depositMoney () throws Exception {
        double currentBalance = LogedInAccount.getInstance().getBalance();
        System.out.println("Your current balance: " + currentBalance + " vnd");
        while (true) {
            System.out.print("Input deposit money: ");
            double depositMoney = getDouble(false);
            String confirmation = getString("You want to deposit " + depositMoney + " ? (Y / N): ", "Y", "y", "N", "n");
            if (confirmation.equalsIgnoreCase("N")) {
                return;
            }

            String password = getString("Input password: ");
            if (LogedInAccount.getInstance().deposit(password, depositMoney)) {
                System.out.println("Withdraw " + depositMoney + " successfully. Current balance " + LogedInAccount.getInstance().getBalance() + " vnd");
            }
            return;
        }
    }

    public void logOut() throws Exception {

        LogedInAccount.setInstance(null);

    }

    public void saveData() {
        try {
            accountsManager.saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCurrentAccountInformation() throws Exception {
        System.out.println("======== Account information ========");
        System.out.println("- Id : " + LogedInAccount.getInstance().getId());
        System.out.println("- Balance : " + LogedInAccount.getInstance().getBalance() + " vnd");
        System.out.println("- Name : " + LogedInAccount.getInstance().getName());
        System.out.println("===============================");
    }

    public void transferMoney() throws Exception {

        String moneyReceiverID = getString("Input receiver id: ");
        if (!accountsManager.checkAccountExist(moneyReceiverID)) {
            System.out.println("Receiver does not exist");
            return;
        }
        System.out.println("Receiver's name: " + accountsManager.getAccountName(moneyReceiverID));
        System.out.println("Your current balance: " + LogedInAccount.getInstance().getBalance());

        System.out.print("Input money amount you want to transfer: ");
        double moneyAmount = getDouble(false);

        if (moneyAmount > LogedInAccount.getInstance().getBalance()) {
            System.out.println("You can not transfer money amount greater than your balance. Exit !!!");
            return;
        }

        String password = getString("Input password: ");
        if (LogedInAccount.getInstance().withdraw(password, moneyAmount)) {
            accountsManager.transferMoney(moneyReceiverID, moneyAmount);
            System.out.println("Transfer " + moneyAmount + " vnd to " + accountsManager.getAccountName(moneyReceiverID) + "\'s account successfully") ;
        } else {
            System.out.println("Transfer money failed !!!");
        }
    }

    public boolean removeAccount() throws Exception {
        System.out.println("====== Remove account confirmation");
        showCurrentAccountInformation();
        String confirmation = getString("You really want to remove this account ? (YES / NO): ", "YES", "NO");
        if (confirmation.equals("YES")) {
            accountsManager.removeAccount(LogedInAccount.getInstance());
            System.out.println("Remove account successfully !!!");
            logOut();
            return true;
        } else {
            System.out.println("Cancel removing accoung !!!");
            return false;
        }
    }
}
