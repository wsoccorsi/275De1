package com.example.salesapp;


import android.content.Context;
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
//        for (Listing listing : mListings){
//            if (listing.getmId().equals(id)){
//                return listing;
//            }
//        }
        return null;
    }

    public void addListing(Listing l){
//        mListings.add(l);
    }

    public List<Listing> getListings() {
//        return mListings;
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
