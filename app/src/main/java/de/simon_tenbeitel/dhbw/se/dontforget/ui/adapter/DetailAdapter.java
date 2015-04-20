package de.simon_tenbeitel.dhbw.se.dontforget.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import de.simon_tenbeitel.dhbw.se.dontforget.objects.ShoppingListItem;

/**
 * Created by Simon on 20.04.2015.
 */
public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ParseQuery<ShoppingListItem> mQuery;
    private List<ShoppingListItem> mShoppingListItems = null;

    private interface ViewTypes {
        static final int ITEM = 1;
        static final int ADD_ITEM = 2;
    }

    public DetailAdapter(Context context, ParseQuery<ShoppingListItem> query) {
        this.mContext = context;
        this.mQuery = query;
        loadObjectsInBackground();
    }

    public void loadObjects() {
        try {
            mShoppingListItems = mQuery.find();
            notifyDataSetChanged();
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("DetailAdapter", "Error while querying in background: " + e.getMessage());
        }
    }

    public void loadObjectsInBackground() {
        mQuery.findInBackground(new FindCallback<ShoppingListItem>() {
            @Override
            public void done(List<ShoppingListItem> shoppingListItems, ParseException e) {
                if (e == null) {
                    DetailAdapter.this.mShoppingListItems = shoppingListItems;
                    DetailAdapter.this.notifyDataSetChanged();
                } else {
                    Log.e("DetailAdapter", "Error while querying in background: " + e.getMessage());
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return mShoppingListItems != null && position < mShoppingListItems.size() ? ViewTypes.ITEM : ViewTypes.ADD_ITEM;
    }

    @Override
    public int getItemCount() {
        return mShoppingListItems == null ? 1 : mShoppingListItems.size() + 1;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class AddItemViewHolder extends RecyclerView.ViewHolder {
        public AddItemViewHolder(View itemView) {
            super(itemView);
        }
    }

}