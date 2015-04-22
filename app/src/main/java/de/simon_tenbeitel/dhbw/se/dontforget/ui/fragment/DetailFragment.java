package de.simon_tenbeitel.dhbw.se.dontforget.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseQuery;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.simon_tenbeitel.dhbw.se.dontforget.R;
import de.simon_tenbeitel.dhbw.se.dontforget.objects.ShoppingListItem;
import de.simon_tenbeitel.dhbw.se.dontforget.ui.adapter.DetailAdapter;
import de.simon_tenbeitel.dhbw.se.dontforget.ui.adapter.ParseQueryRecyclerViewAdapter;

/**
 * Created by Simon on 20.04.2015.
 */
public class DetailFragment extends Fragment {

    @InjectView(R.id.my_recycler_view) RecyclerView mRecyclerView;

    private ParseQueryRecyclerViewAdapter mAdapter;
    private ParseQuery<ShoppingListItem> mQuery;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuery = ShoppingListItem.getQuery();
        mQuery.whereEqualTo(ShoppingListItem.KEY_LIST, getArguments().getString(ShoppingListItem.KEY_LIST));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.inject(this, view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new DetailAdapter(getActivity(), mQuery);
        mRecyclerView.setAdapter(mAdapter);
    }
    
}