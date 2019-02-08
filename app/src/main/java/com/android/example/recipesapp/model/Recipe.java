package com.android.example.recipesapp.model;

/* The Recipe class holds the parsed json data for a recipe

Each Recipe has these attributes:

id              integer starting at 1
name            string
ingredients     See Ingredient class
steps           See RecipeStep class
servings        integer
image           string/url

 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Recipe implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    private Integer id;                     // (integer starting at 1)
    private String name;                    // (string)
    private List<Ingredient> ingredients;   // (list / json array)
    private List<RecipeStep> recipeSteps;   // (list / jason array)
    private Integer servings;               // (integer)
    private String image;                   // (string / url)

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
