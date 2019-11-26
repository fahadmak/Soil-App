package com.example.bujimuapp.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "soil_analysis_table")
public class SoilAnalysis {

    @PrimaryKey(autoGenerate = true)
    private int mId;

    @NonNull
    @ColumnInfo(name = "crop_grown")
    private String mCropGrown;

    @NonNull
    @ColumnInfo(name = "recommendation")
    private String mRecommendation;

    @NonNull
    @Embedded
    private SiteInfo mSiteInfo;

    @NonNull
    @ColumnInfo(name = "date_analyzed")
    private Date mDate;

    public SoilAnalysis(int id, @NonNull SiteInfo siteInfo,
                        @NonNull String cropGrown, @NonNull String recommendation, @NonNull Date date) {
        mId = id;
        mSiteInfo = siteInfo;
        mCropGrown = cropGrown;
        mRecommendation = recommendation;
        mDate = date;
    }

    public int getId() {
        return mId;
    }

    public String getCropGrown() {
        return mCropGrown;
    }

    public String getRecommendation() {
        return mRecommendation;
    }

    public SiteInfo getSiteInfo() {
        return mSiteInfo;
    }

    @NonNull
    public Date getDate() {
        return mDate;
    }
}
