package com.revature;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class JournalInput {
    public BufferedReader reader;
    //String input;

    public String queryInput() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }
}