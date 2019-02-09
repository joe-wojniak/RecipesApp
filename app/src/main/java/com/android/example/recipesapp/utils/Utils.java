package com.android.example.recipesapp.utils;

import android.util.Log;

import com.android.example.recipesapp.model.Ingredient;
import com.android.example.recipesapp.model.Recipe;
import com.android.example.recipesapp.model.RecipeStep;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static final String LOG_TAG = Utils.class.getSimpleName();
    
    /** json keys
     */
    
    private static String KEY_RECIPE_ID = "id";
    private static String KEY_NAME = "name";
    private static String KEY_INGREDIENTS = "ingredients";
    private static String KEY_STEPS = "steps";
    private static String KEY_SERVINGS = "servings";
    private static String KEY_IMAGE = "image";
    private static String KEY_QTY = "quantity";
    private static String KEY_MEASURE = "measure";
    private static String KEY_INGREDIENT = "ingredient";
    private static String KEY_STEP_ID = "id";
    private static String KEY_SHORT_DESCRIPTION = "shortDescription";
    private static String KEY_DESCRIPTION = "description";
    private static String KEY_VIDEO_URL = "videoURL";
    private static String KEY_THUMBNAIL_URL = "thumbnailURL";

    private Utils() {
    }

    /**
     * Query the recipe url and return {@link com.android.example.recipesapp.model.Recipe} objects.
     */
    public static List<Recipe> fetchRecipeData(String requestUrl) {
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        List<Recipe> recipeList = extractFeatureFromJson(jsonResponse);

        return recipeList;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the JSON results. ", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static List<Recipe> extractFeatureFromJson(String json) {

        ArrayList<Recipe> recipeList = new ArrayList<>();
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        ArrayList<RecipeStep> recipeStepList = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(json);
            JSONArray resultsArray = baseJsonResponse.optJSONArray(KEY_RECIPE_ID);
            JSONArray ingredientsArray = baseJsonResponse.optJSONArray(KEY_INGREDIENTS);
            JSONArray stepsArray = baseJsonResponse.optJSONArray(KEY_STEPS);

            // looping through the page results
            for (int i = 0; i < resultsArray.length(); i++) {
                // Extract out the first result (which is a recipe)
                JSONObject firstResult = resultsArray.getJSONObject(i);

                // Extract out values from json response for recipe
                Integer id = firstResult.optInt(KEY_RECIPE_ID,0);
                String recipeName = firstResult.optString(KEY_NAME,"n/a");
                Integer servings = firstResult.optInt(KEY_SERVINGS, 0);
                String image = firstResult.optString(KEY_IMAGE, "n/a");

                // Extract out values from json response for ingredients

                for (int j = 0; j < ingredientsArray.length(); j++){
                    JSONObject ingredients = ingredientsArray.getJSONObject(i);
                    Integer quantity = ingredients.optInt(KEY_QTY,0);
                    String measure = ingredients.optString(KEY_MEASURE,"n/a");
                    String ingredientDescription = ingredients.optString(KEY_INGREDIENT,"n/a");

                    // make new Ingredient object
                    // add object to list

                    Ingredient ingredient = new Ingredient(quantity, measure, ingredientDescription);
                    ingredientList.add(ingredient);
                }

                // Extract out values from json response for recipe steps

                for (int k = 0; k < stepsArray.length(); k++){
                    JSONObject steps = stepsArray.getJSONObject(k);
                    Integer step_id = steps.optInt(KEY_STEP_ID, 0);
                    String shortDescription = steps.optString(KEY_SHORT_DESCRIPTION, "n/a");
                    String description = steps.optString(KEY_DESCRIPTION, "n/a");
                    String videoURL = steps.optString(KEY_VIDEO_URL, "n/a");
                    String thumbnailURL = steps.optString(KEY_THUMBNAIL_URL, "n/a");

                    // make new RecipeStep object
                    // add object to list

                    RecipeStep recipeStep = new RecipeStep(step_id, shortDescription, description,
                            videoURL, thumbnailURL);
                    recipeStepList.add(recipeStep);
                }

                // make new Recipe object
                // add object to list

                Recipe recipe = new Recipe(id, recipeName, ingredientList, recipeStepList, servings, image);
                recipeList.add(recipe);
            }

        } catch (final JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the JSON results ", e);
        }
        return recipeList;
    }
}
