 package com.android.example.recipesapp;

 import android.content.Context;
 import android.net.ConnectivityManager;
 import android.net.NetworkInfo;
 import android.net.Uri;
 import android.os.Bundle;
 import android.support.annotation.NonNull;
 import android.support.annotation.Nullable;
 import android.support.v4.app.LoaderManager;
 import android.support.v4.content.Loader;
 import android.support.v7.app.AppCompatActivity;
 import android.widget.Toast;

 import com.android.example.recipesapp.model.Recipe;
 import com.android.example.recipesapp.utils.RecipeLoader;

 import java.util.ArrayList;

/* Make A Baking App (Project 4-AND)

Internet permissions and network state permissions are required in AndroidManifest.xml:
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

Project mock-up:
https://classroom.udacity.com/nanodegrees/nd801/parts/ec45ffe9-2c4e-4b8d-ad76-d80c5905d926/modules/
015a9039-5994-4242-bed8-fe2610047d8f/lessons/a38bc34d-8c5f-468f-8562-0aed7fba6d5a/concepts/
16d87c7d-a648-4c0c-be4e-e88fb5f1ac6b

Project rubric:
https://review.udacity.com/#!/rubrics/829/view


TODO App should display recipes from provided network resource.
https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json

TODO App should allow navigation between individual recipes and Recipe steps.

TODO App uses RecyclerView and can handle Recipe steps that include videos or images.

TODO App conforms to common standards found in the Android Nanodegree General Project Guidelines.

TODO Application uses Master Detail Flow to display Recipe steps and navigation between them.

TODO Application uses Exoplayer to display videos.

TODO Application properly initializes and releases video assets when appropriate.

TODO Application should properly retrieve media assets from the provided network links.

TODO It should properly handle network requests.

TODO Application makes use of Espresso to test aspects of the UI.

TODO Application sensibly utilizes a third-party library to enhance the app's features.
That could be helper library to interface with ContentProviders if you choose to store the recipes,
a UI binding library to avoid writing findViewById a bunch of times, or something similar.

TODO Application has a companion homescreen widget.

TODO Widget displays Ingredient list for desired Recipe.

Example code adapted from:
Sections of code functionality modified from NewsApp Stage 2 (ABND Project 6) and
https://medium.com/@sanjeevy133/an-idiots-guide-to-android-asynctaskloader-76f8bfb0a0c0
implementing background thread call to recipe json data.
*/


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Recipe>> {

    private static final String RECIPE_URL =
            "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private static final int RECIPE_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(RECIPE_LOADER_ID, null, this);
        } else {
            Toast.makeText(this, "No internet connection found.\\nPlease try again later.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public Loader<ArrayList<Recipe>> onCreateLoader(int i, @Nullable Bundle bundle) {
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // Build Uri

            Uri baseUri = Uri.parse(RECIPE_URL);

            return new RecipeLoader(getApplicationContext(), baseUri.toString());
        } else {
            // Otherwise, display error
            // Update empty state with no connection error message
            Toast.makeText(this, "No internet connection found.\\nPlease try again later.",
                    Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Recipe>> loader, ArrayList<Recipe> recipes) {
    // load data into recycler view adapter, etc - TODO implement Master/Detail flow
        Toast.makeText(this, "Load Finished",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Recipe>> loader) {
        // clear recycler view adapter, etc - TODO implement Master/Detail flow
        Toast.makeText(this, "Reset Loader",
                Toast.LENGTH_SHORT).show();

    }
}
