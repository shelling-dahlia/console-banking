/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;
import java.util.Scanner;
import java.util.regex.Matcher;
import lib.util.InputCheck;
import java.util.regex.Pattern;
/**
 *
 * @author PC
 */
public class Validation {
    private Scanner sc;
    public Validation(){
        sc=new Scanner(System.in);
    }
    
    public String inputUserName(int minLength){
        String temp=InputCheck.getStringWithoutCondition("Please username: ", "This cannot be empty!");
        if (temp.length()<minLength){
            System.out.print("Minimun "+minLength+" length, enter again: ");
            return inputUserName(minLength);
        }
        if (!temp.matches("[a-zA-Z0-9]+")){
            System.out.println("Username cannot contain a special character or space: ");
            return inputUserName(minLength);
        }
        return temp;
    }
    public String inputPassword(int minLength){
        String temp1="";
        boolean checkLength=false;
        boolean checkDigit=false;
        boolean checkCharUp=false;
        boolean checkCharLow=false;
        boolean checkSpec=false;
        do {            
            temp1=InputCheck.getStringWithoutCondition("Please enter password: ", "This cannot be empty!");    

            if(temp1.length()>=6) {
                checkLength=true;
            }
            char c;
            for (int i=0;i<temp1.length();i++){
                c=temp1.charAt(i);
            if (Character.isLowerCase(c)){
                checkCharLow=true;
                break;
            }                
            }
            
            for (int i=0;i<temp1.length();i++){
                c=temp1.charAt(i);
            if (Character.isUpperCase(c)){
                checkCharUp=true;
                break;
            }
            }
            
            for (int i=0;i<temp1.length();i++){
                c=temp1.charAt(i);
            if (Character.isDigit(c)){
                checkDigit=true;
                break;
            }
            }
            
            for (int i=0;i<temp1.length();i++){
                c=temp1.charAt(i);
            if (c==('!')||c==('@')||c==('#')||c==('$')||c==('%')||c==(' ')||
               c==('^')||c==('&')||c==('*')||c==('(')||c==(')')||c==('{')||c==('[')||
               c==('}')||c==(']')||c==('|')||c==('\\')||c==(':')||c==(';')||c==('"')||
               c==('\'')||c==(',')||c==('.')||c==('?')||c==('/')||c==('`')||c==('~')){
                checkSpec=true;
                break;
            }  
            }
            
            if(checkCharLow==false||checkCharUp==false||checkDigit==false||checkSpec==false||checkLength==false){
                System.out.println("Invalid password! \nPassword must contain at least 1 special character, number and character, minimum "+minLength+" length");
            }

            else{
                System.out.println("Good Password");
            }
        } while (checkCharLow==false||checkCharUp==false||checkDigit==false||checkSpec==false||checkLength==false);

        String temp2=InputCheck.getStringWithoutCondition("Retype password: ", "This cannot be empty!");
        if (temp2.equals(temp1)){
            System.out.println("Create account successfully");
        }
        else{
            System.out.println("Wrong password!");
            System.out.println("Input passowrd again");
            return inputPassword(minLength);
        }
        return temp1;
    }
    
    public static String encryptPwdCeasar(String temp1){
        String result="";
        for (int i=0;i<temp1.length();i++){
            char c=(char)(((int)temp1.charAt(i)+123)%256);
            result+=c;
        }
        return result;
    }
    
    public static String decryptPwdCeasar(String temp1){
        String result="";
        for(int i=0;i<temp1.length();i++){
           char c=(char)(((int)temp1.charAt(i)-123)%256);          
           result+=c;
        }
        return result;
    }
}
    