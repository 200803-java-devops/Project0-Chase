package com.revature;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * The JournalEntry class is responsible for asking the user for a new entry as input.
 * Once the input is acquired, it is sent to the "journal_table" in the SQL and backed up in a txt file.
 */
public class JournalEntry {
    public String stdFormattedDate;
    public String sqlFormattedDate;
    public String dateOnly;
    public String time;
    public String entry;

    /**
     * queryEntry calls JournalInput for a new journal entry while getting the current timestamp
     */
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

    /**
     * assemblEntry calls the other class methods while also writing to a backup file
     */
    public void assembleEntry() {
        this.queryEntry();
        System.out.println("Your entry was entered succesfully at " + stdFormattedDate + ".");
        // this.parseEntry();
        new CreateFile();
        new WriteToFile(entry, stdFormattedDate).Write();
        queryDB();
    }

    /**
     * queryDB calls the SqlOperation class to insert a new entry into "journal_table"
     */
    public void queryDB() {
        SqlOperation queryObj = new SqlOperation();
        SeparateDateTime s = new SeparateDateTime(sqlFormattedDate);
        dateOnly = s.getDate();
        time = s.getTime();
        String createT = "CREATE TABLE IF NOT EXISTS \"journal_table\" (date_and_time TIMESTAMP, date_only DATE, time_only TIME, entry TEXT);";
        queryObj.createTable(createT);
        queryObj.insert(sqlFormattedDate, dateOnly, time, entry);
        //queryObj.getTable();
    }

    /**
     * deletTable is not actually used in the application, but is ready to be used for table deletion in SQL database
     * @param table name of the sql table
     */
    public void deleteTable(String table) {
        SqlOperation queryObj = new SqlOperation();
        queryObj.dropTable(table);
    }
}