package com.zxcvb.foodordering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    List<String> menuList;
    RecyclerView rvMenu;
    MenuAdapter adapter;

    TextView tvMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String restaurant = getIntent().getStringExtra("restaurant");
        setMenuList(restaurant);

        tvMenu = findViewById(R.id.tvMenu);

        tvMenu.setText(restaurant);

        rvMenu = findViewById(R.id.rvMenu);
        adapter = new MenuAdapter(menuList, restaurant);
        rvMenu.setAdapter(adapter);
        rvMenu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    }
    public void setMenuList(String restaurant){

        switch(restaurant.toLowerCase()) {
            case "jollibee":
                menuList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.jollibee_array)));
                break;
            case "mcdonalds":
                menuList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.mcdo_array)));
                break;
            case "kfc":
                menuList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.kfc_array)));
                break;
            case "wendy's":
                menuList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.wendys_array)));
                break;
            case "chowking":
                menuList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.chowking_array)));
                break;
            case "greenwich":
                menuList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.greenwich_array)));
                break;
        }

    }

}