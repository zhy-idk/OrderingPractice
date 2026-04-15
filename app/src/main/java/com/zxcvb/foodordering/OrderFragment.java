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

public class OrderFragment extends Fragment {

    RecyclerView rvOrder;
    List<CartModel> orderModelList;
    SharedPrefManager prefManager;
    OrderAdapter adapter;

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

        adapter = new OrderAdapter(orderModelList);
        rvOrder.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvOrder.setAdapter(adapter);

    }

    public void queryUserCart(){
        String userId = AuthActivity.LOGGEDUSER.getId();
        orderModelList = prefManager.loadCartModel();
        List<CartModel> result = new ArrayList<>();

        for (int i = 0; i < orderModelList.size(); i++){
            if (orderModelList.get(i).getCustomerId().equals(userId) && orderModelList.get(i).getStatus().equals("preparing")){
                result.add(orderModelList.get(i));
            }
        }

        orderModelList = result;
    }

    public void setStatus(){

    }
}