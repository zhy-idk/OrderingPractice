package com.zxcvb.foodordering;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    List<CartModel> cart;

    public OrderAdapter(List<CartModel> cart) {
        this.cart = cart;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        holder.tvOrderFood.setText(cart.get(position).getFoodName());
        holder.tvOrderRestaurant.setText(cart.get(position).getRestaurantName());
        holder.tvOrderStatus.setText(cart.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return cart.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderFood, tvOrderRestaurant, tvOrderStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderFood = itemView.findViewById(R.id.tvOrderFood);
            tvOrderRestaurant = itemView.findViewById(R.id.tvOrderRestaurant);
            tvOrderStatus = itemView.findViewById(R.id.tvOrderStatus);

        }
    }
}
