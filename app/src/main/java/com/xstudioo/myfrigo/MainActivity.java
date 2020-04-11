package com.xstudioo.myfrigo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // get the list of stories titles and contents in string array

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navi_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();


        String[] titles = getResources().getStringArray(R.array.recipes_title);
        String[] contents = getResources().getStringArray(R.array.recipes_content);
        int[] images = {R.drawable.chicken,R.drawable.chinese,R.drawable.fish,R.drawable.pasta,R.drawable.pizza,R.drawable.sandwiches};

        recyclerView = findViewById(R.id.recipeListsView);

        Adapter adapter = new Adapter(this, titles, contents, images);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //adapter = new Adapter(this,titles,contents, images); // our adapter takes two string array
        //recyclerView.setAdapter(adapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawer_menu,menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //Handles navigation view
        int id = menuItem.getItemId();

        if(id == R.id.home) {
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        }else if (id == R.id.recipes) {
            startActivity(new Intent(MainActivity.this, RecipeActivity.class));
        }else if (id == R.id.settings){
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;

       // if(menuItem.getItemId() == R.id.home){
         //   Toast.makeText(this, "Home btn Clicked.", Toast.LENGTH_SHORT).show();
        //}
        //return true;
    }
}