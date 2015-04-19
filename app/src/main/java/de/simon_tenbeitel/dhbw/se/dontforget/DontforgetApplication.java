package de.simon_tenbeitel.dhbw.se.dontforget;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;

import de.simon_tenbeitel.dhbw.se.dontforget.objects.ShoppingList;
import de.simon_tenbeitel.dhbw.se.dontforget.objects.ShoppingListItem;

/**
 * Created by Simon on 19.04.2015.
 */
public class DontforgetApplication extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();

        // Configure Parse
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(ShoppingList.class);
        ParseObject.registerSubclass(ShoppingListItem.class);
        Parse.initialize(this, "c4Y0QwdstAcsrw8Ck4W2DUSwR0xh2sF7VXrb22kN", "DgDUeI0UL1gVfSgKGtn7KkBbMdAqE4tqrZaETh0o");
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);
    }

    public static Context getContext(){
        return sContext;
    }

}