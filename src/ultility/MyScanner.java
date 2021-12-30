package ultility;

import java.util.Scanner;

public class MyScanner {

    public static int getInteger (boolean negativable, int max, int min){
        int result = 0;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                result = scanner.nextInt();
                if (result < min || result > max) {
                    System.out.println("Input must be in range " + min + ".." + max);
                } else if (!negativable && result <= 0) {
                    System.out.println("Input must be > 0");
                } else {
                    return result;
                }
            } catch (Exception exception) {
                System.out.println("Input wrong format. Must input integer");
            }
        }

    }

    public static int getInteger (Boolean negativable, int max) throws Exception {
        return getInteger(negativable, max, negativable? Integer.MIN_VALUE : 0);
    }

    public static double getDouble (boolean negativable, double max, double min) throws Exception {
        double result = 0;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                result = scanner.nextDouble();
                if (result < min || result > max) {
                    System.out.println("Input must be in range " + min + ".." + max);
                } else if (!negativable && result <= 0) {
                    System.out.println("Input must be > 0");
                } else {
                    return result;
                }
            } catch (Exception exception) {
                System.out.println("Input wrong format. Must input double");
            }
        }
    }

    public static double getDouble (boolean negativable) throws Exception {
        double result = 0;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                result = scanner.nextDouble();
                if (!negativable && result <= 0) {
                    System.out.println("Input must be > 0");
                } else {
                    return result;
                }
            } catch (Exception exception) {
                System.out.println("Input wrong format. Must input double");
            }
        }
    }

    public static String getString (String message) {

        if (message != null) {
            if (!message.isEmpty())
                System.out.print(message);
        }

        Scanner scanner = new Scanner(System.in);

        String result = "";

        while (result.isEmpty()) {
            result = scanner.nextLine();

            if (result.isEmpty()) {
                System.out.println("You can not input empty text. Re-input: ");
            }
        }

        return result;
    }

    public static String getString (String message, String... optionList) {

        if (message != null) {
            if (!message.isEmpty())
                System.out.print(message);
        }

        Scanner scanner = new Scanner(System.in);

        String result = "";

        while (result.isEmpty() || !inOptions(result, optionList)) {
            result = scanner.nextLine();

            if (result.isEmpty()) {
                System.out.println("You can not input empty text. Re-input: ");
            } else if (!inOptions(result, optionList)) {
                System.out.print("Input must be: ");
                for (String option : optionList) {
                    System.out.print(option + " / ");
                }
                System.out.println();
            }

        }

        return result;
    }

    private static boolean inOptions (String inputedOptions, String[] optionList) {
        for (String option : optionList) {
            if (inputedOptions.equals(option)) {
                return true;
            }
        }
        return false;
    }


    public static String getString () {
        return getString(null);
    }

    public static boolean getBoolean () {
        System.out.println("Input True or False / T or F (Case-free)");

        while (true) {
            String text = getString().toLowerCase();
            if (text.equals("true") || text.equals("t")) {
                return true;
            } else if (text.equals("false") || text.equals("f")) {
                return false;
            } else {
                System.out.println("You must input the right format. Re-input");
            }
        }
    }
}
