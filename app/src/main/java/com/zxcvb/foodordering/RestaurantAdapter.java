package com.zxcvb.foodordering;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    List<String> restaurant;

    public RestaurantAdapter(List<String> restaurant) {
        this.restaurant = restaurant;
    }

    @NonNull
    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.ViewHolder holder, int position) {
        holder.tvRestaurant.setText(restaurant.get(position));
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), MenuActivity.class);
            intent.putExtra("restaurant", restaurant.get(position));

            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return restaurant.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvRestaurant;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRestaurant = itemView.findViewById(R.id.tvRestaurant);
        }
    }
}
