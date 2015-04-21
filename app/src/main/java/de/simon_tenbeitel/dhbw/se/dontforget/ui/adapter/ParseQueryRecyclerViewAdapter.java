package de.simon_tenbeitel.dhbw.se.dontforget.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import de.simon_tenbeitel.dhbw.se.dontforget.objects.ShoppingListItem;

/**
 * Created by Simon on 21.04.2015.
 */
public abstract class ParseQueryRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    protected Context mContext;
    protected ParseQuery<ShoppingListItem> mQuery;
    protected List<ShoppingListItem> mItems = null;

    public ParseQueryRecyclerViewAdapter(Context context, ParseQuery<ShoppingListItem> query) {
        this(context, query, true);
    }

    public ParseQueryRecyclerViewAdapter(Context context, ParseQuery<ShoppingListItem> query, boolean instantQuery) {
        this.mContext = context;
        this.mQuery = query;
        if (instantQuery)
            loadObjectsInBackground();
    }

    public void loadObjects() {
        try {
            mItems = mQuery.find();
            notifyDataSetChanged();
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("ParseQueryRecycAdapter", "Error while querying in background: " + e.getMessage());
        }
    }

    public void loadObjectsInBackground() {
        mQuery.findInBackground(new FindCallback<ShoppingListItem>() {
            @Override
            public void done(List<ShoppingListItem> shoppingListItems, ParseException e) {
                if (e == null) {
                    ParseQueryRecyclerViewAdapter.this.mItems = shoppingListItems;
                    ParseQueryRecyclerViewAdapter.this.notifyDataSetChanged();
                } else {
                    Log.e("ParseQueryRecycAdapter", "Error while querying in background: " + e.getMessage());
                }
            }
        });
    }

}