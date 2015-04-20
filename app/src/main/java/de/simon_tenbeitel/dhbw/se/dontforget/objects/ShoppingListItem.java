package de.simon_tenbeitel.dhbw.se.dontforget.objects;

import com.parse.ParseClassName;
import com.parse.ParseQuery;

/**
 * Created by Simon on 19.04.2015.
 */
@ParseClassName("ShoppingListItem")
public class ShoppingListItem extends MyParseObject {

    private static final String KEY_LIST = "list";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DONE = "done";

    public ShoppingList getShoppingList() {
        return (ShoppingList) get(KEY_LIST);
    }

    public void setShoppingList(ShoppingList list) {
        put(KEY_LIST, list);
    }

    public String getTitle() {
        return getString(KEY_TITLE);
    }

    public void setTitle(String title) {
        put(KEY_TITLE, title);
    }

    public boolean isDone() {
        return getBoolean(KEY_DONE);
    }

    public void setDone(boolean done) {
        put(KEY_DONE, done);
    }

    public static ParseQuery<ShoppingListItem> getQuery() {
        return ParseQuery.getQuery(ShoppingListItem.class);
    }

}