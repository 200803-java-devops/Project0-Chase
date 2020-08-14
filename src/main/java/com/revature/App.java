package com.revature;

public class App {
    public static void main( String[] args )
    {
        System.out.println( "JournalApp starting up..." );
        int userSelection = startMainMenu();
        // TEMPORARY CHANGE TO TEST DB (BELOW):
        //new ActivateSelection(userSelection).activate(); 
        tryDB();
    }

    private static int startMainMenu() {
        Menu menu = new Menu();
        menu.openMainMenu();
        return menu.querySelection();
    }

    private static void tryDB() {
        //ConnectDB connecter = new ConnectDB();
        TryConnect tryC = new TryConnect();
        String sql1 = "CREATE TABLE JOURNALLOG (dateAndTime text, entryText text);";
        //String sql2 = "INSERT INTO JOURNALLOG VALUES (08-14-2020, test entry);";
        //tryC.createTable(sql1, "JOURNALLOG");
        tryC.insert("08-13-2020", "test entry 2");
        //tryC.delete("-2026");
        tryC.getTable();
    }
}

//function for retrieving entries within time range, 
//entries that are important, entries are lists