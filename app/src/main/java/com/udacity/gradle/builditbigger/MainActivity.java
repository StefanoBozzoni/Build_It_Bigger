package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.jokedisplayer.JokeDisplayerActivity;
import com.udacity.gradle.builditbigger.IdlingResource.EspressoIdlingResource;
import com.udacity.gradle.builditbigger.IdlingResource.SimpleCountingIdlingResource;

public class MainActivity extends AppCompatActivity implements
        EndpointsAsyncTask.EndpointCallBack {
    // The Idling Resource which will be null in production.
    private final static String JOKE_EXTRA="JOKE_EXTRA";


    @Override
    public void onCallBack(String strToDisplay) {
        ProgressBar pb = findViewById(R.id.progressBar);
        pb.setVisibility(View.GONE);
        Intent i= new Intent(this, JokeDisplayerActivity.class);

        i.putExtra(JOKE_EXTRA,strToDisplay);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(android.view.View view) {
        ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.VISIBLE);
        SimpleCountingIdlingResource mIdlingResource = (SimpleCountingIdlingResource) EspressoIdlingResource.getIdlingResource();
        new EndpointsAsyncTask(this,mIdlingResource).execute();

    }

}
