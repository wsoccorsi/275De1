package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {

    public static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";

    public static Intent newIntent(Context packageContext, UUID crimeID) {

        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeID);
        return intent;

    }

    @Override
    protected Fragment createFragment() {
        return new ListingFragment();
    }

}
