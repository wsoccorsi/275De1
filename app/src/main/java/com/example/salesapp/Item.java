package com.example.salesapp;

import java.util.UUID;

public class Item {
    private UUID mId;
    private String mItemDesc;
    private String mBrand;
    private String mCondition;
    
    public Item() {
        mId = UUID.randomUUID();
    }

    public UUID getmId() {
        return mId;
    }

    public void setmId(UUID mId) {
        this.mId = mId;
    }

    public String getmItemDesc() {
        return mItemDesc;
    }

    public void setmItemDesc(String mItemDesc) {
        this.mItemDesc = mItemDesc;
    }


    public String getmBrand() {
        return mBrand;
    }

    public void setmBrand(String mBrand) {
        this.mBrand = mBrand;
    }

    public String getmCondition() {
        return mCondition;
    }

    public void setmCondition(String mCondition) {
        this.mCondition = mCondition;
    }
}
