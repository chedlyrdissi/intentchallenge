package com.example.intentchallengeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    EditText name,number,website,location;
    ImageView happy,medium,sad;
    Listener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

            name    =findViewById(R.id.name);
            number  =findViewById(R.id.number);
            website =findViewById(R.id.website);
            location=findViewById(R.id.location);
            happy   =findViewById(R.id.happy);
            medium  =findViewById(R.id.medium);
            sad     =findViewById(R.id.sad);

            listener=new Listener();
            happy.setOnClickListener(listener);
            medium.setOnClickListener(listener);
            sad.setOnClickListener(listener);


    }

    private class Listener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if (check()){
                switch (v.getId()){
                    case R.id.happy:  returnToPrevious(MainActivity.HAPPY);break;
                    case R.id.medium: returnToPrevious(MainActivity.MEDIUM);break;
                    case R.id.sad:    returnToPrevious(MainActivity.SAD);break;
                }
            }
        }

        private void returnToPrevious(int result){

            Intent returnIntent = new Intent();
            returnIntent.putExtra("feeling",result);
            returnIntent.putExtra("name",name.getText().toString());
            returnIntent.putExtra("website",website.getText().toString());
            returnIntent.putExtra("location",location.getText().toString());
            returnIntent.putExtra("phone",number.getText().toString());
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }

        private boolean check(){
            boolean result=true;
            if(name.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"please input a name",Toast.LENGTH_SHORT).show();
                result=false;
            }
            if(number.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"please input a phone number",Toast.LENGTH_SHORT).show();
                result=false;
            }

            if(website.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"please input a website",Toast.LENGTH_SHORT).show();
                result=false;
            }

            if(location.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"please input a location",Toast.LENGTH_SHORT).show();
                result=false;
            }

            return result;
        }
    }
}
