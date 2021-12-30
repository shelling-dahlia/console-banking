package ultility;

import java.util.ArrayList;

public class MyMenu extends ArrayList<String> {

    String title;

    public MyMenu (String title) {
        this.title = title;
    }

    public void showMenu () {
        System.out.println();
        System.out.println("===== " + title + " =====");
        for (int i = 0; i < size(); i++) {
            System.out.println((i + 1) + ". " + get(i));
        }
    }

    public int getUserChoice () throws Exception {
        System.out.print("Select your option: ");
        int option = MyScanner.getInteger(false, size(), 1);
        return option;
    }

}
