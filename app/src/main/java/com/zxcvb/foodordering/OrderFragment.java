package com.zxcvb.foodordering;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderFragment extends Fragment {

    RecyclerView rvOrder;
    List<CartModel> orderModelList;
    List<Object> displayList;
    SharedPrefManager prefManager;
    OrderAdapter adapter;

    Map<String, CartModel> orderModelHashMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prefManager = new SharedPrefManager(view.getContext());
        rvOrder = view.findViewById(R.id.rvOrder);
        queryUserCart();

        // Build flat display list directly from CartModels (no grouping needed)
        displayList = new ArrayList<>();
        for (CartModel cart : orderModelList) {
            displayList.add(cart.getRestaurantName());
            for (String food : cart.getFoodNames()) {
                displayList.add(new String[] { food, cart.getStatus() });
            }
        }

        adapter = new OrderAdapter(displayList);
        rvOrder.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvOrder.setAdapter(adapter);

    }

    public void queryUserCart() {
        String userId = AuthActivity.LOGGEDUSER.getId();
        orderModelHashMap = prefManager.getCartMap();

        orderModelList = orderModelHashMap.values().stream()
                .filter(r -> r.getCustomerId().equals(userId))
                .filter(r -> !r.getStatus().equals("cart"))
                .collect(Collectors.toList());
    }
}