package com.example.salesapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class ListingListFragment extends Fragment {
    private RecyclerView mListingRecyclerView;
    private ListingAdapter mAdapter;
    private boolean mSubtitleVisible;
    private boolean mHiVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
        outState.putBoolean(SAVED_HI_VISIBLE, mHiVisible);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_listing_list, menu);


        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
        MenuItem hiItem = menu.findItem(R.id.show_hi);

        if (mSubtitleVisible){
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }

        if (mHiVisible){
            hiItem.setTitle("Hide Hi");
        } else {
            hiItem.setTitle("Show Hi");
        }

    }
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";
    private static final String SAVED_HI_VISIBLE = "hi";

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.new_listing:
                Listing listing = new Listing();
                ListingLab.get(getActivity()).addListing(listing);
                Intent intent = ListingPagerActivity.newIntent(getActivity(), listing.getmId());
                startActivity(intent);
                return true;
            case R.id.show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            case R.id.show_hi:
                mHiVisible = !mHiVisible;
                getActivity().invalidateOptionsMenu();
                updateHi();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        ListingLab listingLab = ListingLab.get(getActivity());
        int listingCount = listingLab.getListings().size();
        String subtitle = getString(R.string.subtitle_format, listingCount);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);

    }
    private void updateHi() {

        String hello = "Hello";

        if (!mHiVisible) {
            hello = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(hello);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_listing_list, container, false);

        mListingRecyclerView = (RecyclerView) view.findViewById(R.id.listing_recycler_view);
        mListingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstance != null) {
            mSubtitleVisible = savedInstance.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        if (savedInstance != null) {
            mHiVisible = savedInstance.getBoolean(SAVED_HI_VISIBLE);
        }

        updateUI();

        return view;
    }
    private void updateUI() {
        ListingLab listingLab = ListingLab.get(getActivity());
        List<Listing> listings = listingLab.getListings();

        if (mAdapter == null) {
            mAdapter = new ListingAdapter(listings);
            mListingRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setListings(listings);
            mAdapter.notifyDataSetChanged();
        }

        updateSubtitle();
        updateHi();

    }

    private class ListingHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private TextView mDescTextView;
        private TextView mDateTextView;
        private TextView mPriceTextView;

        private ImageView mSoldImageView;
        private Listing mListing;

        public ListingHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_listing, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.listing_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.listing_date);
            mDescTextView = (TextView) itemView.findViewById(R.id.listing_desc);
            mPriceTextView = (TextView) itemView.findViewById(R.id.listing_price);
            mSoldImageView = (ImageView) itemView.findViewById(R.id.listing_sold);

        }

        public void bind(Listing listing) {

            mListing = listing;
            System.out.println("on screen date " + mListing.getmDate() + "on screen id " + mListing.getmId() );
            mTitleTextView.setText("Title: " + mListing.getmTitle());
            mDescTextView.setText("Desc: " + mListing.getmDesc());
            mPriceTextView.setText("Price: $" + mListing.getmPrice());
            mDateTextView.setText("Posted: " + mListing.getmDate().toString());
            mSoldImageView.setVisibility(listing.ismSold() ? View.VISIBLE : View.GONE);


        }
        @Override
        public void onClick(View view){
            Intent intent = ListingPagerActivity.newIntent(getActivity(), mListing.getmId());
            startActivity(intent);
        }
    }

    private class ListingAdapter extends RecyclerView.Adapter<ListingHolder> {
        private List<Listing> mListings;
        public ListingAdapter(List<Listing> listings){
            mListings = listings;
        }

        @Override
        public ListingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ListingHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ListingHolder holder, int position){
            Listing listing = mListings.get(position);
            holder.bind(listing);
        }

        @Override
        public int getItemCount() {
            return mListings.size();
        }

        public void setListings(List<Listing> listings) {
            mListings = listings;
//            System.out.println("check here in setListings" + listings.get(0).getmId());
        }
    }




}
