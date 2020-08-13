package com.revature;

public class DateChecker {
    private String date1;   //cutoff date
    private String date2;   //date to compare to date1
    
    public DateChecker(String d1, String d2) {
        date1 = d1;
        date2 = d2;
    }

    public DateChecker(String d1) {
        date1 = d1;
    }

    public boolean isAfter() {
        //returns true if date2 is after date1
        int month1 = Integer.parseInt(date1.substring(0,2));
        int day1 = Integer.parseInt(date1.substring(3,5));
        int year1 = Integer.parseInt(date1.substring(6,10));
        int month2 = Integer.parseInt(date2.substring(0,2));
        int day2 = Integer.parseInt(date2.substring(3,5));
        int year2 = Integer.parseInt(date2.substring(6,10));
        if ((month2 > month1) || (day2 > day1) || (year2 > year1)) {
            return false;
        }
        return true;
    }

    public boolean isValid() {
        if (date1.length() != 9) {
            System.out.println("Invalid date entered: " + date1);
            return false;
        }
        try {
            int month = Integer.parseInt(date1.substring(0,2));
            // if ((month < 1) || (month > 12)) {
            //     System.out.println("Invalid month entered: " + date1);
            //     return false;
            // }
        } catch(NumberFormatException e1) {
            System.err.println("Invalid month caught in Datechecker.isValid for date: " + date1);
            return false;
        }
        try {
            int day = Integer.parseInt(date1.substring(3,5));   
        } catch(NumberFormatException e2) {
            System.err.println("Invalid day caught in Datechecker.isValid for date: " + date1);
            return false;
        }
        try {
            int year = Integer.parseInt(date1.substring(6,10));
        } catch(NumberFormatException e3) {
            System.err.println("Invalid year caught in Datechecker.isValid for date: " + date1);
            return false;
        }
        return true;
    }
}