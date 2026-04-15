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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CartFragment extends Fragment {
    RecyclerView rvCart;
    Button btnCheckout;
    List<CartModel> cartModelList;
    List<Object> groupedList;
    SharedPrefManager prefManager;
    CartAdapter adapter;

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
        groupedList = groupByRestaurant(cartModelList);

        Gson gson = new Gson();
        Log.d("CartFragment", "Raw cart data: " + gson.toJson(prefManager.getCartMap()));
        Log.d("CartFragment", "Filtered user cart: " + gson.toJson(cartModelList));
        Log.d("CartFragment", "Grouped list: " + gson.toJson(groupedList));

        adapter = new CartAdapter(groupedList);
        rvCart.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvCart.setAdapter(adapter);

        btnCheckout = view.findViewById(R.id.btnCheckout);

        btnCheckout.setOnClickListener(view1 -> {
            setStatus();
        });
    }

    public void queryUserCart() {
        String userId = AuthActivity.LOGGEDUSER.getId();
        cartModelList = prefManager.loadCartModel();
        List<CartModel> result = new ArrayList<>();

        for (int i = 0; i < cartModelList.size(); i++) {
            if (cartModelList.get(i).getCustomerId().equals(userId)
                    && cartModelList.get(i).getStatus().equals("cart")) {
                result.add(cartModelList.get(i));
            }
        }

        cartModelList = result;
    }

    public void setStatus() {
        for (int i = 0; i < cartModelList.size(); i++) {
            cartModelList.get(i).setStatus("preparing");
            prefManager.updateCart(cartModelList.get(i).getId(), cartModelList.get(i));
        }
        cartModelList.clear();
        groupedList.clear();
        adapter.notifyDataSetChanged();
    }

    public List<Object> groupByRestaurant(List<CartModel> cartItems) {
        Map<String, List<CartModel>> grouped = new LinkedHashMap<>();
        for (CartModel item : cartItems) {
            grouped.computeIfAbsent(item.getRestaurantName(), k -> new ArrayList<>()).add(item);
        }
        List<Object> result = new ArrayList<>();
        for (Map.Entry<String, List<CartModel>> entry : grouped.entrySet()) {
            result.add(entry.getKey());
            result.addAll(entry.getValue());
        }
        return result;
    }
}