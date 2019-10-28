package com.example.salesapp;

import androidx.fragment.app.Fragment;

public class ListingListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new ListingListFragment();
    }
}
