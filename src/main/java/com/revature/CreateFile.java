package com.revature;

import java.io.File;
import java.io.IOException;

public class CreateFile {
    public CreateFile() {
        File log = new File("journal-log.txt");
        try {
            if (log.createNewFile()) {
                System.out.println("File created: " + log.getName());
            } else {
                System.out.println(log.getName() + " already exists.");
            }
        } catch (IOException e) {
            System.err.println("An error occured in CreateFile");
        }
    }
}