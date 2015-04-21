package de.simon_tenbeitel.dhbw.se.dontforget.objects;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.UUID;

/**
 * Created by Simon on 20.04.2015.
 */
public class MyParseObject extends ParseObject {

    public static final String KEY_DRAFT = "draft";
    public static final String KEY_AUTHOR = "author";
    public static final String KEY_UUID = "uuid";

    public ParseUser getAuthor() {
        return getParseUser(KEY_AUTHOR);
    }

    public void setAuthor(ParseUser currentUser) {
        put(KEY_AUTHOR, currentUser);
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

}