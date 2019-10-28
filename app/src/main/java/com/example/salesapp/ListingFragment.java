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

import androidx.fragment.app.Fragment;

public class ListingFragment extends Fragment {

    private Listing mListing;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSoldCheckBox;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mListing = new Listing();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listing, container, false);
        mDateButton = (Button) v.findViewById(R.id.listing_date);
        mDateButton.setText(mListing.getmDate().toString());
        mDateButton.setEnabled(false);

        mSoldCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSoldCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mListing.setmSold(isChecked);
            }
        });

        mTitleField = (EditText) v.findViewById(R.id.listing_title);
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
