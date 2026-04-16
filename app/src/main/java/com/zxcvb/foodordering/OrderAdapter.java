package com.zxcvb.foodordering;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    List<Object> items;

    public OrderAdapter(List<Object> items) {
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_header, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).tvOrderHeader.setText((String) items.get(position));
        } else if (holder instanceof ItemViewHolder) {
            String[] entry = (String[]) items.get(position);
            ((ItemViewHolder) holder).tvOrderFood.setText(entry[0]);
            ((ItemViewHolder) holder).tvOrderStatus.setText(entry[1]);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderHeader;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderHeader = itemView.findViewById(R.id.tvOrderHeader);
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderFood, tvOrderStatus;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderFood = itemView.findViewById(R.id.tvOrderFood);
            tvOrderStatus = itemView.findViewById(R.id.tvOrderStatus);
        }
    }
}
