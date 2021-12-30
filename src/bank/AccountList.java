/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import lib.util.InputCheck;

/**
 *
 * @author PC
 */
public class AccountList {
        public ArrayList<Account> accList = new ArrayList();
        public ArrayList<LoginInformation> loginList = new ArrayList();
        public ArrayList<AccountInformation> userList = new ArrayList();
        public Scanner sc = new Scanner(System.in); 
        public int moneyInAccount;
        public int currentAccount;
        public String username;
        public String pwd;
    private String name;

            public int searchInList(ArrayList<Account> a,String username){
        for (Account i:a)
            if (i.getUsename().equalsIgnoreCase(username)) return a.indexOf(i);
        return -1;
    }

        
    public void addANewAccount(ArrayList<Account> a,Validation ic) {        
                    while (true){
            String username=null;
            while(true){
                username=ic.inputUserName(5);
                if (searchInList(accList, username)==-1) break;
                System.out.println("UserName already exit, enter again");
            }
            String pwd=ic.inputPassword(6);
            String name=InputCheck.getStringWithoutCondition("Enter the name: ", "This cannot be empty!");
            moneyInAccount=InputCheck.getAPositiveInteger(0, "Enter your amount of money: ", "This must be a positive number!");            
            accList.add(new Account(username, name, pwd, moneyInAccount));
            System.out.print("Wanna add another user? ");
            int option=InputCheck.getAnIntegerWithDomain(0, 1, "Your option. Choose 0/1 (YES-1/NO-0): ", "Please choose 0 or 1");
            if (option==0) break;
        }
    }
    
    public void printAll(ArrayList<Account> a){
        for (Account i:a){
            System.out.println(i);
        }
    }
    
    
    public void saveAccountDetailToFile(ArrayList<Account> accountList,String username, String temp1){
         for (Account account : accountList){
             userList.add(new AccountInformation(account.user_name,account.name,account.amount));
         }
    }
    
    public void saveLoginInformationToFile(ArrayList<Account> accountList,String username, String temp1){
         for (Account account : accountList){
             loginList.add(new LoginInformation(account.user_name, Validation.encryptPwdCeasar(account.password)));
         }
    }
    
    public ArrayList<AccountInformation> loadAccountDetailFromFile(ArrayList<Account> accountList,String username, String temp1){
        try {
            File f=new File("User.dat");
            if (!f.exists()) return null;
            FileInputStream fi=new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Account b;
            ArrayList<AccountInformation> inf  = (ArrayList<AccountInformation>) fo.readObject();
            fo.close();
            fi.close();
            return inf;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public ArrayList<LoginInformation> loadLoginInformationFromFile(ArrayList<Account> accountList,String username, double amount){
        try {
            File f=new File("Bank.dat");
            if (!f.exists()) return null;
            FileInputStream fi=new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Account b;
            ArrayList<LoginInformation> log  = (ArrayList<LoginInformation>) fo.readObject();
            fo.close();
            fi.close();
            return log;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void loadAccountList(){
        loadLoginInformationFromFile(accList, username, moneyInAccount);
        loadAccountDetailFromFile(accList, username, pwd);
        String currentUsername;
        String currentPassword;
        double currentMoneyInAccount;
        currentUsername = this.username;
        currentPassword = Validation.decryptPwdCeasar(this.pwd);
        currentMoneyInAccount = this.moneyInAccount;
        accList.add(new Account(username, name, pwd, moneyInAccount));        
    }
            
    public void login(ArrayList<Account> a,Validation ic){
        String username=ic.inputUserName(5);
        String pwd=InputCheck.getStringWithoutCondition("Enter password: ", "This cannot be emty!");
        int pos=searchInList(accList, username);
        if (pos==-1){
            System.out.println("Username not found!");
            return;
        }
        Account temp=accList.get(pos);
        if (temp.getPassword().equals(pwd))
            System.out.println("Login Sucessfully!");
        else System.out.println("Wrong Password");
    }
            
            
            
    public int searchAccount(String username) {

        int pos; 
        if (accList.isEmpty()) 
            return -1; 

        for (int i = 0; i < accList.size(); i++) 
            if (accList.get(i).getUsename().equalsIgnoreCase(username))
                return i;            
        return -1;            
    }
    
    public void removeAccount(){
       
        String username;
        int pos; 
        username = InputCheck.getStringWithoutCondition("Input Account username: ", "Account username is required!");
        pos = searchAccount(username);
        System.out.println("------------------------------------");
        if (pos == -1)
            System.out.println("Account not found!!!");
        else {            
            accList.remove(pos);
            System.out.println("The selected account is removed successfully!");
        }
        
    }
    //return object
    
    public void deposit(){
        String username;
        int pos;
        int depositMoney;
        username = InputCheck.getStringWithoutCondition("Input Account username: ", "Account username is required!");
        pos=searchAccount(username);
        System.out.println("------------------------------------");
        if (pos == -1)
            System.out.println("Account not found!!!");
        else {            
            depositMoney=InputCheck.getAPositiveInteger(0, "Enter your deposit money: ", "This must be a postitive number!");
            moneyInAccount+=depositMoney;
        }
    }
    
    public void transfer(){
        String username;
        int pos;
        int transferMoney;
        username=InputCheck.getStringWithoutCondition("Input Account username: ", "Account username is required!");
        pos=searchAccount(username);
        System.out.println("------------------------------------");
        if (pos == -1)
            System.out.println("Account not found!!!");
        else {            
                try {
            transferMoney=InputCheck.getAPositiveInteger(0, "Enter your deposit money: ", "This must be a postitive number!");
            if(moneyInAccount>0){
            moneyInAccount-=transferMoney;
                System.out.println("The remaining. Your money is: "+moneyInAccount);
            }
            else System.out.println("You have run out of money :>");
        } catch (Exception e) {
            System.err.println(e);
        }        
        }

    }

    public void withdraw(){
        try {
            int moneyDrawing=InputCheck.getAPositiveInteger(0, "Enter amount of money you wanna draw", "This must be a positive number!");
            if(moneyInAccount>0){
                moneyInAccount=moneyInAccount-moneyDrawing;
                System.out.println("The remaining. Your money is: "+moneyInAccount);
            }
            else System.out.println("You have run out of money :>");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    
    
}    

