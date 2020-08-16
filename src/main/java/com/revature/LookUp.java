package com.revature;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LookUp {
    // private FileReader reader;
    private String beginDate;
    private String cutoffDate;
    private Map<String, String> map;
    private Scanner reader;

    public LookUp(String d) {
        beginDate = d;
        cutoffDate = d;
        map = new HashMap<String, String>();
    }

    public LookUp(String d1, String d2) {
        beginDate = d1;
        cutoffDate = d2;
        map = new HashMap<String, String>();
    }

    public Map<String, String> find() {
        // finds all entries on day "beginDate" up to and including "cutoffDate"
        String entry = new String();
        try {
            reader = new Scanner(new File("journal-log.txt"));
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.contains(beginDate)) {
                    System.out.println("LookUp.find() has found " + beginDate);
                    break;
                }
            }
            // now we have reached "beginDate" in the text file
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                System.out.println("line = " + line);
                if (line.contains("ENTRY ON")) {
                    // have to add logic for finding the cutoff date
                    break;
                }
                entry = entry.concat(line);
                System.out.println("entry = " + entry);
            }
            map.put(beginDate, entry);
        } catch (FileNotFoundException e) {
            System.err.println("FileReader in LookUp did not find journal-log.txt");
        } catch (NoSuchElementException e2) {
            System.err.println("No line find in LookUp.find()");
        }
        reader.close();
        return map;
    }

    public void lookInTable() {
        SqlOperation queryObj = new SqlOperation();
        queryObj.getEntries(beginDate);
    }
}