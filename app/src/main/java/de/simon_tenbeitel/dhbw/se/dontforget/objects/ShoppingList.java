package de.simon_tenbeitel.dhbw.se.dontforget.objects;

import com.parse.ParseClassName;
import com.parse.ParseQuery;

/**
 * Created by Simon on 19.04.2015.
 */
@ParseClassName("ShoppingList")
public class ShoppingList extends MyParseObject {

    public static final String KEY_TITLE = "title";
    public static final String KEY_COUNT = "count";

    public String getTitle() {
        return getString(KEY_TITLE);
    }

    public void setTitle(String title) {
        put(KEY_TITLE, title);
    }

    public int getItemCount() {
        return getInt(KEY_COUNT);
    }

    public void setItemCount(int itemCount) {
        put(KEY_COUNT, itemCount);
    }

    public static ParseQuery<ShoppingList> getQuery() {
        return ParseQuery.getQuery(ShoppingList.class);
    }

}