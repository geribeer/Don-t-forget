package de.simon_tenbeitel.dhbw.se.dontforget.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import de.simon_tenbeitel.dhbw.se.dontforget.R;
import de.simon_tenbeitel.dhbw.se.dontforget.objects.ShoppingList;
import de.simon_tenbeitel.dhbw.se.dontforget.ui.activity.MainActivity;
import de.simon_tenbeitel.dhbw.se.dontforget.ui.adapter.ShoppingListMasterAdapter;

/**
 * Created by Simon on 19.04.2015.
 */
public class ShoppingListMasterFragment extends ListFragment {

    private ShoppingListMasterAdapter mAdaper;
    private ParseQueryAdapter.QueryFactory<ShoppingList> mQueryFactory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up the Parse query to use in the adapter
        mQueryFactory = new ParseQueryAdapter.QueryFactory<ShoppingList>() {
            public ParseQuery<ShoppingList> create() {
                ParseQuery<ShoppingList> query = ShoppingList.getQuery();
                query.orderByDescending("updatedAt");
                query.fromLocalDatastore();
                return query;
            }
        };
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdaper = new ShoppingListMasterAdapter(getActivity(), mQueryFactory);
        setListAdapter(mAdaper);
        setEmptyText(getString(R.string.noShoppingLists));

        // Attach the floating action button to the ListView, so it can react to scrolling events.
        // Becomes visible when an attached target is scrolled up and invisible when scrolled down.
        ((MainActivity) getActivity()).newShoppingListButton.attachToListView(getListView());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}