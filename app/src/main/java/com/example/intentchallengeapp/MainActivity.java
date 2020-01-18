package com.example.intentchallengeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String MSG1="Cantacts App";
    static final int DEFAULTREQUESTCODE=0;
    static final int HAPPY=1;
    static final int MEDIUM=0;
    static final int SAD=-1;

    Button button;
    TextView textView_main;
    ImageView face,call,web,pin;
    LinearLayout hidable;

    String phoneNumber;
    String website;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.btn_main);
        textView_main=findViewById(R.id.textView_main);
        hidable=findViewById(R.id.hidable);
        face=findViewById(R.id.faceImage);
        call=findViewById(R.id.phoneImage);
        web=findViewById(R.id.websiteImage);
        pin=findViewById(R.id.locationImage);

        textView_main.setText(MSG1);
        hidable.setVisibility(View.GONE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivityForResult(intent,DEFAULTREQUESTCODE);
            }
         });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+phoneNumber));
                startActivity(intent);
            }
        });

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(website));
                startActivity(intent);
            }
        });

        pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("geo:"+location));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == DEFAULTREQUESTCODE) {
            if (resultCode == RESULT_OK) {
                phoneNumber=data.getStringExtra("phone");
                website=data.getStringExtra("website");
                location=data.getStringExtra("location");
                hidable.setVisibility(View.VISIBLE);
                switch (data.getIntExtra("feeling",MEDIUM)){
                    case HAPPY: face.setImageResource(R.drawable.happy);break;
                    case MEDIUM:face.setImageResource(R.drawable.medium);break;
                    case SAD: face.setImageResource(R.drawable.sad);break;
                }
                textView_main.setText(data.getStringExtra("name"));

            }else if (resultCode == RESULT_CANCELED){
                    textView_main.setText("error, try again");
            }
        }
    }
}
