package com.revature;

public class App {
    public static void main( String[] args )
    {
        System.out.println( "JournalApp starting up...\n" );
        int userSelection = startMainMenu();
        new ActivateSelection(userSelection).activate();
    }

    private static int startMainMenu() {
        MainMenu menu = new MainMenu();
        menu.openMainMenu();
        return menu.querySelection();
    }   
}

/*
AwtControlDemo  awtControlDemo = new AwtControlDemo();
awtControlDemo.showTextAreaDemo();
*/