package com.example.bujimuapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class SiteInfo implements Parcelable {

    private FarmInfo info;
    private String area;
    private String location;
    private String slopeNature;
    private String soilNature;
    private String degrade;
    private String antHill;
    private String footPath;
    private String tree;
    private String currentCover;
    private String cropType;
    private String vegCover;
    private String dominantCover;

    public SiteInfo(FarmInfo info, String area, String location, String slopeNature,
                    String soilNature, String degrade, String antHill, String footPath,
                    String tree, String currentCover, String cropType, String vegCover,
                    String dominantCover) {
        this.info = info;
        this.area = area;
        this.location = location;
        this.slopeNature = slopeNature;
        this.soilNature = soilNature;
        this.degrade = degrade;
        this.antHill = antHill;
        this.footPath = footPath;
        this.tree = tree;
        this.currentCover = currentCover;
        this.cropType = cropType;
        this.vegCover = vegCover;
        this.dominantCover = dominantCover;
    }

    protected SiteInfo(Parcel in) {
        this.info = in.readParcelable(FarmInfo.class.getClassLoader());
        this.area = in.readString();
        this.location = in.readString();
        this.slopeNature = in.readString();
        this.soilNature = in.readString();
        this.degrade = in.readString();
        this.antHill = in.readString();
        this.footPath = in.readString();
        this.tree = in.readString();
        this.currentCover = in.readString();
        this.cropType = in.readString();
        this.vegCover = in.readString();
        this.dominantCover = in.readString();

    }

    public static final Creator<SiteInfo> CREATOR = new Creator<SiteInfo>() {
        @Override
        public SiteInfo createFromParcel(Parcel in) {
            return new SiteInfo(in);
        }

        @Override
        public SiteInfo[] newArray(int size) {
            return new SiteInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(info, flags);
        dest.writeString(area);
        dest.writeString(location);
        dest.writeString(slopeNature);
        dest.writeString(soilNature);
        dest.writeString(degrade);
        dest.writeString(antHill);
        dest.writeString(footPath);
        dest.writeString(tree);
        dest.writeString(currentCover);
        dest.writeString(cropType);
        dest.writeString(vegCover);
        dest.writeString(dominantCover);
    }

    public FarmInfo getInfo() {
        return info;
    }

    public String getArea() {
        return area;
    }

    public String getLocation() {
        return location;
    }

    public String getSlopeNature() {
        return slopeNature;
    }

    public String getSoilNature() {
        return soilNature;
    }

    public String getDegrade() {
        return degrade;
    }

    public String getAntHill() {
        return antHill;
    }

    public String getFootPath() {
        return footPath;
    }

    public String getTree() {
        return tree;
    }

    public String getCurrentCover() {
        return currentCover;
    }

    public String getCropType() {
        return cropType;
    }

    public String getVegCover() {
        return vegCover;
    }

    public String getDominantCover() {
        return dominantCover;
    }
}
