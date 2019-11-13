package com.example.bujimuapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class FarmInfo implements Parcelable {
    String name;
    String district;
    String subCounty;
    String parish;
    String vilage;
    String reason;

    public FarmInfo(String name, String district, String subCounty, String parish, String vilage,
                    String reason) {
        this.name = name;
        this.district = district;
        this.subCounty = subCounty;
        this.parish = parish;
        this.vilage = vilage;
        this.reason = reason;
    }

    protected FarmInfo(Parcel in) {
        name = in.readString();
        district = in.readString();
        subCounty = in.readString();
        parish = in.readString();
        vilage = in.readString();
        reason = in.readString();
    }

    public static final Creator<FarmInfo> CREATOR = new Creator<FarmInfo>() {

        @Override
        public FarmInfo createFromParcel(Parcel in) {
            return new FarmInfo(in);
        }

        @Override
        public FarmInfo[] newArray(int size) {
            return new FarmInfo[size];
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
}

