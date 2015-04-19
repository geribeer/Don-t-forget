package de.simon_tenbeitel.dhbw.se.dontforget.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;
import com.parse.ParseUser;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.simon_tenbeitel.dhbw.se.dontforget.R;
import de.simon_tenbeitel.dhbw.se.dontforget.ui.fragment.ShoppingListMasterFragment;

/**
 * Created by Simon on 19.04.2015.
 */
public class MainActivity extends ActionBarActivity {

    private static final String TAG_SHOPPINGLIST_MASTER_FRAGMENT = "shoppinglist_master";

    @InjectView(R.id.new_shoppingList) FloatingActionButton newShoppingListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_shoppinglist_master_container, new ShoppingListMasterFragment(), TAG_SHOPPINGLIST_MASTER_FRAGMENT)
                    .commit();
        }

        // Attach the floating action button to the ListView, so it can react to scrolling events.
        // Becomes visible when an attached target is scrolled up and invisible when scrolled down.
        ListFragment shoppingListFragment = (ListFragment) fragmentManager.findFragmentByTag(TAG_SHOPPINGLIST_MASTER_FRAGMENT);
        ListView shoppingListListView = shoppingListFragment.getListView();
        newShoppingListButton.attachToListView(shoppingListListView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                ParseUser.logOutInBackground();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.new_shoppingList)
    public void newShoppingList() {

    }

}