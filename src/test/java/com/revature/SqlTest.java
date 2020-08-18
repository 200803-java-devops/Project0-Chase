package com.revature;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SqlTest {
    

    @Test
    public void checkTableExists() {
        SqlOperation sqlObj = new SqlOperation();
        assertTrue(sqlObj.checkTable());
    }
}