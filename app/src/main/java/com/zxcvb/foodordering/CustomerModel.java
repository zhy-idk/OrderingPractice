package com.zxcvb.foodordering;

public class CustomerModel {
    String id;
    String name;
    String email;
    String pass;
    String status;

    public CustomerModel(String id, String name, String email, String pass, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
