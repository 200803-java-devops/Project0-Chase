package com.revature;

import java.io.IOException;

public class SearchMenu {
    private int lookUp = 2;
    private int searchWord = 3;

    public void openSearchMenu() {
        System.out.println("\"look up\": to look up an existing entry");
        System.out.println("\"search\": to search and return all entries that contain keywords");
    }

    public int querySelection() {
        JournalInput menuInput = new JournalInput();
        try {
            String userSelection = menuInput.queryInput().toLowerCase();
            if (userSelection.equals("look up")) {
                return lookUp;
            }
            else if (userSelection.equals("search")) {
                return searchWord;
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