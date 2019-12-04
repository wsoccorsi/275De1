package com.example.salesapp;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.sql.Date;
import java.util.UUID;

public class ListingCursorWrapper extends CursorWrapper {

    public ListingCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Listing getListing() {
        String uuidString = getString(getColumnIndex(ListingDBSchema.ListingTable.Cols.UUID));
        String title = getString(getColumnIndex(ListingDBSchema.ListingTable.Cols.TITLE));
        long date = getLong(getColumnIndex(ListingDBSchema.ListingTable.Cols.DATE));
        int isSold = getInt(getColumnIndex(ListingDBSchema.ListingTable.Cols.SOLD));

        Listing listing = new Listing(UUID.fromString(uuidString));
        System.out.println("CHECKING DB " + uuidString);
        listing.setmTitle(title);
        listing.setmDate(new Date(date));
        listing.setmSold(isSold != 0);

        return listing;

    }

}
