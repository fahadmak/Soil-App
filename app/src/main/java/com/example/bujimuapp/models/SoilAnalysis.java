package com.example.bujimuapp.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "soil_analysis_table", foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "id",
        childColumns = "userId",
        onDelete = CASCADE))
public class SoilAnalysis {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "crop_grown")
    private String cropGrown;

    @NonNull
    @ColumnInfo(name = "recommendation")
    private String recommendation;

    @NonNull
    @Embedded
    private SiteInfo siteInfo;

    @NonNull
    @ColumnInfo(name = "date_analyzed")
    private Date date;

    private long userId;

    public SoilAnalysis(int id, @NonNull String cropGrown, @NonNull String recommendation,
                        @NonNull SiteInfo siteInfo, @NonNull Date date, long userId) {
        this.id = id;
        this.cropGrown = cropGrown;
        this.recommendation = recommendation;
        this.siteInfo = siteInfo;
        this.date = date;
        this.userId = userId;
    }

    public SoilAnalysis(@NonNull SiteInfo siteInfo,
                        @NonNull String cropGrown, @NonNull String recommendation,
                        @NonNull Date date, long id) {
        userId = id;
        this.siteInfo = siteInfo;
        this.cropGrown = cropGrown;
        this.recommendation = recommendation;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getCropGrown() {
        return cropGrown;
    }

    public void setCropGrown(@NonNull String cropGrown) {
        this.cropGrown = cropGrown;
    }

    @NonNull
    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(@NonNull String recommendation) {
        this.recommendation = recommendation;
    }

    @NonNull
    public SiteInfo getSiteInfo() {
        return siteInfo;
    }

    public void setSiteInfo(@NonNull SiteInfo siteInfo) {
        this.siteInfo = siteInfo;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
