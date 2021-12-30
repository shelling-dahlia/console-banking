package lib.util;

import java.util.Scanner;

/**
 *
 * @author PC
 */

public class InputCheck {

    private static Scanner sc = new Scanner(System.in);
    
    public static boolean getBoolean(String inputMsg){
        System.out.println(inputMsg+"-(T/F, Y/N, 1/0): ");
        String str;
        str=sc.nextLine().trim().toUpperCase();
        char c=str.charAt(0);
        if(c=='T'||c=='Y'||c=='1') return true;
        return false;
    }
    
    public static int getAnInteger(String inputMsg, String errorMsg) {
        int n;
        
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
 
    public static int getAnIntegerWithDomain(int lowerBound, int upperBound, String inputMsg, String errorMsg) {
        int n, tmp;
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine()); 
                if (n < lowerBound || n > upperBound)
                    throw new Exception();                   
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    
        public static int getAPositiveInteger(int bound, String inputMsg, String errorMsg) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine()); 
                if (n < bound)
                    throw new Exception();                   
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static double getADouble(String inputMsg, String errorMsg) {
        double n;
        
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static double getADoubleWithDomain(double lowerBound, double upperBound, String inputMsg, String errorMsg) {
        double n, tmp;
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n < lowerBound || n > upperBound)
                    throw new Exception();                
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    
        public static double getAPositiveDouble(double bound,String inputMsg, String errorMsg) {
        double n;
        
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n < bound)
                    throw new Exception();                
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    
    public static String getStringWithFormat(String inputMsg, String errorMsg, String format) {
        String stdString;
        boolean match;
        
        while (true) {
            System.out.print(inputMsg);
            stdString = sc.nextLine().trim().toUpperCase();
            match = stdString.matches(format);
            if (stdString.length() == 0 || stdString.isEmpty() || match == false)
                System.out.println(errorMsg);
            else
                return stdString;            
        }
    }
    
    public static String getStringWithoutCondition(String inputMsg, String errorMsg) {
        String str;  
        
        while (true) {
            System.out.print(inputMsg);
            str = sc.nextLine().trim();            
            if (str.length() == 0 || str.isEmpty())
                System.out.println(errorMsg);
            else 
                return str;
        }
    }
}