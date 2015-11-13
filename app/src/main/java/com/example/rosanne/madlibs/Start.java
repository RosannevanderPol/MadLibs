package com.example.rosanne.madlibs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void GetstartedClick(View view) {
        Intent Getstarted = new Intent(this, SecondScreen.class);
        Getstarted.putExtra("callingActivity", "MainActivity");
        startActivity(Getstarted);
    }
}


