package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TryConnect {
    public Connection connection = null;

    void createTable(String sql, String tableName) {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();

        runSQL(sql, tableName);

        db.close();
    }

    void delete(String date) {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();

        String sql = "DELETE FROM JOURNALLOG WHERE dateAndTime='" + date + "';";

        runSQL(sql, "JOURNALLOG");

        db.close();
    }

    // eventually change to date, time, entryText
    void insert(String date, String entryText) {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();

        //String sql = "INSERT INTO JOURNALLOG VALUES (" + date + "," + entryText + ");";
        //String sql = "INSERT INTO JOURNALLOG VALUES ('08-14-2020', 'hard code test');";
        String sql = "INSERT INTO JOURNALLOG VALUES ('" + date + "','" + entryText + "');"; 

        runSQL(sql, "JOURNALLOG");

        db.close();
    }

    void runSQL(String sql, String tableName) {
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

    void getTable() {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();

        String sql = "SELECT * FROM JOURNALLOG";

        System.out.println("Looking into database for table...");

        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            int index = 1;
            while(result.next()) {
                System.out.print("row " + index + ": " + result.getString("dateAndTime") + "\t");
                System.out.println(result.getString("entryText"));
                index++;
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println("failed to look into table" + e);
        }
        db.close();
    }
}