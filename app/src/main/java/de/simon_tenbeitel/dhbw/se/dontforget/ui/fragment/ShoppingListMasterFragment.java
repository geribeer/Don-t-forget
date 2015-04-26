package de.simon_tenbeitel.dhbw.se.dontforget.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import de.simon_tenbeitel.dhbw.se.dontforget.R;
import de.simon_tenbeitel.dhbw.se.dontforget.objects.ShoppingList;
import de.simon_tenbeitel.dhbw.se.dontforget.objects.ShoppingListItem;
import de.simon_tenbeitel.dhbw.se.dontforget.ui.activity.DetailActivity;
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

        //todo Set up the Parse query to use in the adapter
        
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
        ShoppingList shoppingList = mAdaper.getItem(position);
        String objectID = shoppingList.getObjectId();
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(ShoppingList.KEY_TITLE, shoppingList.getTitle());
        intent.putExtra(ShoppingListItem.KEY_LIST, objectID);
        startActivity(intent);
    }

}