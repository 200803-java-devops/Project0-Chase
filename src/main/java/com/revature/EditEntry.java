package com.revature;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class EditEntry {
    private String entryDT;
    private String entry;
    private String updatedEntry;

    public EditEntry(String dateAndTime) {
        entryDT = dateAndTime;
        updatedEntry = new String();
    }

    // call to SqlOperation.getEntry
    public void retrieveEntry() {
        SqlOperation queryObj = new SqlOperation();
        entry = queryObj.getEntry(entryDT);
    }

    public void writeTempFile() throws IOException {
        BufferedWriter out = null;
        try {
            FileWriter fstream = new FileWriter("/target/out.txt", true);
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

    public void writeFileOut() {
        try {
            FileWriter writer = new FileWriter("./target/out.txt", true);
            writer.write(entry);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error in EditEntry.WriteFileOut: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void openTextEditor() {
        try {
            File file = new File("./target/out.txt");
            if (!Desktop.isDesktopSupported()) // check if Desktop is supported by Platform or not
            {
                System.out.println("opening a text editor not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (file.exists())
                desktop.open(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitForUser() {
        BufferedReader dummyInput = new BufferedReader(new InputStreamReader(System.in));
        try {
            dummyInput.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        File file = new File("./target/out.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                updatedEntry = updatedEntry.concat(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error in EditEntry.ReadFile: " + e.getMessage());
            e.printStackTrace();
        }
        
    }

    public void updateEntry() {
        this.readFile();
        SqlOperation sqlObj = new SqlOperation();
        sqlObj.updateEntry(entryDT, updatedEntry);
    }
}