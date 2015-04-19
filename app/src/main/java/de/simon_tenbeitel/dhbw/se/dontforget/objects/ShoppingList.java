package de.simon_tenbeitel.dhbw.se.dontforget.objects;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Simon on 19.04.2015.
 */
@ParseClassName("ShoppingList")
public class ShoppingList extends ParseObject {

    private static final String TITLE = "title";
    private static final String COUNT = "count";

    public String getTitle() {
        return getString(TITLE);
    }

    public void setTitle(String title) {
        put(TITLE, title);
    }

    public int getItemCount() {
        return getInt(COUNT);
    }

    public void setItemCount(int itemCount) {
        put(COUNT, itemCount);
    }

}