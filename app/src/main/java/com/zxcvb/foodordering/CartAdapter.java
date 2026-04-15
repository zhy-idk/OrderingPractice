package com.zxcvb.foodordering;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    List<CartModel> cart;

    public CartAdapter(List<CartModel> cart) {
        this.cart = cart;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        holder.tvCartFood.setText(cart.get(position).getFoodName());
        holder.tvCartRestaurant.setText(cart.get(position).getRestaurantName());

        holder.btnRemove.setOnClickListener(view -> {
            SharedPrefManager prefManager = new SharedPrefManager(holder.itemView.getContext());
            cart.remove(holder.getBindingAdapterPosition());

            prefManager.saveCart(cart);
            notifyDataSetChanged();

        });
    }

    @Override
    public int getItemCount() {
        return cart.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCartFood, tvCartRestaurant;
        Button btnRemove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCartFood = itemView.findViewById(R.id.tvCartFood);
            tvCartRestaurant = itemView.findViewById(R.id.tvCartRestaurant);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}
