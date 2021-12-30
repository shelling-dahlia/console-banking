package bank;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static ultility.PasswordSecure.decode;
import static ultility.PasswordSecure.encode;

public class AccountListManager {

    private ArrayList<Account> accountList = new ArrayList();

    private HashMap<String, String> loginInformationMap = new HashMap<>();

    public AccountListManager () throws IOException {
        loadAccountListFromFile();
        loadLoginInformationFromFile();
        decryptAccountListPassword();
        decryptLoginInformationPassword();
    }

    public Account getAccount (String id, String password) {
        for (Account account : accountList) {
            if (account.getId().equals(id)) {
                if (account.getPassword().equals(password)) {
                    return account;
                }
            }
        }

        return null;
    }

    public void addAccount (Account newAccount) {
        accountList.add(newAccount);
        loginInformationMap.put(newAccount.getId(), newAccount.getPassword());
    }

    public boolean isAccount_ID_Legal(String id) {
        return !checkAccountExist(id);
    }

    public boolean checkAccountExist(String id) {
        return loginInformationMap.containsKey(id);
    }

    public void saveToFile () throws IOException {
        encryptAccountListPassword();
        saveBankAccounts();

        encryptLoginInformationPassword();
        saveUserLoginInfor();
    }

    public void saveBankAccounts () throws IOException {
        if (accountList == null) return;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream("bank.txt");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(accountList);
            System.out.println("Save to file successfully");

        } catch (Exception exception) {
            System.out.println(exception);
            System.out.println("Save to file failed");

        } finally {
            fileOutputStream.close();
            objectOutputStream.close();
        }
    }

    private void encryptAccountListPassword () {
        for (Account account : accountList) {
            account.setPassword(encode(account.getPassword()));
        }
    }

    private void encryptLoginInformationPassword () {
        for (Map.Entry<String, String> pair : loginInformationMap.entrySet()) {
            loginInformationMap.put(pair.getKey(), encode(pair.getValue()));
        }
    }

    private void decryptAccountListPassword () {
        for (Account account : accountList) {
            account.setPassword(decode(account.getPassword()));
        }
    }

    private void decryptLoginInformationPassword () {
        for (Map.Entry<String, String> pair : loginInformationMap.entrySet()) {
            loginInformationMap.put(pair.getKey(), decode(pair.getValue()));
        }
    }



    public void saveUserLoginInfor () throws IOException {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream("user.txt");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(loginInformationMap);
            System.out.println("Save user login information to file successfully");

        } catch (Exception exception) {
            System.out.println(exception);
            System.out.println("Save user login information to file failed");

        } finally {
            fileOutputStream.close();
            objectOutputStream.close();
        }
    }

    private boolean loadAccountListFromFile() throws IOException {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File("bank.txt"));
            objectInputStream = new ObjectInputStream(fileInputStream);
            accountList = (ArrayList<Account>) objectInputStream.readObject();
            if (accountList == null) {
                accountList = new ArrayList();
            }
            System.out.println("Load account list successfully !");
            return true;

        } catch (Exception ex) {
            System.out.println("Load account list failed !");
            return false;

        } finally {
            if (fileInputStream != null)
                fileInputStream.close();

            if (objectInputStream != null)
                objectInputStream.close();
        }
    }

    private boolean loadLoginInformationFromFile() throws IOException {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            loginInformationMap = new HashMap<>();
            fileInputStream = new FileInputStream("user.txt");
            objectInputStream = new ObjectInputStream(fileInputStream);
            loginInformationMap = (HashMap<String, String>) objectInputStream.readObject();
            System.out.println("Load login information successfully !");
            return true;

        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Load login information failed !");
            return false;

        } finally {
            if (fileInputStream != null)
                fileInputStream.close();

            if (objectInputStream != null)
                objectInputStream.close();

        }
    }

    public void transferMoney(String moneyReceiverID, double moneyAmount) {
        for (Account account : accountList) {
            if (account.getId().equalsIgnoreCase(moneyReceiverID)) {
                account.deposit(account.getPassword(), moneyAmount);
            }
        }
    }

    public String getAccountName(String moneyReceiverID) {
        for (Account account : accountList) {
            if (account.getId().equals(moneyReceiverID)) {
                return account.getName();
            }
        }

        System.out.println("Id " + moneyReceiverID + " not found");
        return null;
    }

    public void removeAccount(Account acc) {
        accountList.remove(acc);
        loginInformationMap.remove(acc.getId());
    }
}
