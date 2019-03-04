package com.h.mechanicalengineering.utilse;

import android.os.Parcel;
import android.os.Parcelable;

public class LessonsModel implements Parcelable{
    private String name;

    public LessonsModel(Parcel in) {
        name = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LessonsModel(String name) {
        this.name = name;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LessonsModel> CREATOR = new Creator<LessonsModel>() {
        @Override
        public LessonsModel createFromParcel(Parcel in) {
            return new LessonsModel(in);
        }

        @Override
        public LessonsModel[] newArray(int size) {
            return new LessonsModel[size];
        }
    };
}