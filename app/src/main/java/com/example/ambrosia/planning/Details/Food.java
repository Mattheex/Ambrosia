package com.example.ambrosia.planning.Details;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Food implements Parcelable {
    private final String name;
    private Integer cal;
    private Map<String, Integer> nutriments;

    public Food(String name, Integer cal) {
        this.name = name;
        this.cal = cal;
        nutriments = new HashMap<>();
    }

    protected Food(Parcel in) {
        this.name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }

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

    public String getName() {
        return name;
    }

    public Integer getCal() {
        return cal;
    }

    public Map<String, Integer> getNutriments() {
        return nutriments;
    }
}
