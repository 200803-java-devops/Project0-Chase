package com.revature;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.Test;

public class SqlTest {

    @Test
    public void checkTableExists() {
        SqlOperation sqlObj = new SqlOperation();
        assertTrue(sqlObj.checkTable());
    }

    @Test
    public void enterEntryTest() {
        ConnectDB db = new ConnectDB();
        Connection connection = db.getConnection();
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter sqlDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String sqlFormattedDateTime = dateTime.format(sqlDateFormat);
        SeparateDateTime separatorObj = new SeparateDateTime(sqlFormattedDateTime);
        String date = separatorObj.getDate();
        String time = separatorObj.getTime();
        String sql1 = "INSERT INTO \"journal_table\" VALUES ('" + sqlFormattedDateTime + "','" + date + "','" + time + "','jUnit test entry');";
        
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(sql1);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        String sql2 = "SELECT * FROM \"journal_table\" WHERE date_and_time= '" + sqlFormattedDateTime + "';";

        try {
            statement = connection.prepareStatement(sql2);
            ResultSet result = statement.executeQuery();
            result.next();
            assertTrue(result.getString("date_and_time").equals(sqlFormattedDateTime));
            assertTrue(result.getString("date_only").equals(date));
            assertTrue(result.getString("time_only").equals(time));
            assertTrue(result.getString("entry").equals("jUnit test entry"));
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();
    }

    @After
    public void deleteTestEntry() {
        ConnectDB db = new ConnectDB();
        Connection connection = db.getConnection();
        String sql = "DELETE FROM \"journal_table\" WHERE entry= 'jUnit test entry'";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();
    }
}