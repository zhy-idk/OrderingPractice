package com.zxcvb.foodordering;

import java.util.ArrayList;
import java.util.List;

public class CartModel {
    String id;
    String restaurantName;
    List<String> foodNames;
    String status;
    String customerId;

    public CartModel(String id, String restaurantName, List<String> foodNames, String status, String customerId) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.foodNames = foodNames;
        this.status = status;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public List<String> getFoodNames() {
        return foodNames;
    }

    public void setFoodNames(List<String> foodNames) {
        this.foodNames = foodNames;
    }

    public void addFood(String foodName) {
        if (this.foodNames == null) {
            this.foodNames = new ArrayList<>();
        }
        this.foodNames.add(foodName);
    }

    public void removeFood(String foodName) {
        if (this.foodNames != null) {
            this.foodNames.remove(foodName);
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
