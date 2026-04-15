package com.zxcvb.foodordering;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
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
            String cartId = UUID.randomUUID().toString();
            CartModel cartModel = new CartModel(cartId, restaurant, menu.get(position), "cart", userId);

            prefs.addCart(cartId, cartModel);
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
