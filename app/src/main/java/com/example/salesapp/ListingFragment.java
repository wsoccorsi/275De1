package com.example.salesapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
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
    private TextView mTitleField;
    private Button mDateButton;
    private CheckBox mSoldCheckBox;
    private TextView mSold;
    private TextView mDesc;
    private TextView mPrice;

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
//        UUID listingId = (UUID) getActivity().getIntent().getSerializableExtra(MainActivity.EXTRA_LISTING_ID);
        UUID listingId = (UUID) getArguments().getSerializable(ARG_LISTING_ID);
        mListing = ListingLab.get(getActivity()).getListing(listingId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listing, container, false);
        mDateButton = (Button) v.findViewById(R.id.listing_date);
        mDateButton.setText("Posted: " + mListing.getmDate().toString());
        mDateButton.setEnabled(false);

        mDesc = (TextView) v.findViewById(R.id.listing_desc);
        mDesc.setText("  Desc: " + mListing.getmDesc());

        mPrice = (TextView) v.findViewById(R.id.listing_price);
        mPrice.setText("  Price " + mListing.getmPrice());

        mSold = (TextView) v.findViewById(R.id.listing_textSold);
        mSoldCheckBox = (CheckBox)v.findViewById(R.id.listing_sold);
        if (mListing.ismSold()) {
            mSold.setText("  Status: Sorry this item has been sold");
        } else {
            mSold.setText("  Status: This item is still for sale");
        }
        mSoldCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mListing.setmSold(isChecked);
            }
        });

        mTitleField = (TextView) v.findViewById(R.id.listing_title);
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

        return v;
    }
}
