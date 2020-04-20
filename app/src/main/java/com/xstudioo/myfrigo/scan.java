package com.xstudioo.myfrigo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class scan extends AppCompatActivity {

/*    //QR Scanner
    public static TextView resultTextView;
    Button btn_scan;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);


     /*   //QR Code Temporary Button + Code Item
        resultTextView = (TextView) findViewById(R.id.result_txt);
        btn_scan = (Button) findViewById(R.id.btn_scan);

        btn_scan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), ScanCodeActivity.class));
            }
        });*/
    }
}
