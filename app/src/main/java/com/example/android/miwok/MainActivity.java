package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        //create an object from the NumbersClickListeners
        //NumbersClickListener clickListener = new NumbersClickListener();

        //find the view for each category
        final TextView numbersTextView = findViewById(R.id.numbers);
        final TextView familyTextView  = findViewById(R.id.family);
        final TextView colorsTextView  = findViewById(R.id.colors);
        final TextView phrasesTextView = findViewById(R.id.phrases);

        //set a click listener to that view
        //numbersTextView.setOnClickListener(new NumbersClickListener());//inline object creation
        //Make it all inline
        numbersTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "Go to Numers", Toast.LENGTH_LONG).show();
                //Create a new activity to open the {@link NumbersActivity}
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(numbersIntent);
            }
        });

        //Set on click listener for family text view
        familyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new activity to open the {@link FamilyMembersActivity}
                Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(familyIntent);
            }
        });

        colorsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new activity to open the {@link ColorsActivity}
                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(colorsIntent);
            }
        });

        phrasesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new activity to open the {@link PhrasesActivity}
                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(phrasesIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(">>>>>>>>>>MainActivity", "onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.v(">>>>>>>>>>MainActivity", "onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.v(">>>>>>>>>MainActivity", "onPause");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(">>>>>>>>>MainActivity", "onDestroy");
    }
}
