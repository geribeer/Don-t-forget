package de.simon_tenbeitel.dhbw.se.dontforget.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseQuery;

import de.simon_tenbeitel.dhbw.se.dontforget.objects.ShoppingListItem;

/**
 * Created by Simon on 20.04.2015.
 */
public class DetailAdapter extends ParseQueryRecyclerViewAdapter {

    private interface ViewTypes {
        static final int ITEM = 1;
        static final int ADD_ITEM = 2;
    }

    public DetailAdapter(Context context, ParseQuery<ShoppingListItem> query) {
        super(context, query);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ViewTypes.ITEM:

                return null;
            case ViewTypes.ADD_ITEM:

                return null;
            default:
                throw new RuntimeException("Error while creating RecyclerView.ViewHolder for DetailAdapter: viewType " + viewType + " not found");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return mItems != null && position < mItems.size() ? ViewTypes.ITEM : ViewTypes.ADD_ITEM;
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 1 : mItems.size() + 1;
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