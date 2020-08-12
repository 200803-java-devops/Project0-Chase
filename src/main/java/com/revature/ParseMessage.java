package com.revature;

import java.util.ArrayList;

public class ParseMessage {
    public ArrayList<String> entryArray = new ArrayList<String>();
    public String entry;
    
    public ParseMessage(String message) {
        entry = message;
    }

    public ArrayList<String> seperateSentences() {
        int index = 0;
        double i1, i2, i3;
        while (((entry.indexOf(". ", index) != -1) || (entry.indexOf("? ", index) != -1) || (entry.indexOf("! ", index) != -1)) && index < entry.length()) {
            if (entry.indexOf(". ", index) != -1) {
                i1 = entry.indexOf(". ", index);
            } else {
                i1 = Double.POSITIVE_INFINITY;
            }
            if (entry.indexOf("? ", index) != -1) {
                i2 = entry.indexOf("? ", index);
            } else {
                i2 = Double.POSITIVE_INFINITY;
            }
            if (entry.indexOf("! ", index) != -1) {
                i3 = entry.indexOf("! ", index);
            } else {
                i3 = Double.POSITIVE_INFINITY;
            }          
            int nextEnd = (int) Math.min(i1, Math.min(i2, i3));
            entryArray.add(entry.substring(index, nextEnd));
            index = nextEnd + 1;
        }
        if (!(entry.substring(index).equals("") || entry.substring(index).equals(" "))) {
            entryArray.add(entry.substring(index));
        }
        return entryArray;
    }
}