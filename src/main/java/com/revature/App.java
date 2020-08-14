package com.revature;

public class App {
    public static void main( String[] args )
    {
        System.out.println( "JournalApp starting up..." );
        int userSelection = startMainMenu();
        // TEMPORARY CHANGE TO TEST DB (BELOW):
        new ActivateSelection(userSelection).activate();
    }

    private static int startMainMenu() {
        Menu menu = new Menu();
        menu.openMainMenu();
        return menu.querySelection();
    }   
}

//function for retrieving entries within time range, 
//entries that are important, entries are lists