package com.example.salesapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static javax.xml.transform.OutputKeys.VERSION;

public class ListingBaseHelper extends SQLiteOpenHelper {
    private static final int Version = 1;
    private static final String DATABASE_NAME = "listingBase.db";

    public ListingBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
