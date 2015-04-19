package de.simon_tenbeitel.dhbw.se.dontforget.objects;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.UUID;

/**
 * Created by Simon on 19.04.2015.
 */
@ParseClassName("ShoppingListItem")
public class ShoppingListItem extends ParseObject {

    private static final String KEY_LIST_ID = "list";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DONE = "done";
    private static final String KEY_UUID = "uuid";

    public String getListId() {
        return getString(KEY_LIST_ID);
    }

    public void setListId(String listId) {
        put(KEY_LIST_ID, listId);
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

    public void setUuidString() {
        UUID uuid = UUID.randomUUID();
        put(KEY_UUID, uuid.toString());
    }

    public String getUuidString() {
        return getString(KEY_UUID);
    }

    public static ParseQuery<ShoppingListItem> getQuery() {
        return ParseQuery.getQuery(ShoppingListItem.class);
    }

}