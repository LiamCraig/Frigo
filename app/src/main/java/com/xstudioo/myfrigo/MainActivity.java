package com.xstudioo.myfrigo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Nav
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    //List
    ArrayList<String> list = new ArrayList<>();
    ListView list_view;
    ArrayAdapter arrayAdapter;

    //QR Scanner
    public static TextView resultTextView;
    Button btn_scan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //QR Code Temporary Button + Code Item
        resultTextView = (TextView)findViewById(R.id.result_txt);
        btn_scan = (Button)findViewById(R.id.btn_scan);

        btn_scan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),ScanCodeActivity.class));

            }
        });

        //List
        list_view = findViewById(R.id.list_view);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        arrayAdapter.add("Eggs");
        arrayAdapter.add("Milk");
        arrayAdapter.add("Bread");
        arrayAdapter.add("Cheese");
        arrayAdapter.add("Flour");
        arrayAdapter.add("Butter");

        list_view.setAdapter(arrayAdapter);


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



        //liams added code(Implements List)
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            //UPDATE ITEM
                            case R.id.item_update:

                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_dialog, null, false);
                                builder.setTitle("Update");
                                final EditText editText = v.findViewById(R.id.etItem);
                                editText.setText(list.get(position));

                                //SETS CUSTOM VIEW FROM DIALOG
                                builder.setView(v);

                                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (!editText.getText().toString().isEmpty()) {
                                            list.set(position, editText.getText().toString().trim());
                                            arrayAdapter.notifyDataSetChanged();
                                            Toast.makeText(MainActivity.this, "Item Updated!", Toast.LENGTH_SHORT).show();

                                        } else {
                                            editText.setError("add item here !");
                                        }
                                    }
                                });

                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                                builder.show();

                                break;

                            //DELETE ITEM
                            case R.id.item_del:


                                Toast.makeText(MainActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                                list.remove(position);
                                arrayAdapter.notifyDataSetChanged();

                                break;

                        }

                        return true;
                    }
                });

                popupMenu.show();

            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            //FUNCTION TO ADD ITEMS
            case R.id.add_item:

                _addItem();
                break;

        }

        return true;
    }

    //METHOD FOR ADDING ITEMS
    private void _addItem() {


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add New Item");

        View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_dialog, null, false);

        builder.setView(v);

        final EditText etItemBarcode = v.findViewById(R.id.etItemBarcode);
        final EditText etItem = v.findViewById(R.id.etItem);
        final EditText etItemPurchase = v.findViewById(R.id.etItemPurchase);
        final EditText etItemExpiry = v.findViewById(R.id.etItemExpiry);


        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {



            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (!etItemBarcode.getText().toString().isEmpty()) {
                    list.add(etItemBarcode.getText().toString().trim());
                    arrayAdapter.notifyDataSetChanged();
                }
                if (!etItem.getText().toString().isEmpty()) {
                    list.add(etItem.getText().toString().trim());
                    arrayAdapter.notifyDataSetChanged();
                }
                if (!etItemPurchase.getText().toString().isEmpty()) {
                    list.add(etItemPurchase.getText().toString().trim());
                    arrayAdapter.notifyDataSetChanged();
                }
                if (!etItemExpiry.getText().toString().isEmpty()) {
                    list.add(etItemExpiry.getText().toString().trim());
                    arrayAdapter.notifyDataSetChanged();
                }
                else {
                    etItem.setError("add item here !");
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
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
