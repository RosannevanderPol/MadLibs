package com.example.rosanne.madlibs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class storyscreen extends AppCompatActivity
{
    public  TextView storytell;
    public  Story parsing;
    public String curstory;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        // put story on the screen
        storytell = (TextView) findViewById(R.id.storyviewer);
        Intent calledActivity = getIntent();
        parsing = (Story) calledActivity.getSerializableExtra("parsing");
        curstory = parsing.toString();
        storytell.setText(curstory);
    }

    // button to start again
    public void anotherstoryClick(View view)
    {
        Intent again =new Intent(this, Start.class);
        startActivity(again);
    }
}