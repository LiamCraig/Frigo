package com.example.myfrigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private EditText mTextUsername;
    private EditText mTextPassword;
    private Button mButtonLogin;
    private TextView mInfo;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTextUsername = (EditText) findViewById(R.id.edittext_username);
        mTextPassword = (EditText) findViewById(R.id.edittext_password);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mInfo = (TextView)findViewById(R.id.tvInfo);

        mInfo.setText("No of attempts remaining: 5");

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(mTextUsername.getText().toString(), mTextPassword.getText().toString());
            }
        });
            }
            private void validate(String usermTextUsername, String usermTextPassword){
        if((usermTextUsername.equals("admin")) && (usermTextPassword.equals("123"))){
            Intent intent = new Intent(HomeActivity.this, ContentsActivity.class);
            startActivity(intent);
        }else{
            counter--;

            mInfo.setText("No of attempts remaining: " + String.valueOf(counter));

            if(counter == 0){
                mButtonLogin.setEnabled(false);
            }
        }
            }
}
