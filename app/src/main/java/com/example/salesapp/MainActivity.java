package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {

    private static final String EXTRA_LISTING_ID = "com.bignerdranch.android.salesapp.listing_id";

    public static Intent newIntent(Context packageContext, UUID crimeID) {

        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_LISTING_ID, crimeID);
        return intent;

    }

    @Override
    protected Fragment createFragment() {
        UUID listingId = (UUID) getIntent().getSerializableExtra(EXTRA_LISTING_ID);
        return ListingFragment.newInstance(listingId);
    }

}
