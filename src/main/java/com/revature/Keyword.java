package com.revature;

public class Keyword {
    private String matchPhrase;

    public Keyword(String p) {
        matchPhrase = p;
    }

    public void search() {
        SqlOperation queryObj = new SqlOperation();
        queryObj.searchByKeyword(matchPhrase);
    }
}