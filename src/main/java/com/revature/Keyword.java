package com.revature;

import java.util.ArrayList;

/**
 * Keyword's search method will call SqlOperation to retrieve and print any
 * entries containing user's keyword phrase.
 */
public class Keyword {
    private String matchPhrase;

    public Keyword(String p) {
        matchPhrase = p;
    }

    public ArrayList<String> search() {
        SqlOperation queryObj = new SqlOperation();
        return queryObj.searchByKeyword(matchPhrase);
    }
}