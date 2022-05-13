package com.example.ambrosia.planning.Details;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

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
    private String image;
    private String urlFood;
    private String source;
    private Integer cal;
    private List<String> ingredientList;
    private List<String> nutrients;

    public Food(String name, Integer cal, String image, String urlFood, String source, List<String> ingredientList, List<String> nutrients) {
        this.name = name;
        this.cal = cal;
        this.image = image;
        this.urlFood = urlFood;
        this.source = source;
        this.ingredientList = ingredientList;
        this.nutrients = nutrients;
    }

    protected Food(Parcel in) {
        this.name = in.readString();
        this.cal = in.readInt();
        this.image = in.readString();
        this.urlFood = in.readString();
        this.source = in.readString();
        this.ingredientList = in.createStringArrayList();
        this.nutrients = in.createStringArrayList();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(cal);
        parcel.writeString(image);
        parcel.writeString(urlFood);
        parcel.writeString(source);
        parcel.writeStringList(ingredientList);
        parcel.writeStringList(nutrients);

    }

    public String getName() {
        return name;
    }

    public Integer getCal() {
        return cal;
    }

    public String getImage() {
        return image;
    }

    public String getSource() {
        return source;
    }

    public String getUrlFood() {
        return urlFood;
    }

    public List<String> getIngredientList() {
        return ingredientList;
    }

    public List<String> getNutrients() {
        return nutrients;
    }
}
