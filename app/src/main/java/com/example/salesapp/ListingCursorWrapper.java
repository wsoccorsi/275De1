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
        String desco = getString(getColumnIndex(ListingDBSchema.ListingTable.Cols.DESC));
        String price = getString(getColumnIndex(ListingDBSchema.ListingTable.Cols.PRICE));


        Listing listing = new Listing(UUID.fromString(uuidString));
        System.out.println("CHECKING DB " + uuidString);
        listing.setmPrice(price);
        listing.setmTitle(title);
        listing.setmDate(new Date(date));
        listing.setmSold(isSold != 0);

        listing.setmDesc(desco);

        return listing;

    }

}

