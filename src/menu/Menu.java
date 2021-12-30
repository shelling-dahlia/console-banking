package menu;

import java.util.ArrayList;
import lib.util.InputCheck;

/**
 *
 * @author PC
 *
 */

public class Menu {
    
    private String menuTitle;
    private ArrayList<String> optionList = new ArrayList();
    public Menu(String menuTitle) {
        this.menuTitle = menuTitle;
    }
    public void addNewOption(String newOption) {
        optionList.add(newOption);        
    }
    public void printMenu() {
        
        if (optionList.isEmpty()) {
            System.out.println("There is no item in the menu");
            return;
        }
        System.out.println("\n------------------------------------");
        System.out.println("Welcome to " + menuTitle);
        for (String x : optionList)
            System.out.println(x);  
    }
    public int getChoice() {
        int maxOption = optionList.size();      
        String inputMsg = "Choose [1.." + maxOption + "]: ";
        String errorMsg = "You are required to choose the option 1.." + maxOption; 
        return InputCheck.getAnIntegerWithDomain(1, maxOption, inputMsg, errorMsg);
    }
}
