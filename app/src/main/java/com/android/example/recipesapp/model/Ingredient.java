package com.android.example.recipesapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/*  Ingredient holds json array data matching key: ingredients
ingredients     (list / json array)
    quantity    (integer)
    measure     (string)
    Ingredient  (string)
 */

public class Ingredient implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    private Integer quantity;
    private String measure;
    private String ingredientDescription;

    public Ingredient(Integer quantity, String measure, String ingredientDescription) {
        this.ingredientDescription = ingredientDescription;
        this.measure = measure;
        this.quantity = quantity;
    }

    // Getter and Setter methods

    public Integer getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredientDescription() {
        return ingredientDescription;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setIngredientDescription(String ingredientDescription) {
        this.ingredientDescription = ingredientDescription;
    }

    public Ingredient(Parcel in) {
        this.quantity = in.readInt();
        this.measure = in.readString();
        this.ingredientDescription = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.quantity);
        dest.writeString(this.measure);
        dest.writeString(this.ingredientDescription);
    }
}
