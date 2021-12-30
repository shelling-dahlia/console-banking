package ultility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean validateID (String id) {
        if (id.length() < 6) {
            System.out.println("ID length must be bigger than 6");
            return false;
        }

        if (id.contains(" ")) {
            System.out.println("ID must not contain space");
            return false;
        }

        return true;
    }

    public static boolean validatePassword (String password) {

        if (password.length() < 6) {
            System.out.println("Password length must be bigger than 6");
            return false;
        }

        if (! hasUppercase(password)) {
            System.out.println("Password must have at least 1 uppercase letter");
            return false;
        }

        if (! hasNumber (password)) {
            System.out.println("Password must have at least 1 number");
            return false;
        }

        if (! hasSpecialCharacter(password)) {
            System.out.println("Password must have at least 1 special letter");
            return false;
        }

        return true;
    }


    private static boolean hasSpecialCharacter (String password) {

        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(password);
        boolean b = m.find();

        return b;
    }


    private static boolean hasNumber(String password) {
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isDigit(c)) {
                return true;
            }
        }

        return false;
    }

    private static boolean hasUppercase (String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

}
