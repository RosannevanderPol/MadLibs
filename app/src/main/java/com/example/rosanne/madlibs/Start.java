package com.example.rosanne.madlibs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Start extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_start);
    }

    public void GetstartedClick(View view)
    {
        Intent Getstarted = new Intent(this, choosestory.class);
        startActivity(Getstarted);
    }

    // Method to enable rotation
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
    }

    // Method to enable rotation
    @Override
    public void onRestoreInstanceState(Bundle inState)
    {
        super.onRestoreInstanceState(inState);
    }
}


