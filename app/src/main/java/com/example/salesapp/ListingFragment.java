package com.example.salesapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class ListingFragment extends Fragment {

    private static final String ARG_LISTING_ID = "listing_id";
    private Listing mListing;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSoldCheckBox;
    private TextView mSold;
    private EditText mDesc;
    private EditText mPrice;

    public static ListingFragment newInstance(UUID listingID){
        Bundle args = new Bundle();
        args.putSerializable(ARG_LISTING_ID, listingID);

        ListingFragment fragment = new ListingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        UUID listingId = (UUID) getArguments().getSerializable(ARG_LISTING_ID);
        System.out.println("Checking this ID " + listingId);
        mListing = ListingLab.get(getActivity()).getListing(listingId);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_listing, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.delete_listing:
                UUID listingId = (UUID) getArguments().getSerializable(ARG_LISTING_ID);
                ListingLab listingLab = ListingLab.get(getActivity());
                mListing = listingLab.getListing(listingId);
                listingLab.deleteListing(mListing);
                try {
                    getActivity().finish();
                } catch (NullPointerException e) {
                    return true;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        ListingLab.get(getActivity()).updateListing(mListing);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listing, container, false);
        mDateButton = (Button) v.findViewById(R.id.listing_date);
        mDateButton.setText("Posted: " + mListing.getmDate().toString());
        mDateButton.setEnabled(false);

        mDesc = (EditText) v.findViewById(R.id.listing_desc);
        mDesc.setText("  Desc: " + mListing.getmDesc());

        mPrice = (EditText) v.findViewById(R.id.listing_price);
        mPrice.setText("  Price " + mListing.getmPrice());

//        mSold = (TextView) v.findViewById(R.id.listing_textSold);
        mSoldCheckBox = (CheckBox)v.findViewById(R.id.listing_sold);
        System.out.println("is sold" + mListing.ismSold());
        mSoldCheckBox.setSelected(mListing.ismSold());
//        if (mListing.ismSold()) {
//            mSold.setText("  Status: Sorry this item has been sold");
//        } else {
//            mSold.setText("  Status: This item is still for sale");
//        }
        mSoldCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mListing.setmSold(isChecked);
            }
        });

        mTitleField = (EditText) v.findViewById(R.id.listing_title);
        mTitleField.setText("  " + mListing.getmTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mListing.setmTitle(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mListing.setmPrice(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mListing.setmDesc(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

//        private void deleteListing() {
//            ListingLab listingLab = ListingLab.get(getActivity());
//            listingLab.deleteListing(mListing);
//
//        }


        return v;
    }
}
