package de.simon_tenbeitel.dhbw.se.dontforget.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseQueryAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.simon_tenbeitel.dhbw.se.dontforget.R;
import de.simon_tenbeitel.dhbw.se.dontforget.objects.ShoppingList;

/**
 * Created by Simon on 19.04.2015.
 */
public class ShoppingListMasterAdapter extends ParseQueryAdapter<ShoppingList> {

    public ShoppingListMasterAdapter(Context context, QueryFactory<ShoppingList> queryFactory) {
        super(context, queryFactory);
    }

    @Override
    public View getItemView(ShoppingList shoppingList, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.title.setText(shoppingList.getTitle());
        holder.itemCount.setText(getContext().getResources().getQuantityString(R.plurals.numberOfShoppingListItems, shoppingList.getItemCount()));

        return view;
    }

    static class ViewHolder {
        @InjectView(android.R.id.text1) TextView title;
        @InjectView(android.R.id.text2) TextView itemCount;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}