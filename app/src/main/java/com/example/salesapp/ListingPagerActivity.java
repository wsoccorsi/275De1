package com.example.salesapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class ListingPagerActivity extends AppCompatActivity {
    private static final String EXTRA_LISTING_ID = "com.bignerdranch.android.salesapp.listing_id";

    private ViewPager mViewPager;
    private List<Listing> mListings;

    public static Intent newIntent(Context packageContext, UUID listingId){
        Intent intent = new Intent(packageContext, ListingPagerActivity.class);
        intent.putExtra(EXTRA_LISTING_ID, listingId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_pager);
        UUID listingId = (UUID) getIntent().getSerializableExtra(EXTRA_LISTING_ID);

        mViewPager = (ViewPager) findViewById(R.id.listing_view_pager);

        mListings = ListingLab.get(this).getListings();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Listing listing = mListings.get(position);
                System.out.println(position);
                return ListingFragment.newInstance(listing.getmId());

            }

            @Override
            public int getCount() {
                return mListings.size();
            }


        });

        for (int i = 0; i < mListings.size(); i++){
            if (mListings.get(i).getmId().equals(listingId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }




    }
}
