package de.simon_tenbeitel.dhbw.se.dontforget.objects;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.UUID;

/**
 * Created by Simon on 19.04.2015.
 */
@ParseClassName("ShoppingList")
public class ShoppingList extends ParseObject {

    public static final String KEY_TITLE = "title";
    public static final String KEY_COUNT = "count";
    public static final String KEY_DRAFT = "draft";
    public static final String KEY_UUID = "uuid";

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

    public boolean isDraft() {
        return getBoolean(KEY_DRAFT);
    }

    public void setDraft(boolean isDraft) {
        put(KEY_DRAFT, isDraft);
    }

    public void setUuidString() {
        UUID uuid = UUID.randomUUID();
        put(KEY_UUID, uuid.toString());
    }

    public String getUuidString() {
        return getString(KEY_UUID);
    }

    public static ParseQuery<ShoppingList> getQuery() {
        return ParseQuery.getQuery(ShoppingList.class);
    }

}