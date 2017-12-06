package com.example.tehhuzzlenut.ripebananas;

/**
 * Created by TehHuzzlenut on 12/6/2017.
 */

public class Food {

    // Store the id of the food
    private int mImageDrawable;
    // Store the name of the food
    private String mFoodName;
    // Store the release date food
    private String mFoodExpDate;

    // Constructor that is used to create an instance of the Food object
    public Food(int mImageDrawable, String mFoodName, String mFoodExpDate)
    {
        this.mImageDrawable = mImageDrawable;
        this.mFoodName = mFoodName;
        this.mFoodExpDate = mFoodExpDate;
    }

    public int getmImageDrawable() {
        return mImageDrawable;
    }

    public void setmImageDrawable(int mImageDrawable) {
        this.mImageDrawable = mImageDrawable;
    }

    public String getmName() {
        return mFoodName;
    }

    public void setmName(String mFoodName) {
        this.mFoodName = mFoodName;
    }

    public String getmRelease() {
        return mFoodExpDate;
    }

    public void setmFoodExpDate(String mFoodExpDate) {
        this.mFoodExpDate = mFoodExpDate;
    }
}
