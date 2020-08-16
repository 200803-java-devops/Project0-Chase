package com.revature;

import java.io.IOException;
import java.util.Map;

public class ActivateSelection {
    private int selection;
    private String beginDate;

    public ActivateSelection(int s) {
        selection = s;
    }

    public void activate() {
        if (selection == 1) {
            // TEMPORARY SWITCH to JournalEntryCopy from JournalEntry
            JournalEntry entryObj = new JournalEntry();
            entryObj.assembleEntry();
        }
        if (selection == 2) {
            System.out.println("Please enter the date you would like to view entries from (yyyy-MM-dd): ");
            try {
                beginDate = new JournalInput().queryInput();
            } catch (IOException e) {
                System.err.println("There was an IOException in Activate Selection (selection 2).");
            }
            LookUp searchObj = new LookUp(beginDate);
            // Map<String,String> resultMap = searchObj.find();
            // System.out.println("key: " + beginDate);
            // System.out.println("value: " + resultMap.get(beginDate));
            searchObj.lookInTable();
        }
        if (selection == 9) {
            SqlOperation queryObj = new SqlOperation();
            queryObj.dropTable("\"journal_table\"");
        }
    }
}