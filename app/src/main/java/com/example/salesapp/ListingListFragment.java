package com.example.salesapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class ListingListFragment extends Fragment {
    private RecyclerView mListingRecyclerView;
    private ListingAdapter mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_listing_list, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_listing_list, container, false);

        mListingRecyclerView = (RecyclerView) view.findViewById(R.id.listing_recycler_view);
        mListingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }
    private void updateUI() {
        ListingLab listingLab = ListingLab.get(getActivity());
        List<Listing> listings = listingLab.getListings();

        mAdapter = new ListingAdapter(listings);
        mListingRecyclerView.setAdapter(mAdapter);
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
            System.out.println(mListing.getmId());

            mTitleTextView.setText(mListing.getmTitle());
            mDescTextView.setText(mListing.getmDesc());
            mPriceTextView.setText(mListing.getmPrice());
            mDateTextView.setText(mListing.getmDate().toString());
            mSoldImageView.setVisibility(listing.ismSold() ? View.VISIBLE : View.GONE);


        }
        @Override
        public void onClick(View view){
            Intent intent = MainActivity.newIntent(getActivity(), mListing.getmId());
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
    }


}
