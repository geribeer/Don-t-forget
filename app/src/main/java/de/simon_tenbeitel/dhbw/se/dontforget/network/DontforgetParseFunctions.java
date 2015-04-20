package de.simon_tenbeitel.dhbw.se.dontforget.network;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import de.simon_tenbeitel.dhbw.se.dontforget.objects.MyParseObject;
import de.simon_tenbeitel.dhbw.se.dontforget.objects.ShoppingList;

/**
 * Created by Simon on 20.04.2015.
 */
public class DontforgetParseFunctions {


    public static void syncToParse() {
        syncShoppingLists();
    }
    
    private static void syncShoppingLists() {
        ParseQuery<ShoppingList> query = ShoppingList.getQuery();
        query.fromPin();
        query.whereEqualTo(MyParseObject.KEY_DRAFT, true);
        query.findInBackground(new FindCallback<ShoppingList>() {
            public void done(List<ShoppingList> shoppingLists, ParseException e) {
                if (e == null) {
                    Log.d("SyncShoppingLists", shoppingLists.size() + " elements to sync");
                    for (final ShoppingList shoppingList : shoppingLists) {
                        // Set is draft flag to false before
                        // syncing to Parse
                        shoppingList.setDraft(false);
                        shoppingList.saveEventually();

                    }
                } else {
                    Log.i("DontforgetParse",
                            "syncShoppingListsToParse: Error finding pinned ShoppingLists: " + e.getMessage());
                }
            }
        });
    }

}