package com.android.example.recipesapp.model;

/* The Recipe class holds the parsed json data for a recipe

Each Recipe has these attributes:

id              integer starting at 1
name            string
ingredients     See Ingredient class
steps           See RecipeStep class
servings        integer
image           string/url

Example code modified to implement in.readArrayList:
https://www.codota.com/code/java/methods/android.os.Parcel/readArrayList

 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Recipe implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    private Integer id;                         // (integer starting at 1)
    private String name;                        // (string)
    private ArrayList<Ingredient> ingredients;  // (list / json array)
    private ArrayList<RecipeStep> recipeSteps;  // (list / jason array)
    private Integer servings;                   // (integer)
    private String image;                       // (string / url)

    public Recipe(Integer id, String name, ArrayList<Ingredient> ingredients, ArrayList<RecipeStep> recipeSteps
            , Integer servings, String image) {

        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.recipeSteps = recipeSteps;
        this.servings = servings;
        this.image = image;
    }

    // Getter and Setter Methods

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<RecipeStep> getRecipeSteps() {
        return recipeSteps;
    }

    public Integer getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setRecipeSteps(ArrayList<RecipeStep> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Recipe(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.ingredients = in.readArrayList(Object.class.getClassLoader());
        this.recipeSteps = in.readArrayList(Object.class.getClassLoader());
        this.servings = in.readInt();
        this.image = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
