package com.revature;

import java.io.File;
import java.io.IOException;
/**
 * CreateFile makes a txt file, journal-log.txt, for backing up all new journal entries, which appears in the project directory.
 */
public class CreateFile {
    public CreateFile() {
        File log = new File("journal-log.txt");
        try {
            if (log.createNewFile()) {
                System.out.println("File created: " + log.getName());
            } else {
                //System.out.println(log.getName() + " already exists. Writing to file for backup");
            }
        } catch (IOException e) {
            System.err.println("An error occured in CreateFile");
        }
    }
}