package com.example.rosanne.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.InputStream;

public class choosewords extends AppCompatActivity
{
    public InputStream stream;
    public Story parsing;
    public TextView placeholder;
    public TextView placeholdercount;
    public TextView textshow;
    public EditText inputtext;
    public int checker;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        // define all variables used
        textshow = (TextView) findViewById(R.id.storynum);
        placeholder = (TextView) findViewById(R.id.placeholders);
        placeholdercount = (TextView) findViewById(R.id.wordscount);
        inputtext = (EditText) findViewById(R.id.editText);
        Bundle extras = getIntent().getExtras();
        checker = extras.getInt("checker");
        getStory(checker);
    }

    public void inputWord(View z)
    {
        boolean errorcheck = false;
        int counter;

        // Get words from the textfield
        String input = String.valueOf(inputtext.getText());
        inputtext.setText("");

        // if input are not letters: errormessage
        if (!input.matches("[a-zA-Z\\s]+") && errorcheck == false)
        {
            textshow.setText(getString(R.string.errorinput));
            errorcheck = true;
        }
        // else pass input
        else
        {
            parsing.fillInPlaceholder(input);
            if (errorcheck == true)
            {
                textshow.setText(getString(R.string.inputword));
                errorcheck = false;
            }
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

    //  parse selected story
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

    //Method to save the current state
    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        /* send checker id and story element
         */
        outState.putInt("checker", checker);
        outState.putSerializable("parsing", parsing);
    }

    /* Method in order to retrieve data after pause
       also enables rotation
     */
    @Override
    public void onRestoreInstanceState(Bundle inState) {

        super.onRestoreInstanceState(inState);

        /* retrieve checker id and get story element back
        */
        checker = inState.getInt("checker");
        parsing = (Story) inState.getSerializable("parsing");

        /* reset text and fetch placeholder
        */
        resetText();
        placeholder.append(" " + parsing.getNextPlaceholder());
        placeholdercount.append(" " + parsing.getPlaceholderRemainingCount());
    }
}


