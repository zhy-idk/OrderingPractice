package com.zxcvb.foodordering;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPrefManager {
    private static final String PREF_NAME = "FoodOrdering";
    private static final String KEY_CART = "cart";
    private static final String KEY_CUSTOMER = "customer";

    SharedPreferences prefs;
    Gson gson;

    public SharedPrefManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveCustomer(List<CustomerModel> lists){
        String json = gson.toJson(lists);
        prefs.edit().putString(KEY_CUSTOMER, json).apply();
    }
    public List<CustomerModel> loadCustomer() {
        String json = prefs.getString(KEY_CUSTOMER, null);
        if(json == null) return new ArrayList<>();

        Type type = new TypeToken<List<CustomerModel>>(){}.getType();
        return gson.fromJson(json, type);
    }

    public void saveCart(List<CartModel> lists) {
        String json = gson.toJson(lists);
        prefs.edit().putString(KEY_CART, json).apply();
    }
    public List<CartModel> loadCartModel() {
        String json = prefs.getString(KEY_CART,null);
        if(json == null) return new ArrayList<>();

        Type type = new TypeToken<List<CartModel>>(){}.getType();
        return gson.fromJson(json, type);
    }
}
