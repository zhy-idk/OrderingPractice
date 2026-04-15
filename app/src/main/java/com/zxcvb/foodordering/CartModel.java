package com.zxcvb.foodordering;

public class CartModel {
    String id;
    String restaurantName;
    String foodName;
    String status;
    String customerId;

    public CartModel(String id, String restaurantName, String foodName, String status, String customerId) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.foodName = foodName;
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

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
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
