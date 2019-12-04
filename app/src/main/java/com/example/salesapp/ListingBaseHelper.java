package com.example.salesapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.salesapp.ListingDBSchema.ListingTable;

import static javax.xml.transform.OutputKeys.VERSION;

public class ListingBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "listingBase.db";

    public ListingBaseHelper(Context context) {

            super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ListingTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ListingTable.Cols.UUID + ", " +
                ListingTable.Cols.TITLE + ", " +
                ListingTable.Cols.DATE + ", " +
                ListingTable.Cols.SOLD + ", " +
                ListingTable.Cols.PRICE + ", " +
                ListingTable.Cols.DESC + ")"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
