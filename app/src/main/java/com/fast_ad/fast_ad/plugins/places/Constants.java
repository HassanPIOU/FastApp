package com.fast_ad.fast_ad.plugins.places;

/**
 * Created by Hp on 3/18/2016.
 */
public class Constants {
    //COLUMNS
    static final String ROW_ID="id";
    static final String NAME = "name";

    //DB PROPERTIES
    static final String DB_NAME="placesdb";
    static final String TB_NAME="places";


    static final String CREATE_TB ="CREATE TABLE d_TB(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT NOT NULL);";
}
