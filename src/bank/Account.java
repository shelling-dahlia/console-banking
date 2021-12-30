/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.util.Scanner;
import lib.util.InputCheck;
import java.io.Serializable;
/**
 *
 * @author PC
 */


public class Account implements Serializable {

    private final String id;

    private final String name;

    private double balance;

    private String password;

    public Account(String id, String name, String password, double balance) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean withdraw(String password, double withdrawal) {
        if (balance < withdrawal) {
            System.out.println("Withdrawal money is bigger than current balance");
            return false;
        }

        if (password.matches(this.password)) {
            balance -= withdrawal;
            return true;
        } else {
            System.out.println("Wrong password");
            return false;
        }
    }

    public boolean deposit (String password, double deposit) {
        if (password.matches(this.password)) {
            balance += deposit;
            return true;
        } else {
            System.out.println("Wrong password");
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }
}
