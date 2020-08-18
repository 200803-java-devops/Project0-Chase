package com.revature;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
/**
 * JournalInput gets any user input from the console and returns it.
 */
public class JournalInput {
    public BufferedReader reader;

    public String queryInput() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }
}