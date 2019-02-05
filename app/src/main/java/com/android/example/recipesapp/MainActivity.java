package com.android.example.recipesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/* Make A Baking App (Project 4-AND)

TODO App should display recipes from provided network resource.
https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json

TODO App should allow navigation between individual recipes and recipe steps.

TODO App uses RecyclerView and can handle recipe steps that include videos or images.

TODO App conforms to common standards found in the Android Nanodegree General Project Guidelines.

TODO Application uses Master Detail Flow to display recipe steps and navigation between them.

TODO Application uses Exoplayer to display videos.

TODO Application properly initializes and releases video assets when appropriate.

TODO Application should properly retrieve media assets from the provided network links.

TODO It should properly handle network requests.

TODO Application makes use of Espresso to test aspects of the UI.

TODO Application sensibly utilizes a third-party library to enhance the app's features.
That could be helper library to interface with ContentProviders if you choose to store the recipes,
a UI binding library to avoid writing findViewById a bunch of times, or something similar.

TODO Application has a companion homescreen widget.

TODO Widget displays ingredient list for desired recipe.

Example code modified:

*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
