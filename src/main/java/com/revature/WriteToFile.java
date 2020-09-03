package com.revature;

import java.io.FileWriter;
import java.io.IOException;
/**
 * WriteToFile will be called for new entries to be written to the journal-log.txt backup file.
 */
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
            myWriter.write("ENTRY ON " + "[" + date + "]:\n" + entry + "\n\n\n");   //ENTRY ON will be removed
            myWriter.close();
            //System.out.println("Successfully wrote to journal-log.txt for a backup log.");
        } catch (IOException e) {
            System.err.println("IOException with WriteToFile: FileWriter");
            e.printStackTrace();
        }
    }
}