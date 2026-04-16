package com.zxcvb.foodordering;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    List<Object> items;

    public CartAdapter(List<Object> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof String) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_header, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).tvHeader.setText((String) items.get(position));
        } else if (holder instanceof ItemViewHolder) {
            String[] foodEntry = (String[]) items.get(position);
            String cartId = foodEntry[0];
            String foodName = foodEntry[1];
            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            itemHolder.tvCartFood.setText(foodName);

            itemHolder.btnRemove.setOnClickListener(view -> {
                int pos = itemHolder.getBindingAdapterPosition();
                if (pos == RecyclerView.NO_POSITION)
                    return;

                String[] entry = (String[]) items.get(pos);
                SharedPrefManager prefManager = new SharedPrefManager(itemHolder.itemView.getContext());

                // Remove food from the CartModel's list
                CartModel cart = prefManager.getCartMap().get(entry[0]);
                if (cart != null) {
                    cart.removeFood(entry[1]);
                    if (cart.getFoodNames().isEmpty()) {
                        prefManager.deleteCart(entry[0]);
                    } else {
                        prefManager.updateCart(entry[0], cart);
                    }
                }

                items.remove(pos);

                // Remove header if no more items under it
                if (pos > 0 && items.get(pos - 1) instanceof String) {
                    if (pos >= items.size() || items.get(pos) instanceof String) {
                        items.remove(pos - 1);
                    }
                }

                notifyDataSetChanged();
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView tvHeader;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.tvCartHeader);
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvCartFood;
        Button btnRemove;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCartFood = itemView.findViewById(R.id.tvCartFood);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}
