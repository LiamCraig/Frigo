package com.xstudioo.myfrigo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class AddRecordActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mAgeEditText;
    private EditText mOccupationEditText;
    private EditText mImageEditText;
    private Button mAddBtn;

    public static EditText resultTextView;
    Button btn_scan;


    private PersonDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        //QR Scanner
        resultTextView = (EditText) findViewById(R.id.barcode_id);
        btn_scan = (Button) findViewById(R.id.scan_item_btn);

        btn_scan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), ScanCodeActivity.class));
            }
        });

        //init
        mNameEditText = (EditText)findViewById(R.id.userName);
        mAgeEditText = (EditText)findViewById(R.id.userAge);
        mOccupationEditText = (EditText)findViewById(R.id.userOccupation);
        mImageEditText = (EditText)findViewById(R.id.userProfileImageLink);
        mAddBtn = (Button)findViewById(R.id.addNewUserButton);

        //listen to add button click
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the save person method
                savePerson();
            }
        });

    }

    private void savePerson(){
        String name = mNameEditText.getText().toString().trim();
        String age = mAgeEditText.getText().toString().trim();
        String occupation = mOccupationEditText.getText().toString().trim();
        String image = mImageEditText.getText().toString().trim();
        String barcode = resultTextView.getText().toString().trim();

        dbHelper = new PersonDBHelper(this);


        //create new person
        Person person = new Person(name, age, occupation, image, barcode);
        dbHelper.saveNewPerson(person);

        //finally redirect back home
        // NOTE you can implement an sqlite callback then redirect on success delete
        goBackHome();

    }

    private void goBackHome(){
        startActivity(new Intent(AddRecordActivity.this, add.class));
    }
}