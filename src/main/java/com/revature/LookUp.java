package com.revature;
/**
 * LookUp's lookInTable will call SqlOperation to print out all the entries from the given window of dates, or a single date.
 * @param beginDate is the first date in the time window
 * @param cutoffDate is the final date, which is equal to beginDate in the case where only one date is given
 */
public class LookUp {
    // private FileReader reader;
    private String beginDate;
    private String cutoffDate;

    public LookUp(String d) {
        beginDate = d;
        cutoffDate = d;
    }

    public LookUp(String d1, String d2) {
        beginDate = d1;
        cutoffDate = d2;
    }

    public void lookInTable() {
        SqlOperation queryObj = new SqlOperation();
        queryObj.getEntries(beginDate, cutoffDate);
    }
}