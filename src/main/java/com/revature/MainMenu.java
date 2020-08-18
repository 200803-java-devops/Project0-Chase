package com.revature;

import java.io.IOException;

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
            if (userSelection.equals("look up")) {
                return lookUp;
            }
            if (userSelection.equals("search")) {
                return searchWord;
            }
            if (userSelection.equals("delete")) {
                return 9;
            }
        } catch (IOException e) {
            System.err.println("IOException in menu input.");
        }
        return 0;
    }
}