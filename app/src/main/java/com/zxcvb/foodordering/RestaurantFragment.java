package com.zxcvb.foodordering;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RestaurantFragment extends Fragment {

    RecyclerView rvRestaurant;
    RestaurantAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvRestaurant = view.findViewById(R.id.rvRestaurant);


        List<String> restaurant = new ArrayList<>();
        restaurant.add("Jollibee");
        restaurant.add("Mcdonalds");
        restaurant.add("KFC");
        restaurant.add("Wendy's");
        restaurant.add("Chowking");
        restaurant.add("Greenwich");

        adapter = new RestaurantAdapter(restaurant);
        rvRestaurant.setAdapter(adapter);
        rvRestaurant.setLayoutManager(new GridLayoutManager(requireContext(), 2));
    }
}