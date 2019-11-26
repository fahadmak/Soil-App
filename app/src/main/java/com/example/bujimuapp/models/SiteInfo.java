package com.example.bujimuapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class SiteInfo implements Parcelable {

    String name;
    String district;
    String subCounty;
    String parish;
    String vilage;
    String reason;
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

    public SiteInfo(String name, String district, String subCounty, String parish, String vilage,
                    String reason, String area, String location, String slopeNature,
                    String soilNature, String degrade, String antHill, String footPath, String tree,
                    String currentCover, String cropType, String vegCover, String dominantCover) {
        this.name = name;
        this.district = district;
        this.subCounty = subCounty;
        this.parish = parish;
        this.vilage = vilage;
        this.reason = reason;
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
        this.name = in.readString();
        this.district = in.readString();
        this.subCounty = in.readString();
        this.parish = in.readString();
        this.vilage = in.readString();
        this.reason = in.readString();
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
        dest.writeString(name);
        dest.writeString(district);
        dest.writeString(subCounty);
        dest.writeString(parish);
        dest.writeString(vilage);
        dest.writeString(reason);
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

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }

    public String getSubCounty() {
        return subCounty;
    }

    public String getParish() {
        return parish;
    }

    public String getVilage() {
        return vilage;
    }

    public String getReason() {
        return reason;
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
