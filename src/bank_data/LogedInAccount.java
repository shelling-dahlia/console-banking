package bank_data;

public class LogedInAccount {

    private static Account instance = null;

    public static void setInstance (Account logedInAccount) throws Exception {
        if (logedInAccount == null) {
            instance = null;
            return;
        }

        if (instance == null) {
            instance = logedInAccount;
        } else {
            throw new Exception("Overlapping logging !!");
        }
    }

    public static boolean accountLogedIn () {
        return instance != null;
    }

    public static Account getInstance () throws Exception {
        if (instance == null) {
            throw new Exception("User has not loged in !!!");
        }
        return instance;
    }

}
