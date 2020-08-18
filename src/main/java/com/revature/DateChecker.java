package com.revature;
/**
 * DateChecker will check if the user input dates in the correct format
 */
public class DateChecker {
    private String date1; // cutoff date
    private String date2; // date to compare to date1
    private String dateInput;

    public DateChecker(String input) {
        dateInput = input;
    }

    /**
     * checkLength is for measuring the input
     * @return integer corresponding to the number of correct dates entered by the user
     */
    public int checkLength() {
        if (dateInput.length() == 10) {
            return 1;
        }
        if (dateInput.length() == 21) {
            return 2;
        }
        return 0;
    }

    /**
     * isAfter checks to see the order of two dates
     * @return true if the first date is is chronologically before the second
     */
    public boolean isAfter() {
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

    /**
     * singleFormat checks the format of a date from the user input
     * @param d the date acquired from user input
     * @return true if the date is in the correct format
     */
    public boolean singleFormat(String d) {
        if (!(d.substring(4,5).equals("-")) || !(d.substring(7,8).equals("-")) || !(d.length() == 10)) {
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

    /**
     * separate sets the class fields date1 and date2 based on the result of checkLength
     * @return true if the dates were in valid format
     */
    public boolean separate() {
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

    /**
     * runs the individual checks on the date
     * @return true if one or two dates were entered in a valid format with the second date not occurring before the first
     */
    public boolean runAll() {

        if (separate() == false) {
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