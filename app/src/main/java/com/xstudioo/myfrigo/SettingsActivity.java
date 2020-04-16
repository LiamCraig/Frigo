package com.xstudioo.myfrigo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class SettingsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    TextView fullName,email,phone;//verifyTxtNotice;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    Button verifyBtn, verifiedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navi_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        fullName = findViewById(R.id.pFullName);
        email = findViewById(R.id.pEmail);
        phone = findViewById(R.id.pPhone);

        verifyBtn = findViewById(R.id.verifyBtn);
        verifiedBtn = findViewById(R.id.verifiedBtn);
      //  verifyTxtNotice = findViewById(R.id.verifyAccountTxt);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();     //initialize data

        userID = fAuth.getCurrentUser().getUid();    //retrieve unique user ID
        final FirebaseUser user = fAuth.getCurrentUser();

        if (!user.isEmailVerified()) {
            verifiedBtn.setVisibility(View.INVISIBLE);
            //verifyTxtNotice.setVisibility(View.VISIBLE);
            verifyBtn.setVisibility(View.VISIBLE);
            verifyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(v.getContext(), "Verification email has been sent", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        if(user.isEmailVerified()){
           // verifyTxtNotice.setVisibility(View.INVISIBLE);
            verifyBtn.setVisibility(View.INVISIBLE);
            verifiedBtn.setVisibility(View.VISIBLE);

        }



        //retrieves data from database
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
           phone.setText(documentSnapshot.getString("phone"));
           fullName.setText(documentSnapshot.getString("fName"));     //references tables made in database which were created in RegisterActivity
           email.setText(documentSnapshot.getString("email"));
            }
        });

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();    //logs user out
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));    //sends user to login activity
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.home) {
            startActivity(new Intent(SettingsActivity.this, MainActivity.class));
        } else if (id == R.id.recipes) {
            startActivity(new Intent(SettingsActivity.this, RecipeActivity.class));
        } else if (id == R.id.settings) {
            startActivity(new Intent(SettingsActivity.this, SettingsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


    }


