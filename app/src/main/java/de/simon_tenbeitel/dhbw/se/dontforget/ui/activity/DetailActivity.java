package de.simon_tenbeitel.dhbw.se.dontforget.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

import de.simon_tenbeitel.dhbw.se.dontforget.R;
import de.simon_tenbeitel.dhbw.se.dontforget.objects.MyParseObject;
import de.simon_tenbeitel.dhbw.se.dontforget.objects.ShoppingList;
import de.simon_tenbeitel.dhbw.se.dontforget.objects.ShoppingListItem;
import de.simon_tenbeitel.dhbw.se.dontforget.ui.fragment.DetailFragment;

/**
 * Created by Simon on 20.04.2015.
 */
public class DetailActivity extends ActionBarActivity {

    private static final String TAG_DETAIL_FRAGMENT = "detail_fragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent startingIntent = getIntent();

        String listTitle = startingIntent.getStringExtra(ShoppingList.KEY_TITLE);
        setTitle(listTitle);

        loadItemsFromParse();

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            String objectID = startingIntent.getStringExtra(ShoppingListItem.KEY_LIST);
            Fragment detailFragment = new DetailFragment();
            Bundle args = new Bundle();
            args.putString(ShoppingListItem.KEY_LIST, objectID);
            detailFragment.setArguments(args);

            fragmentManager.beginTransaction()
                    .replace(R.id.container, detailFragment, TAG_DETAIL_FRAGMENT)
                    .commit();
        }
    }

    private void refreshList() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DetailFragment fragment = (DetailFragment) fragmentManager.findFragmentByTag(TAG_DETAIL_FRAGMENT);
        fragment.getAdapter().loadObjectsInBackground();
    }

    private void loadItemsFromParse() {
        ParseQuery<ShoppingListItem> query = ShoppingListItem.getQuery();
        query.whereEqualTo(MyParseObject.KEY_AUTHOR, ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ShoppingListItem>() {
            public void done(List<ShoppingListItem> items, ParseException e) {
                if (e == null) {
                    Log.d("Load list items", items.size() + " elements fetched");
                    ParseObject.pinAllInBackground((List<ShoppingListItem>) items,
                            new SaveCallback() {
                                public void done(ParseException e) {
                                    if (e == null) {
                                        if (!isFinishing()) {
                                            DetailActivity.this.refreshList();
                                        }
                                    } else {
                                        Log.i("DontforgetParse",
                                                "Error pinning shopping list items: " + e.getMessage());
                                    }
                                }
                            });
                } else {
                    Log.i("DetailActivity",
                            "loadFromParse: Error finding pinned items: "
                                    + e.getMessage());
                }
            }
        });
    }

}