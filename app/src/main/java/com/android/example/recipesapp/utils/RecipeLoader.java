package com.android.example.recipesapp.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.android.example.recipesapp.model.Recipe;

import java.util.ArrayList;

public class RecipeLoader extends AsyncTaskLoader<ArrayList<Recipe>> {

    private String mUrl;

    public RecipeLoader(Context context, String url){
        super(context);
        mUrl = url;
    }
    @Override
    protected void onStartLoading(){forceLoad();}

    @Nullable
    @Override
    public ArrayList<Recipe> loadInBackground() {
        if(mUrl==null){
            return null;
        }
        ArrayList<Recipe> recipeList = (ArrayList<Recipe>) Utils.fetchRecipeData(mUrl);
        return recipeList;
    }
}

