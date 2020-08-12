package com.revature;

public class App {
    public static void main( String[] args )
    {
        System.out.println( "JournalApp starting up..." );
        int userSelection = startMainMenu();
        activateSelection(userSelection);
    }

    private static int startMainMenu() {
        Menu menu = new Menu();
        menu.openMainMenu();
        return menu.querySelection();
    }

    private static void activateSelection(int selection) {
        if (selection == 1) {
            //TEMPORARY SWITH to JournalEntryCopy from JournalEntry
            JournalEntry entry = new JournalEntry();
            entry.assembleEntry();
        }
    }
}

//function for retrieving entries within time range, 
//entries that are important, entries are lists