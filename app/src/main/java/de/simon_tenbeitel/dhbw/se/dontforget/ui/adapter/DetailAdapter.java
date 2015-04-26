package de.simon_tenbeitel.dhbw.se.dontforget.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.simon_tenbeitel.dhbw.se.dontforget.R;
import de.simon_tenbeitel.dhbw.se.dontforget.network.DontforgetParseFunctions;
import de.simon_tenbeitel.dhbw.se.dontforget.objects.ShoppingList;
import de.simon_tenbeitel.dhbw.se.dontforget.objects.ShoppingListItem;
import de.simon_tenbeitel.dhbw.se.dontforget.ui.activity.DetailActivity;

/**
 * Created by Simon on 20.04.2015.
 */
public class DetailAdapter extends ParseQueryRecyclerViewAdapter {

    private ShoppingList mEmptyShoppingList;

    private interface ViewTypes {
        static final int ITEM = 1;
        static final int ADD_ITEM = 2;
    }

    public DetailAdapter(Context context, ParseQuery<ShoppingListItem> query, String shoppingListId) {
        super(context, query);
        mEmptyShoppingList = ShoppingList.createWithoutData(ShoppingList.class, shoppingListId);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ViewTypes.ITEM:
                View itemView = inflater.inflate(R.layout.row_detail, parent, false);
                return new ItemViewHolder(itemView);
            case ViewTypes.ADD_ITEM:
                View addItemView = inflater.inflate(R.layout.row_detail_add_button, parent, false);
                return new AddItemViewHolder(addItemView);
            default:
                throw new RuntimeException("Error while creating RecyclerView.ViewHolder for DetailAdapter: viewType " + viewType + " not found");
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)) {
            case ViewTypes.ITEM:
                final ShoppingListItem item = mItems.get(position);
                bindItemViewHolder(item, viewHolder);
                break;
            case ViewTypes.ADD_ITEM:
                ((AddItemViewHolder) viewHolder).addItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setTitle(mContext.getString(R.string.newItem));

                        // Set up the input
                        final EditText input = new EditText(mContext);
                        // Specify the type of input expected
                        input.setInputType(InputType.TYPE_CLASS_TEXT);
                        builder.setView(input);

                        builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String title = input.getText().toString();
                                createItem(title);
                            }
                        });

                        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        builder.show();
                    }
                });
                break;
        }
    }

    private void bindItemViewHolder(final ShoppingListItem item, final RecyclerView.ViewHolder viewHolder) {
        final String title = item.getTitle();
        final TextView titleTextview = ((ItemViewHolder) viewHolder).title;
        titleTextview.setText(title);
        final CheckBox checkBox = ((ItemViewHolder) viewHolder).checkBox;
        if (item.isDone()) {
            titleTextview.setPaintFlags(titleTextview.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            checkBox.setChecked(true);
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setDone(isChecked);
                try {
                    item.pin();
                    item.saveEventually();
                    if (isChecked) {
                        titleTextview.setPaintFlags(titleTextview.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        titleTextview.setPaintFlags(titleTextview.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return mItems != null && position < mItems.size() ? ViewTypes.ITEM : ViewTypes.ADD_ITEM;
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 1 : mItems.size() + 1;
    }

    private void createItem(String title) {
        ShoppingListItem item = new ShoppingListItem();
        item.setUuidString();
        item.setShoppingList(mEmptyShoppingList);
        item.setTitle(title);
        item.setDone(false);
        item.setDraft(true);
        item.setAuthor(ParseUser.getCurrentUser());

        mItems.add(item);

        notifyItemInserted(mItems.size() - 1);

        item.pinInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    DontforgetParseFunctions.syncToParse();
                    ((DetailActivity) mContext).loadItemsFromParse();
                    loadObjectsInBackground();
                } else {
                    Toast.makeText(mContext, R.string.saveItemError, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        @InjectView(android.R.id.text1) TextView title;
        @InjectView(R.id.checkBox) CheckBox checkBox;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public static class AddItemViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.add_item) Button addItem;

        public AddItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

    }

}