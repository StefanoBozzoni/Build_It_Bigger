package com.example.jokedisplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class JokeDisplayerActivity extends AppCompatActivity {

    private final static String JOKE_EXTRA="JOKE_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_displayer);
        Intent i =getIntent();
        if ((i!=null) && (i.hasExtra(JOKE_EXTRA))) {
            String inputJoke = i.getStringExtra(JOKE_EXTRA);
            TextView tv = findViewById(R.id.tv_joke);
            tv.setText(inputJoke);
        }
    }
}
