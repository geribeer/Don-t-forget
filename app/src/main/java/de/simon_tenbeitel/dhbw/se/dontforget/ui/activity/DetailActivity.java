package de.simon_tenbeitel.dhbw.se.dontforget.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;

import de.simon_tenbeitel.dhbw.se.dontforget.R;

/**
 * Created by Simon on 20.04.2015.
 */
public class DetailActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_detail);
    }

}