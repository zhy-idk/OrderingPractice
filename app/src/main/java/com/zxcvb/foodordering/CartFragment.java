package com.zxcvb.foodordering;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CartFragment extends Fragment {
    RecyclerView rvCart;
    Button btnCheckout;
    List<CartModel> cartModelList;
    List<Object> displayList;
    SharedPrefManager prefManager;
    CartAdapter adapter;

    Map<String, CartModel> cartModelHashMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prefManager = new SharedPrefManager(view.getContext());
        rvCart = view.findViewById(R.id.rvCart);
        queryUserCart();

        // Build flat display list directly from CartModels (no grouping needed)
        displayList = new ArrayList<>();
        for (CartModel cart : cartModelList) {
            displayList.add(cart.getRestaurantName());
            for (String food : cart.getFoodNames()) {
                displayList.add(new String[] { cart.getId(), food });
            }
        }

        Gson gson = new Gson();
        Log.d("CartFragment", "Raw cart data: " + gson.toJson(prefManager.getCartMap()));
        Log.d("CartFragment", "Filtered user cart: " + gson.toJson(cartModelList));
        Log.d("CartFragment", "Display list size: " + displayList.size());

        adapter = new CartAdapter(displayList);
        rvCart.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvCart.setAdapter(adapter);

        btnCheckout = view.findViewById(R.id.btnCheckout);

        btnCheckout.setOnClickListener(view1 -> {
            setStatus();
        });
    }

    public void queryUserCart() {
        String userId = AuthActivity.LOGGEDUSER.getId();
        cartModelHashMap = prefManager.getCartMap();

        cartModelList = cartModelHashMap.values().stream()
                .filter(r -> r.getCustomerId().equals(userId))
                .filter(r -> r.getStatus().equals("cart"))
                .collect(Collectors.toList());
    }

    public void setStatus() {
        for (int i = 0; i < cartModelList.size(); i++) {
            cartModelList.get(i).setStatus("preparing");
            prefManager.updateCart(cartModelList.get(i).getId(), cartModelList.get(i));
        }

        cartModelList.clear();
        displayList.clear();
        adapter.notifyDataSetChanged();
    }

}