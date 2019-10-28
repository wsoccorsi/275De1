package com.example.salesapp;

import java.util.Date;
import java.util.UUID;

public class Listing {

    private Item item;
    private Date mDate;
    private boolean mSold;
    private Integer mPrice;
    private String mImagePath;
    private String mContactInformation;
    private String mTitle;



    public Listing() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getmId() {
        return mId;
    }

    public void setmId(UUID mId) {
        this.mId = mId;
    }

    private UUID mId;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean ismSold() {
        return mSold;
    }

    public void setmSold(boolean mSold) {
        this.mSold = mSold;
    }

    public Integer getmPrice() {
        return mPrice;
    }

    public void setmPrice(Integer mPrice) {
        this.mPrice = mPrice;
    }

    public String getmImagePath() {
        return mImagePath;
    }

    public void setmImagePath(String mImagePath) {
        this.mImagePath = mImagePath;
    }

    public String getmContactInformation() {
        return mContactInformation;
    }

    public void setmContactInformation(String mContactInformation) {
        this.mContactInformation = mContactInformation;
    }
    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
