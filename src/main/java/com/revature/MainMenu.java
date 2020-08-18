package com.revature;

import java.io.IOException;
/**
 * MainMenu is the class which displays the menu options and sends the user's choice out to ActivateSelection
 * @return  the user's menu input choice is returned as an integer by querySelection
 */
public class MainMenu {
    private int newEntry = 1;
    private int lookUp = 2;
    private int searchWord = 3;

    public void openMainMenu() {
        System.out.println("Welcome to your journal main menu! Enter one of the following keywords to begin (without quotes). ");
        System.out.println("\"new\": to make a new journal entry");
        System.out.println("\"look up\": to look up an existing entry");
        System.out.println("\"search\": to search and return all entries that contain keywords");
    }

    public int querySelection() {
        JournalInput menuInput = new JournalInput();
        try {
            String userSelection = menuInput.queryInput().toLowerCase();
            if (userSelection.equals("new")) {
                return newEntry;
            }
            else if (userSelection.equals("look up")) {
                return lookUp;
            }
            else if (userSelection.equals("search")) {
                return searchWord;
            }
            else if (userSelection.equals("delete")) {
                return 9;
            }
            else {
                System.out.println("That was not a valid menu choice. The app is closing.");
                return -1;
            }
        } catch (IOException e) {
            System.err.println("IOException in menu input.");
        }
        return 0;
    }
}