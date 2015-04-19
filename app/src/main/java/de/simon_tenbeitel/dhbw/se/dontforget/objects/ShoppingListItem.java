package de.simon_tenbeitel.dhbw.se.dontforget.objects;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Simon on 19.04.2015.
 */
@ParseClassName("ShoppingListItem")
public class ShoppingListItem extends ParseObject {

    private static final String LIST_ID = "list";
    private static final String TITLE = "title";
    private static final String DONE = "done";

    public String getListId() {
        return getString(LIST_ID);
    }

    public void setListId(String listId) {
        put(LIST_ID, listId);
    }

    public String getTitle() {
        return getString(TITLE);
    }

    public void setTitle(String title) {
        put(TITLE, title);
    }

    public boolean isDone() {
        return getBoolean(DONE);
    }

    public void setDone(boolean done) {
        put(DONE, done);
    }

}