package com.zxcvb.foodordering;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharedPrefManager {
    private static final String PREF_NAME = "FoodOrdering";
    private static final String KEY_CART = "cart";
    private static final String KEY_CUSTOMER = "customer";

    private SharedPreferences prefs;
    private Gson gson;

    public SharedPrefManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    // ==================== CUSTOMER (HashMap CRUD) ====================

    private void saveCustomerMap(Map<String, CustomerModel> map) {
        prefs.edit().putString(KEY_CUSTOMER, gson.toJson(map)).apply();
    }

    public Map<String, CustomerModel> getCustomerMap() {
        String json = prefs.getString(KEY_CUSTOMER, null);
        if (json == null)
            return new HashMap<>();
        Type type = new TypeToken<Map<String, CustomerModel>>() {
        }.getType();
        Map<String, CustomerModel> map = gson.fromJson(json, type);
        return map != null ? map : new HashMap<>();
    }

    public List<CustomerModel> loadCustomer() {
        return new ArrayList<>(getCustomerMap().values());
    }

    public void addCustomer(String id, CustomerModel customer) {
        Map<String, CustomerModel> map = getCustomerMap();
        map.put(id, customer);
        saveCustomerMap(map);
    }

    public void updateCustomer(String id, CustomerModel customer) {
        Map<String, CustomerModel> map = getCustomerMap();
        if (map.containsKey(id)) {
            map.put(id, customer);
            saveCustomerMap(map);
        }
    }

    public void deleteCustomer(String id) {
        Map<String, CustomerModel> map = getCustomerMap();
        if (map.containsKey(id)) {
            map.remove(id);
            saveCustomerMap(map);
        }
    }

    // ==================== CART (HashMap CRUD) ====================

    private void saveCartMap(Map<String, CartModel> map) {
        prefs.edit().putString(KEY_CART, gson.toJson(map)).apply();
    }

    public Map<String, CartModel> getCartMap() {
        String json = prefs.getString(KEY_CART, null);
        if (json == null)
            return new HashMap<>();
        Type type = new TypeToken<Map<String, CartModel>>() {
        }.getType();
        Map<String, CartModel> map = gson.fromJson(json, type);
        return map != null ? map : new HashMap<>();
    }

    public List<CartModel> loadCartModel() {
        return new ArrayList<>(getCartMap().values());
    }

    public void addCart(String id, CartModel cart) {
        Map<String, CartModel> map = getCartMap();
        map.put(id, cart);
        saveCartMap(map);
    }

    public void updateCart(String id, CartModel cart) {
        Map<String, CartModel> map = getCartMap();
        if (map.containsKey(id)) {
            map.put(id, cart);
            saveCartMap(map);
        }
    }

    public void deleteCart(String id) {
        Map<String, CartModel> map = getCartMap();
        if (map.containsKey(id)) {
            map.remove(id);
            saveCartMap(map);
        }
    }

}
