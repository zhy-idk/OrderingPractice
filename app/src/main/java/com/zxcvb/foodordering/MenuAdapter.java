package com.zxcvb.foodordering;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    List<String> menu;
    String restaurant;

    public MenuAdapter(List<String> menu, String restaurant) {
        this.menu = menu;
        this.restaurant = restaurant;
    }

    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);

        return new MenuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, int position) {
        holder.tvMenuItem.setText(menu.get(position));
        holder.btnAddToCart.setOnClickListener(view -> {
            SharedPrefManager prefs = new SharedPrefManager(view.getContext());
            String userId = AuthActivity.LOGGEDUSER.getId();

            // Find existing cart entry for this restaurant
            Map<String, CartModel> cartMap = prefs.getCartMap();
            CartModel existing = null;
            for (CartModel cm : cartMap.values()) {
                if (cm.getCustomerId().equals(userId)
                        && cm.getRestaurantName().equals(restaurant)
                        && cm.getStatus().equals("cart")) {
                    existing = cm;
                    break;
                }
            }

            if (existing != null) {
                existing.addFood(menu.get(position));
                prefs.updateCart(existing.getId(), existing);
            } else {
                String cartId = UUID.randomUUID().toString();
                List<String> foods = new ArrayList<>();
                foods.add(menu.get(position));
                CartModel cartModel = new CartModel(cartId, restaurant, foods, "cart", userId);
                prefs.addCart(cartId, cartModel);
            }
        });

    }

    @Override
    public int getItemCount() {
        return menu.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMenuItem;
        Button btnAddToCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMenuItem = itemView.findViewById(R.id.tvMenuItem);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }
}
