package de.simon_tenbeitel.dhbw.se.dontforget.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.simon_tenbeitel.dhbw.se.dontforget.R;
import de.simon_tenbeitel.dhbw.se.dontforget.network.DontforgetParseFunctions;
import de.simon_tenbeitel.dhbw.se.dontforget.objects.MyParseObject;
import de.simon_tenbeitel.dhbw.se.dontforget.objects.ShoppingList;
import de.simon_tenbeitel.dhbw.se.dontforget.ui.fragment.ShoppingListMasterFragment;

/**
 * Created by Simon on 19.04.2015.
 */
public class MainActivity extends ActionBarActivity {

    private static final String TAG_SHOPPINGLIST_MASTER_FRAGMENT = "shoppinglist_master";

    @InjectView(R.id.new_shoppingList) public FloatingActionButton newShoppingListButton;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadShoppingListsFromParse();
        DontforgetParseFunctions.syncToParse();
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

    public void refreshList() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ListFragment shoppingListFragment = (ListFragment) fragmentManager.findFragmentByTag(TAG_SHOPPINGLIST_MASTER_FRAGMENT);
        ParseQueryAdapter<ShoppingList> adapter = (ParseQueryAdapter<ShoppingList>) shoppingListFragment.getListAdapter();
        adapter.loadObjects();
    }

    @OnClick(R.id.new_shoppingList)
    public void newShoppingList() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.createNewShoppingList));

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton(getString(R.string.save), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //todo create new shopping list
                
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void loadShoppingListsFromParse() {
        ParseQuery<ShoppingList> query = ShoppingList.getQuery();
        query.whereEqualTo(MyParseObject.KEY_AUTHOR, ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ShoppingList>() {
            public void done(List<ShoppingList> shoppingLists, ParseException e) {
                if (e == null) {
                    Log.d("Load shopping lists", shoppingLists.size() + " elements fetched");
                    ParseObject.pinAllInBackground((List<ShoppingList>) shoppingLists,
                            new SaveCallback() {
                                public void done(ParseException e) {
                                    if (e == null) {
                                        if (!isFinishing()) {
                                            MainActivity.this.refreshList();
                                        }
                                    } else {
                                        Log.i("DontforgetParse",
                                                "Error pinning shopping lists: " + e.getMessage());
                                    }
                                }
                            });
                } else {
                    Log.i("MainActivity",
                            "loadFromParse: Error finding pinned shopping lists: "
                                    + e.getMessage());
                }
            }
        });
    }

}