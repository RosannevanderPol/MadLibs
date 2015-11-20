package com.example.rosanne.madlibs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class choosestory extends AppCompatActivity
{
    public int checker = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_choosestory);
    }

    // select a story
    public void onRadioButtonClicked(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.simple:
                if (checked)
                    checker = 1;
                break;
            case R.id.tarzan:
                if (checked)
                    checker = 2;
                break;
            case R.id.university:
                if(checked)
                    checker = 3;
                break;
            case R.id.clothes:
                if(checked)
                    checker = 4;
                break;
            case R.id.dancer:
                if(checked)
                    checker = 5;
                break;
        }
    }

    // Send selected story to next screen
    public void NextstepClicked(View g){
        TextView text1 = (TextView) findViewById(R.id.choosestory);
        // if no selection made: errormessage
        if(checker == 0)
        {
            text1.setText(this.getString(R.string.errorstory));
        }
        // else send story
        else
        {
            Intent passinfo = new Intent(this, choosewords.class);
            passinfo.putExtra("checker", checker);
            startActivity(passinfo);
        }
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
