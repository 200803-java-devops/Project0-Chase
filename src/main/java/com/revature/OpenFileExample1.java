package com.revature;

import java.awt.Desktop;
import java.io.File;

public class OpenFileExample1 {
    public void run() {
        try {
            // constructor of file class having file as argument
            File file = new File("./target/out.txt");
            if (!Desktop.isDesktopSupported())// check if Desktop is supported by Platform or not
            {
                System.out.println("not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) // checks file exists or not
                desktop.open(file); // opens the specified file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}