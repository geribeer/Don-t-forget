package de.simon_tenbeitel.dhbw.se.dontforget.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.parse.ParseQuery;

import de.simon_tenbeitel.dhbw.se.dontforget.objects.ShoppingListItem;
import de.simon_tenbeitel.dhbw.se.dontforget.ui.adapter.DetailAdapter;
import de.simon_tenbeitel.dhbw.se.dontforget.ui.adapter.ParseQueryRecyclerViewAdapter;

/**
 * Created by Simon on 20.04.2015.
 */
public class DetailFragment extends Fragment {

    private ParseQueryRecyclerViewAdapter mAdapter;
    private ParseQuery<ShoppingListItem> mQuery;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuery = ShoppingListItem.getQuery();
        mQuery.whereEqualTo(ShoppingListItem.KEY_LIST, getArguments().getString(ShoppingListItem.KEY_LIST));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new DetailAdapter(getActivity(), mQuery);
    }
}