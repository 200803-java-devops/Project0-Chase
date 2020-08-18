package com.revature;

public class DateChecker {
    private String date1; // cutoff date
    private String date2; // date to compare to date1
    private String dateInput;

    public DateChecker(String input) {
        dateInput = input;
    }

    public int checkLength() {
        // returns 1 if the length of dateInput is 10, returns 2 if the length is 21, returns 0 otherwise
        if (dateInput.length() == 10) {
            return 1;
        }
        if (dateInput.length() == 21) {
            return 2;
        }
        return 0;
    }

    public boolean isAfter() {
        //returns true if date2 is after date1
        try {
            int year1 = Integer.parseInt(date1.substring(0,4));
            int month1 = Integer.parseInt(date1.substring(5,7));
            int day1 = Integer.parseInt(date1.substring(8,10));
            int year2 = Integer.parseInt(date2.substring(0,4));
            int month2 = Integer.parseInt(date2.substring(5,7));
            int day2 = Integer.parseInt(date2.substring(8,10));
            if ((month1 > month2) || (day1 > day2) || (year1 > year2)) {
                return false;
            }
        } catch(NumberFormatException e) {
            System.err.println("Invalid date found in DateChecker.isAfter(): " + date1 + " or " + date2);
        }
        return true;
    }

    public boolean singleFormat(String d) {
        //returns true if a single date was entered in the correct format
        if (!(d.substring(4,5).equals("-")) || !(d.substring(7,8).equals("-")) || !(d.length() == 10)) {
            //System.out.println("An invalid date format was detected. Closing application...");
            //System.out.println("d.substring(4,5) = " + d.substring(4,5));
            //System.out.println("d.substring(7,8) = " + d.substring(7,8));
            //System.out.println("d.length() = " + d.length());
            return false;
        }
        try {
            Integer.parseInt(d.substring(0,4));
            Integer.parseInt(d.substring(5,7));
            Integer.parseInt(d.substring(8, 10));
        } catch (NumberFormatException e) {
            System.out.println("An invalid date format was detected. Closing application...");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean seperate() {
        //returns true if the user's date was able to be seperated
        int amount = checkLength();
        if (amount == 1) {
            date1 = dateInput;
            date2 = dateInput;
            return true;
        } else if (amount == 2) {
            date1 = dateInput.substring(0,10);
            date2 = dateInput.substring(11, 21);
            return true;
        }
        else {
            System.out.println("The date input was not valid");
            return false;
        }
    }

    public boolean runAll() {
        // returns true if one or two dates were entered in a valid format with the second date not occurring before the first
        if (seperate() == false) {
            return false;
        }
        if ((singleFormat(date1) == false) || (singleFormat(date2) == false )) {
            return false;
        }
        if ((checkLength() == 2) && (isAfter() == false)) {
            return false;
        }
        return true;
    }

    public String getDate1() {
        return date1;
    }

    public String getDate2() {
        return date2;
    }
}