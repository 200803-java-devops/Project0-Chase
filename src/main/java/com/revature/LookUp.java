package com.revature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LookUp {
    //private FileReader reader;
    private String beginDate;
    private Map<String, String> map;
    private Scanner reader;

    public LookUp(String date) {
        beginDate = date;
        map = new HashMap<String, String>();
    }

    public Map<String, String> Find() {
        try {
            //reader = new FileReader("journal-log.txt");
            reader = new Scanner(new File("journal-log.txt"));
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                //System.out.println(line);
                if (line.contains(beginDate)) {
                    //add the date to the key value of the map
                    //add the remainder of the content until the \n\n characters are reached
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("FileReader in LookUp did not find journal-log.txt");
        }
        return map;
    }
}