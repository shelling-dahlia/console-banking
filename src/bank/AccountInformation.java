/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class AccountInformation implements Serializable{
    String userame;
    String name;
    double amount;

    public AccountInformation(String userame, String name, double amount) {
        this.userame = userame;
        this.name = name;
        this.amount = amount;
    }
    
    
}
