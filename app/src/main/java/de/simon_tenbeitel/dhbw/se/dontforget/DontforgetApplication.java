package de.simon_tenbeitel.dhbw.se.dontforget;

import android.app.Application;
import android.content.Context;

/**
 * Created by Simon on 19.04.2015.
 */
public class DontforgetApplication extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext(){
        return sContext;
    }

}