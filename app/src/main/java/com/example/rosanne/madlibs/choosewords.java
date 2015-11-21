package com.example.rosanne.madlibs;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

public class choosewords extends AppCompatActivity
{
    public static final int RESULT_SPEECH = 1;

    public InputStream stream;
    public Story parsing;
    public TextView placeholder;
    public TextView placeholdercount;
    public TextView textshow;
    public TextToSpeech translator;
    public EditText inputtext;
    public boolean  textspeaker = false;
    public int checker;
    public boolean errorcheck = false;
    public int counter;
    public ImageButton btnSpeak;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        // define all variables used
        btnSpeak = (ImageButton) findViewById(R.id.imageButton);
        inputtext = (EditText) findViewById(R.id.editText);
        textshow = (TextView) findViewById(R.id.storynum);
        placeholder = (TextView) findViewById(R.id.placeholders);
        placeholdercount = (TextView) findViewById(R.id.wordscount);
        Bundle extras = getIntent().getExtras();
        checker = extras.getInt("checker");
        getStory(checker);

        btnSpeak.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent convertTalk = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                convertTalk.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
                convertTalk.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                convertTalk.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please say a" + placeholder);
                try
                {
                    startActivityForResult(convertTalk, RESULT_SPEECH);
                    inputtext.setText("");
                }
                catch (ActivityNotFoundException a)
                {
                    Toast t = Toast.makeText(getApplicationContext(), "Ooops! Your device does not support Speech to Text", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
    }


    // Receiving and processing spoken data
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent input)
        {
            super.onActivityResult(requestCode, resultCode, input);
            switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != input) {
                    ArrayList<String> list = input.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String spokenText = list.get(0);
                    processInput(spokenText);

                    inputtext.setText(list.get(0));
                }
                break;
            }
            }
        }


    public void inputWord(View z) {
        // Get words from the textfield
        String input = String.valueOf(inputtext.getText());
        inputtext.setText("");

        // if input are not letters: errormessage
        if (!input.matches("[a-zA-Z\\s]+") && errorcheck == false) {
            textshow.setText(getString(R.string.errorinput));
            errorcheck = true;
        }
        // else pass input
        else {
            processInput(input);
        }
    }

         /* Method for handling the spoken/written input of the user. */
        public void processInput(String input) {

        /* Send input to parser. */
        parsing.fillInPlaceholder(input);
        if (errorcheck == true){
            textshow.setText(getString(R.string.inputword));
            errorcheck = false;
        }

        // words left to fill in
        counter = parsing.getPlaceholderRemainingCount();

        // go to the storyscreen if all the words are filled in
        if (counter == 0)
        {
            Intent storyscreen = new Intent(this, storyscreen.class);
            storyscreen.putExtra("parsing", parsing);
            startActivity(storyscreen);
        }



        /* If tts is ready, prompt the required word type through spoken text. */
        else if (textspeaker)
        {
            translator.speak("Please enter a " + parsing.getNextPlaceholder().toLowerCase(), TextToSpeech.QUEUE_FLUSH, null);
        }

        // update words
        resetText();
        placeholder.append(" " + parsing.getNextPlaceholder());
        placeholdercount.append(" " + counter);
    }


    // load the selected story
    public void getStory(int checker) {
        switch (checker) {
            case 1:
                stream = this.getResources().openRawResource(R.raw.madlib0_simple);
                break;
            case 2:
                stream = this.getResources().openRawResource(R.raw.madlib1_tarzan);
                break;
            case 3:
                stream = this.getResources().openRawResource(R.raw.madlib2_university);
                break;
            case 4:
                stream = this.getResources().openRawResource(R.raw.madlib3_clothes);
                break;
            case 5:
                stream = this.getResources().openRawResource(R.raw.madlib4_dance);
        }
        parseStory(stream);
    }

    // parse selected story
    public void parseStory(InputStream stream)
    {
        parsing = new Story(stream);
        placeholder.append(" " + parsing.getNextPlaceholder());
        placeholdercount.append(" " + parsing.getPlaceholderCount());
    }

    // reset the text
    public void resetText()
    {
        placeholder.setText(getString(R.string.placeholders));
        placeholdercount.setText(getString(R.string.placeholderscount));
    }

    // Save the current state
    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        /* send checker id and story element
         */
        outState.putInt("checker", checker);
        outState.putSerializable("parsing", parsing);
    }

    // enables rotation
    @Override
    public void onRestoreInstanceState(Bundle inState)
    {
        super.onRestoreInstanceState(inState);

        // retrieve checker id and get story element back
        checker = inState.getInt("checker");
        parsing = (Story) inState.getSerializable("parsing");

        // reset text
        resetText();
        placeholder.append(" " + parsing.getNextPlaceholder());
        placeholdercount.append(" " + parsing.getPlaceholderRemainingCount());
    }
}


