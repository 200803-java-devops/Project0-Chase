package com.revature;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    public String entry;
    public String date = "no date";
    
    public WriteToFile(String text) {
        entry = text;
    }

    public WriteToFile(String text, String d) {
        entry = text;
        date = d;
    }

    public void Write() {
        try {
            FileWriter myWriter = new FileWriter("journal-log.txt", true);
            myWriter.write("\n\n" + date + "\n" + entry);
            myWriter.close();
            System.out.println("Successfully wrote to journal-log.txt");
        } catch (IOException e) {
            System.err.println("IOException with WriteToFile: FileWriter");
        }
    }
}