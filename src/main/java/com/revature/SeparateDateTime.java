package com.revature;

public class SeparateDateTime {
    private String date;
    private String time;

    public SeparateDateTime(String dt) {
        date = dt.substring(0, 10);
        time = dt.substring(11, 19);
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}