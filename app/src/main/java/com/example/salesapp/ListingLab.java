package com.example.salesapp;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListingLab {
    private static ListingLab sListingLab;
    private List<Listing>  mListings;

    public static ListingLab get(Context context) {
        if (sListingLab == null) {
            sListingLab = new ListingLab(context);
        }
        return sListingLab;
    }

    public Listing getListing(UUID id){
        for (Listing listing : mListings){
            if (listing.getmId().equals(id)){
                return listing;
            }
        }
        return null;
    }

    public List<Listing> getListings() {
        return mListings;
    }

    private ListingLab(Context context) {
        mListings = new ArrayList<>();
        for (int i = 1; i < 100; i++){
            Listing listing = new Listing();
            listing.setmTitle("Chair #" + i);
            listing.setmSold(i%2 ==0);
            int chairBefore = i-1;
            listing.setmDesc("This chair is better than chair " + chairBefore);
            mListings.add(listing);
        }
    }

}
