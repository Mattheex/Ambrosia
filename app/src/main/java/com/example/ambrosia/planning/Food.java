package com.example.ambrosia.planning;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {
    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };
    private String name;
    private int kcal;

    public Food(String name, int kcal) {
        this.name = name;
        this.kcal = kcal;
    }

    protected Food(Parcel in) {
        this.name = in.readString();
        this.kcal = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(kcal);
    }

    public String getName() {
        return name;
    }

    public Integer getKcal() {
        return kcal;
    }
}
