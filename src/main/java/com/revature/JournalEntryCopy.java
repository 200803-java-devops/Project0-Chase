package com.revature;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class JournalEntryCopy {
    public String formattedDate;
    public int month;
    public int day;
    public int year;
    public String time;
    public int hour;
    public int minute;
    public String entry;
    public ArrayList<String> entryArray = new ArrayList<String>();
    //boolean important;
    //int totalEntries;
    //add list format    

    public JournalEntryCopy() {}

    private void queryEntry() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        formattedDate = dateTime.format(dateFormat);
        JournalInput journalInput = new JournalInput();
        System.out.println("Please enter your current journal entry: ");
        try {
            entry = journalInput.queryInput();
        } catch (IOException e) {
            System.err.println("IOException with queryEntry");
        }
    }

    public void parseEntry() {
        Scanner scanner = new Scanner(entry);
        scanner.useDelimiter(".\s|\\?\s|!\s");
        //scanner.useDelimiter(".\\s");
        while (scanner.hasNext()) {
            entryArray.add(scanner.next());
            System.out.println(scanner.next());
        }
        scanner.close();
        // ParseMessage p = new ParseMessage(entry);
        // entryArray = p.seperateSentences();
        // System.out.println(entryArray);
    }

    public void assembleEntry() {
        this.queryEntry();
        System.out.println("Your entry was entered succesfully at " + formattedDate + ".");
        this.parseEntry();
    }
}