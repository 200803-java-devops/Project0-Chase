package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlOperation {
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

    public void insert(String dateTime, String date, String time, String entry) {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();
        String sql = "INSERT INTO \"journal_table\" VALUES ('" + dateTime + "','" + date + "','" + time + "','" + entry + "');";
        runSQL(sql);
        db.close();
    }

    public void getTable() {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();
        String sql = "SELECT * FROM \"journal_table\";";
        System.out.println("Looking into database for \"journal_table\"...");
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            int index = 1;
            while (result.next()) {
                System.out.print(
                        "ENTRY " + index + ": " + result.getString("date_and_time") + "\t");
                System.out.println(result.getString("entry"));
                index++;
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println("failed to look into table");
            e.printStackTrace();
        }
        db.close();
    }

    public void getEntries(String date) {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();
        String sql = "SELECT * FROM \"journal_table\" WHERE date_only='" + date + "';";
        System.out.println("Looking into database for \"journal_table\"...");
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            int index = 1;
            while (result.next()) {
                System.out.print("ENTRY " + index + ": " + result.getString("date_and_time") + "\t");
                System.out.println(result.getString("entry"));
            }
        } catch (SQLException e) {
            System.err.println("failed to look into table" + e);
            e.printStackTrace();
        }
        db.close();
    }

    // public void delete(String date) {
    //     ConnectDB db = new ConnectDB();
    //     connection = db.getConnection();
    //     String sql = "DELETE FROM \"journal_table\" WHERE date_and_time='" + date + "';";
    //     runSQL(sql);
    //     db.close();
    // }

    // checks the existance of the SQL table. Returns true if it exists.
    public boolean checkTable() {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("SELECT * FROM \"journal_table\";");
            statement.executeQuery();
        } catch (SQLException e) {
            db.close();
            System.out.println("The table \"journal_table\" does indeed exist");
            e.printStackTrace();
            return true;
        }
        db.close();
        return false;
    }

    public void dropTable(String tableName) {
        ConnectDB db = new ConnectDB();
        connection = db.getConnection();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("DROP TABLE " + tableName + ";");
            statement.executeUpdate();
            System.out.println("Successfuly dropped table: " + tableName);
        } catch (SQLException e) {
            System.err.println("Problem dropping table: " + tableName);
            e.printStackTrace();
        }
        db.close();
    }
}