package com.example.rosanne.madlibs;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class storyscreen extends AppCompatActivity
{
    public  TextView storytell;
    public  Story parsing;
    public String curstory;
    public boolean  textspeaker = false;
    public TextToSpeech translator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        translator = new TextToSpeech(this, new TextToSpeech.OnInitListener()
        {
            @Override
            public void onInit(int status)
            {
                translator.speak("" +curstory, TextToSpeech.QUEUE_FLUSH, null);
                textspeaker = true;
            }
        });


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

    // Method to save the current state
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        // Pass story
        outState.putString("story", curstory);
    }

    // Method for restoring data also enables turning of screen
    @Override
    public void onRestoreInstanceState(Bundle inState)
    {
        super.onRestoreInstanceState(inState);
        // Retrieve story
        curstory = inState.getString("story");
        storytell.setText(curstory);
    }
}