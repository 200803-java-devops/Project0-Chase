package com.revature;
/**
 * Keyword's search method will call SqlOperation to retrieve and print any entries containing user's keyword phrase.
 */
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