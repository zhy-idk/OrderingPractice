package com.zxcvb.foodordering;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {
    RecyclerView rvCart;
    Button btnCheckout;
    List<CartModel> cartModelList;
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

        adapter = new CartAdapter(cartModelList);
        rvCart.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvCart.setAdapter(adapter);

        btnCheckout = view.findViewById(R.id.btnCheckout);

        btnCheckout.setOnClickListener(view1 -> {
            setStatus();
        });
    }

    public void queryUserCart(){
        String userId = AuthActivity.LOGGEDUSER.getId();
        cartModelList = prefManager.loadCartModel();
        List<CartModel> result = new ArrayList<>();

        for (int i = 0; i < cartModelList.size(); i++){
            if (cartModelList.get(i).getCustomerId().equals(userId) && cartModelList.get(i).getStatus().equals("cart")){
                result.add(cartModelList.get(i));
            }
        }

        cartModelList = result;
    }

    public void setStatus(){
        String userId = AuthActivity.LOGGEDUSER.getId();
        List<CartModel> cartModelList1 = prefManager.loadCartModel();

        for (int i = 0; i < cartModelList1.size(); i++){
            if (cartModelList1.get(i).getCustomerId().equals(userId) && cartModelList1.get(i).getStatus().equals("cart")){
                cartModelList1.get(i).setStatus("preparing");
            }
        }
        prefManager.saveCart(cartModelList1);
        cartModelList.clear();
        adapter.notifyDataSetChanged();
    }
}