package com.xstudioo.myfrigo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mFullName, mEmail, mPassword, mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;         //used for registering user
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName = findViewById(R.id.pFullName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);              //connects resources on xml file
        mPhone = findViewById(R.id.pPhone);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.createText);

        fAuth = FirebaseAuth.getInstance();        //getting current instance of database
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);



        if (fAuth.getCurrentUser() != null) {   //if current user is present the current user is logged in so nothing will be displayed, they'll be sent to mainActivity
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        //code that register user into database
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String fullName = mFullName.getText().toString();                  //retrieving email;password;fullname;phone
                final String phone = mPhone.getText().toString();

                //validate data entered into email and password
                if (TextUtils.isEmpty(email)) {     //textUtils.isEmpty checks if text field is empty
                    mEmail.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is required");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password must contain 6 or more characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //register user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            //sends verification link
                            FirebaseUser fuser = fAuth.getCurrentUser();     //retrieves current user as object
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(RegisterActivity.this, "Verification email has been sent", Toast.LENGTH_SHORT).show();
                                }
                            });

                            Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid(); //methods that retrieve the userID of the user that is currently logged in
                            DocumentReference documentReference = fStore.collection("users").document(userID);   //reference document on firebase database
                            Map<String,Object> user = new HashMap<>();    //stores the data          String = key    Object = data
                            user.put("fName",fullName);   //inserts data for full name
                            user.put("email",email); //inserts data for email
                            user.put("phone",phone); //inserts data for ph number
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {    //inserts into cloud database
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: Profile created for "+ userID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(RegisterActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    }
}