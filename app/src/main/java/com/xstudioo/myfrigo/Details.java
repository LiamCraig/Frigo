package com.xstudioo.myfrigo;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class Details extends AppCompatActivity {
    TextView recipeContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        recipeContent = findViewById(R.id.contentOfRecipe);
        Intent i = getIntent();
        String title = i.getStringExtra("titleOfStory");
        String content = i.getStringExtra("contentOfRecipe");

        // set the appbar title as Story title
        getSupportActionBar().setTitle(title);

        // set content of the story to textview
        recipeContent.setText(content);
        recipeContent.setMovementMethod(new ScrollingMovementMethod());

        // enable back button to main activity or recyclerview
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }
}
