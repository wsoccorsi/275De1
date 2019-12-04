package com.example.salesapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListingLab {
    private static ListingLab sListingLab;
//    private List<Listing>  mListings;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ListingLab get(Context context) {
        if (sListingLab == null) {
            sListingLab = new ListingLab(context);
        }
        return sListingLab;
    }

    public Listing getListing(UUID id){
        ListingCursorWrapper cursor = queryListings(
                ListingDBSchema.ListingTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getListing();
        } finally {
            cursor.close();
        }
    }

    public void updateListing(Listing listing){
        String uuidString = listing.getmId().toString();
        ContentValues values = getContentValues(listing);

        mDatabase.update(ListingDBSchema.ListingTable.NAME, values,
                ListingDBSchema.ListingTable.Cols.UUID + " = ?",
                new String[] { uuidString });

    }

    private ListingCursorWrapper queryListings(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                ListingDBSchema.ListingTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new ListingCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Listing listing) {
        ContentValues values = new ContentValues();
        values.put(ListingDBSchema.ListingTable.Cols.UUID, listing.getmId().toString());
        values.put(ListingDBSchema.ListingTable.Cols.TITLE, listing.getmTitle());
        values.put(ListingDBSchema.ListingTable.Cols.DATE, listing.getmDate().getTime());
        values.put(ListingDBSchema.ListingTable.Cols.SOLD, listing.ismSold() ? 1 : 0);
        values.put(ListingDBSchema.ListingTable.Cols.DESC, listing.getmDesc());
        values.put(ListingDBSchema.ListingTable.Cols.PRICE, listing.getmPrice());


        return values;

    }

    public void addListing(Listing l){
    ContentValues values = getContentValues(l);
    mDatabase.insert(ListingDBSchema.ListingTable.NAME, null, values);
    }

    public int deleteListing(Listing listing) {
        String uuidString = listing.getmId().toString();
        return mDatabase.delete(
                ListingDBSchema.ListingTable.NAME,
                ListingDBSchema.ListingTable.Cols.UUID + " = ?",
                new String[] { uuidString }
        );
    }


    public List<Listing> getListings() {
        List<Listing> listings = new ArrayList<>();

        ListingCursorWrapper cursor = queryListings(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                listings.add(cursor.getListing());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
//        System.out.println("LISTINGS " + listings.get(0).);
        return listings;
    }

    private ListingLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ListingBaseHelper(mContext).getWritableDatabase();
//        mListings = new ArrayList<>();

//        for (int i = 1; i < 100; i++){
//            Listing listing = new Listing();
//            listing.setmTitle("Chair #" + i);
//            listing.setmSold(i%2 ==0);
//            int chairBefore = i-1;
//            int price = i*2;
//            listing.setmDesc("This chair is better than chair " + chairBefore);
//            listing.setmPrice("$" + price);
//            mListings.add(listing);
//        }
    }


}
