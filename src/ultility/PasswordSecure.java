package ultility;

import java.util.Random;

public class PasswordSecure {


    public static String coreEncrypt (String text, int additional) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            char c = (char) ( ( (int) text.charAt(i) + additional) % 256);
            result += c;
        }
        return result;
    }

    public static String coreDecrypt (String text, int additional) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            char c = (char) ( ( (int) text.charAt(i) - additional) % 256);
            result += c;
        }
        return result;
    }

    public static String encode (String password) {

        Random random = new Random();

        int passwordKeyNumber = random.nextInt(23) + 100;

        int passwordKeyNumber_KeyCode = random.nextInt(100);

        String formatedPassKeyNumber = String.format("%03d", passwordKeyNumber);

        String formatedPassKeyNumber_KeyCode = String.format("%02d", passwordKeyNumber_KeyCode);

        String tail = coreEncrypt(formatedPassKeyNumber, passwordKeyNumber_KeyCode) + coreEncrypt(formatedPassKeyNumber_KeyCode, 121);

        String encryptedPassword = coreEncrypt(password, passwordKeyNumber) + tail;

        return encryptedPassword;

    }

    public static String decode (String encryptedPass) {
        int encryptedPassLength = encryptedPass.length();
        int passwordKeyNumber_KeyCode = Integer.parseInt(coreDecrypt(encryptedPass.substring(encryptedPassLength - 2), 121));
        int passwordKeyNumber = Integer.parseInt(coreDecrypt(encryptedPass.substring(encryptedPassLength - 5, encryptedPassLength - 2), passwordKeyNumber_KeyCode));

        encryptedPass = encryptedPass.substring(0, encryptedPassLength - 5);
        String originalPassword = coreDecrypt(encryptedPass, passwordKeyNumber);

        return originalPassword;

    }

}
