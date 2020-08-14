package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TryConnect {
    public Connection connection = null;

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

    public void createTable(String sql) {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();

        runSQL(sql);

        db.close();
    }

    // eventually change to date, time, entryText
    public void insert(String date, String entryText) {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();

        String sql = "INSERT INTO JOURNALLOG VALUES ('" + date + "','" + entryText + "');";

        runSQL(sql);

        db.close();
    }

    public void insert(String date, String time, String entry) {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();
        String sql = "INSERT INTO JOURNAL_TABLE VALUES ('" + date + "','" + time + "','" + entry + "');";
        runSQL(sql);
        db.close();
    }

    public void getTable() {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();

        String sql = "SELECT * FROM JOURNAL_TABLE";

        System.out.println("Looking into database for JOURNAL_TABLE...");

        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            int index = 1;
            while (result.next()) {
                System.out.print(
                        "row " + index + ": " + result.getString("date") + "\t" + result.getString("time") + "\t");
                System.out.println(result.getString("entry"));
                index++;
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println("failed to look into table" + e);
        }
        db.close();
    }

    public void delete(String date) {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();

        String sql = "DELETE FROM JOURNALLOG WHERE dateAndTime='" + date + "';";

        runSQL(sql);

        db.close();
    }

    // checks the existance of the SQL table. Returns true if it exists.
    public boolean checkTable() {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("CREATE TABLE JOURNAL_TABLE (date text, time text, entry text);");
            statement.executeQuery();
        } catch (SQLException e) {
            db.close();
            return true;
        }
        db.close();
        return false;
    }
}