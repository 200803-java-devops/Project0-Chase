package com.revature;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class JournalEntry {
    public String stdFormattedDate;
    public String sqlFormattedDate;
    public String dateOnly;
    // public int month;
    // public int day;
    // public int year;
    public String time;
    // public int hour;
    // public int minute;
    public String entry;
    public ArrayList<String> entryArray = new ArrayList<String>();
    // boolean important;
    // int totalEntries;
    // add list format

    public JournalEntry() {
    }

    private void queryEntry() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        DateTimeFormatter sqlDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        stdFormattedDate = dateTime.format(dateFormat);
        sqlFormattedDate = dateTime.format(sqlDateFormat);
        JournalInput message = new JournalInput();
        System.out.println("Please enter your current journal entry: ");
        try {
            entry = message.queryInput();
        } catch (IOException e) {
            System.err.println("IOException with queryEntry");
        }
    }

    public void parseEntry() {
        ParseMessage p = new ParseMessage(entry);
        entryArray = p.seperateSentences();
        System.out.println(entryArray);
    }

    public void assembleEntry() {
        this.queryEntry();
        System.out.println("Your entry was entered succesfully at " + stdFormattedDate + ".");
        // this.parseEntry();
        new CreateFile();
        new WriteToFile(entry, stdFormattedDate).Write();
        queryDB();
    }

    public void queryDB() {
        SqlOperation queryObj = new SqlOperation();
        SeparateDateTime s = new SeparateDateTime(sqlFormattedDate);
        dateOnly = s.getDate();
        time = s.getTime();
        String createT = "CREATE TABLE IF NOT EXISTS \"journal_table\" (date_and_time TIMESTAMP, date_only DATE, time_only TIME, entry TEXT);";
        queryObj.createTable(createT);
        queryObj.insert(sqlFormattedDate, dateOnly, time, entry);
        queryObj.getTable();
    }

    public void deleteTable(String table) {
        SqlOperation queryObj = new SqlOperation();
        queryObj.dropTable(table);
    }
}