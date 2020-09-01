package com.revature;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EditEntry {
    private String entryDT;
    private String entry;

    public EditEntry(String dateAndTime) {
        entryDT = dateAndTime;
    }

    // call to SqlOperation.getEntry
    public void RetrieveEntry() {
        SqlOperation queryObj = new SqlOperation();
        entry = queryObj.getEntry(entryDT);
    }

    public void WriteTempFile() throws IOException {
        BufferedWriter out = null;
        try {
            FileWriter fstream = new FileWriter("out.txt", true);
            out = new BufferedWriter(fstream);
            out.write(entry);
        } catch (IOException e) {
            System.err.println("Error in EditEntry.WriteTempFile: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public void OpenTextEditor() {
        
    }
}