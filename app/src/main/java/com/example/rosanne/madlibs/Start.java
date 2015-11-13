package com.example.rosanne.madlibs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void GetstartedClick(View view) {
        Intent Getstarted = new Intent(getApplicationContext(), SecondScreen.class);
        startActivity(Getstarted);
    }

    public void GomakemystoryClick(View view) {
        Intent Gomakemystory = new Intent(this, ThirdScreen.class);

        final int result = 1;

        Gomakemystory.putExtra("callingActivity", "MainActivity");

        startActivity(Gomakemystory);
    }

}
