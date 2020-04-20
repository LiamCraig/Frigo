package com.xstudioo.myfrigo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView ideaCard, addCard, scanCard, infoCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //defining cards
        ideaCard = (CardView)findViewById(R.id.idea);
        addCard = (CardView)findViewById(R.id.addItem);
        scanCard = (CardView)findViewById(R.id.qrScan);
        infoCard = (CardView)findViewById(R.id.idea22);

        //add click listener to cards
        ideaCard.setOnClickListener(this);
        addCard.setOnClickListener(this);
        scanCard.setOnClickListener(this);
        infoCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch(v.getId()) {
            case R.id.idea: i = new Intent(this, RecipeActivity.class);startActivity(i); break;
            case R.id.addItem: i = new Intent(this,SettingsActivity.class);startActivity(i);break;
            case R.id.qrScan: i = new Intent(this,scan.class);startActivity(i); break;
            case R.id.idea22: i = new Intent(this,add.class);startActivity(i); break;
            default:break;
        }
    }
}