package com.zxcvb.foodordering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnLogout;
    TextView tvUsername;
    SharedPrefManager prefManager;
    List<CustomerModel> customerList;

    BottomNavigationView bottomNav;
    NavController navController;
    NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefManager = new SharedPrefManager(this);
        customerList = prefManager.loadCustomer();

        btnLogout = findViewById(R.id.btnLogout);
        tvUsername = findViewById(R.id.tvUsername);

        tvUsername.setText(AuthActivity.LOGGEDUSER.getName());

        btnLogout.setOnClickListener(view -> {
            logout();
        });

        bottomNav = findViewById(R.id.bottomNav);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNav, navController);
    }

    void logout(){
        for (int i = 0; i <= customerList.size() - 1; i++) {
            if(customerList.get(i).getStatus().equals("logged in")){
                customerList.get(i).setStatus("logged out");
                prefManager.saveCustomer(customerList);
                finish();
            }
        }
    }
}