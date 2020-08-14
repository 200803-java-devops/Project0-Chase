package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    // completely unsure about this url
    private final String url = "jdbc:postgresql://localhost:5432/journaldb";

    // database credentials
    private final String user = "postgres-user";
    private final String password = "9876";

    Connection connection = null;

    public ConnectDB() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("CONNECTED TO DATABASE SUCCESSFULLY!");
        } catch (SQLException sqlE) {
            System.err.println("Could NOT connect to database due to SQLException...");
            sqlE.printStackTrace();    
        } catch (Exception e) {
            System.err.println("Could NOT connect to database due to Exception...");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void close() { 
        try {
            if (this.connection != null) {
                this.connection.close();
                System.out.println("Database connection is closed.");
            }
        } catch(SQLException sqlE) {
            System.err.println("SQLException trying to close the connection...");
            sqlE.printStackTrace();
        }
    }
}