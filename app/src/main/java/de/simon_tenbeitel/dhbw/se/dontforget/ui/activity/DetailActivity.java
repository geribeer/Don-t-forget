package de.simon_tenbeitel.dhbw.se.dontforget.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import de.simon_tenbeitel.dhbw.se.dontforget.R;
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

}