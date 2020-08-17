package com.revature;

import java.io.IOException;
import java.util.Map;

public class ActivateSelection {
    private int selection;
    private String dateInput;
    private String keywordInput;

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
            System.out.println("Please enter the date you would like to view entries from in this format (without parentheses): (yyyy-MM-dd). ");
            System.out.println("Or, to view all entries in a time window, enter two dates in this format (without parentheses): (yyyy-MM-dd,yyyy-MM-dd).");
            try {
                dateInput = new JournalInput().queryInput();
                DateChecker checkerObj = new DateChecker(dateInput);
                if (checkerObj.runAll()) {
                    System.out.println("The date(s) was/were valid.");
                }
            } catch (IOException e) {
                System.err.println("There was an IOException in Activate Selection (selection 2).");
            }
            DateChecker checkerObj = new DateChecker(dateInput);
            checkerObj.runAll();
            if (dateInput.length() == 10) {
                LookUp searchObj = new LookUp(checkerObj.getDate1());
                searchObj.lookInTable();
            } else if (dateInput.length() == 21) {
                LookUp searchObj = new LookUp(checkerObj.getDate1(), checkerObj.getDate2());
                searchObj.lookInTable();
            }
            
            // Map<String,String> resultMap = searchObj.find();
            // System.out.println("key: " + dateInput);
            // System.out.println("value: " + resultMap.get(dateInput));
            
        }
        if (selection == 3) {
            System.out.println("Please enter the keyword phrase you would like to search for:");
            try {
                keywordInput = new JournalInput().queryInput();
                Keyword keywordObj = new Keyword(keywordInput);
                keywordObj.search();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (selection == 9) {
            SqlOperation queryObj = new SqlOperation();
            queryObj.dropTable("\"journal_table\"");
        }
    }
}