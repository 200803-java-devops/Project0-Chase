package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * SqlOperation is responsible for any interaction to the postgreSQL database.
 */
public class SqlOperation {
    public Connection connection = null;

    /**
     * runSQL runs a sql query
     * @param sql string of the query
     */
    public void runSQL(String sql) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException sqlE) {
            System.err.println("SQLException caught in TryConnect.runSQL...");
            System.err.println("Could not execute statement: " + sql);
            sqlE.printStackTrace();
        }
    }

    /**
     * createTable creates a new table in the database
     * @param sql string of the query
     */
    public void createTable(String sql) {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();
        runSQL(sql);
        db.close();
    }

    /**
     * insert will insert a new journal entry
     * @param dateTime date and time in TIMESTAMP format
     * @param date only the date
     * @param time only the time
     * @param entry text for the journal entry
     */
    public void insert(String dateTime, String date, String time, String entry) {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();
        String sql = "INSERT INTO \"journal_table\" VALUES ('" + dateTime + "','" + date + "','" + time + "','" + entry + "');";
        runSQL(sql);
        db.close();
    }

    /**
     * getTable() prints all of the entries in the table
     */
    public void getTable() {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();
        String sql = "SELECT * FROM \"journal_table\";";
        System.out.println();
        System.out.println("Looking into database for \"journal_table\"...");
        System.out.println();
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            int index = 1;
            while (result.next()) {
                System.out.print("ENTRY " + index + ": " + result.getString("date_and_time") + "\t");
                System.out.println(result.getString("entry"));
                index++;
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println("failed to look into table");
            System.err.println();
            e.printStackTrace();
        }
        db.close();
    }

    /**
     * getEntries prints the entries in the window of date1 to date2 inclusively
     * @param date1 first chronological date
     * @param date2 second chronological date
     * @return ArrayList of the timpestamps of the entries that were printed
     */
    public ArrayList<String> getEntries(String date1, String date2) {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();
        String sql = "SELECT * FROM \"journal_table\" WHERE date_only BETWEEN '" + date1 + "' AND '" + date2 + "';";
        //System.out.println("Looking into database for \"journal_table\" for entries between " + date1 + " & " + date2 + "...");
        PreparedStatement statement;

        ArrayList<String> rows = new ArrayList<String>();
        //This 'Buffer Space' aligns the index of the 'rows' list with the index of the printed entries below
        rows.add("Buffer Space");   

        try {
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            int index = 1;
            while (result.next()) {
                System.out.print("ENTRY " + index + ": " + result.getString("date_and_time") + "\t");
                System.out.println(result.getString("entry"));
                rows.add(result.getString("date_and_time"));
                index++;
            }
            statement.close();
            System.out.println();
        } catch (SQLException e) {
            System.err.println("failed to look into table");
            System.err.println();
            e.printStackTrace();
        }
        db.close();
        return rows;
    }

    /**
     * getEntry returns the entry that matches the given timestamp dateAndTime
     * @param dateAndTime timestamp of the entry to retrieve
     * @return the entry as a String
     */
    public String getEntry(String dateAndTime) {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();
        PreparedStatement statement;
        String entry = new String();
        String sql = "SELECT * FROM \"journal_table\" WHERE date_and_time='" + dateAndTime + "';";
        try {
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            result.next();
            entry = result.getString("entry");
            statement.close();
        } catch (SQLException e) {
            System.err.println("failed to look into table in SqlOperation.getEntry");
            e.printStackTrace();
        }
        db.close();
        return entry;
    }

    /**
     * updateEntry edits an existing entry without changing the date or time
     * @param dateAndTime the primary key and timestamp of the entry to update
     * @param entry the new entry text
     */
    public void updateEntry(String dateAndTime, String entry) {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();
        String sql = "UPDATE \"journal_table\" SET entry='" + entry + "' WHERE date_and_time='" + dateAndTime + "';";
        runSQL(sql);
        db.close();
    }

    /**
     * searchByKeyword prints all the entries containing the given keyword phrase
     * @param phrase keyword phrase to search table for
     * @return ArrayList of the timpestamps of the entries that were printed
     */
    public ArrayList<String> searchByKeyword(String phrase) {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();
        String sql = "SELECT * FROM \"journal_table\" WHERE entry ILIKE '%" + phrase + "%'";
        System.out.println();
        System.out.println("Looking into \"journal_table\" for keyword(s): " + phrase);
        System.out.println();
        PreparedStatement statement;

        ArrayList<String> rows = new ArrayList<String>();
        //This 'Buffer Space' aligns the index of the 'rows' list with the index of the printed entries below
        rows.add("Buffer Space");   

        try {
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            int index = 1;
            while (result.next()) {
                System.out.print("ENTRY " + index + ": " + result.getString("date_and_time") + "\t");
                System.out.println(result.getString("entry"));
                rows.add(result.getString("date_and_time"));
                index++;
            }
            System.out.println();
            if (index == 1) {
                System.out.println("There were no entries in your journal that contained that phrase.");
                System.out.println();
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println("Failed to look into table when searching for keyword");
            e.printStackTrace();
        }
        db.close();
        return rows;
    }

    /**
     * checks the existance of the SQL table
     * @return true if it exists.
     */
    public boolean checkTable() {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("SELECT * FROM \"journal_table\";");
            statement.executeQuery();
            statement.close();
        } catch (SQLException e) {
            System.out.println("The table \"journal_table\" does NOT exist");
            e.printStackTrace();
            return false;
        }
        db.close();
        return true;
    }

    /**
     * dropTable is never called in the app, but ready to use if called to reset the table
     */
    public void dropTable(String tableName) {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("DROP TABLE " + tableName + ";");
            statement.executeUpdate();
            statement.close();
            System.out.println("Successfuly dropped table: " + tableName);
        } catch (SQLException e) {
            System.err.println("Problem dropping table: " + tableName);
            e.printStackTrace();
        }
        db.close();
    }
}