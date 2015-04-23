package de.simon_tenbeitel.dhbw.se.dontforget.network;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import de.simon_tenbeitel.dhbw.se.dontforget.objects.MyParseObject;
import de.simon_tenbeitel.dhbw.se.dontforget.objects.ShoppingList;
import de.simon_tenbeitel.dhbw.se.dontforget.objects.ShoppingListItem;

/**
 * Created by Simon on 20.04.2015.
 */
public class DontforgetParseFunctions {


    public static void syncToParse() {
        syncShoppingLists();
        syncItems();
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

    private static void syncItems() {
        ParseQuery<ShoppingListItem> query = ShoppingListItem.getQuery();
        query.fromPin();
        query.whereEqualTo(MyParseObject.KEY_DRAFT, true);
        query.findInBackground(new FindCallback<ShoppingListItem>() {
            @Override
            public void done(List<ShoppingListItem> shoppingListItems, ParseException e) {
                if (e == null) {
                    Log.d("SyncItems", shoppingListItems.size() + " elements to sync");
                    for (final ShoppingListItem item : shoppingListItems) {
                        item.setDraft(false);
                        item.saveEventually();
                    }
                } else {
                    Log.i("DontforgetParse",
                            "syncShoppingListsItemsToParse: Error finding pinned ShoppingListItems: " + e.getMessage());
                }
            }
        });
    }

}