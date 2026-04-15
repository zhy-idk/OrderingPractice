package com.zxcvb.foodordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AuthActivity extends AppCompatActivity {

    EditText etNameLogin, etPassLogin, etNameSignup, etEmailSignup, etPassSignup;
    Button btnSignup, btnLogin;
    TextView btnSwitch;
    SharedPrefManager prefManager;
    List<CustomerModel> customerList;

    LinearLayout loginLinear, signupLinear;

    public static CustomerModel LOGGEDUSER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        prefManager = new SharedPrefManager(this);
        customerList = prefManager.loadCustomer();

        etNameLogin = findViewById(R.id.etNameLogin);
        etPassLogin = findViewById(R.id.etPassLogin);

        etNameSignup = findViewById(R.id.etNameSignup);
        etEmailSignup = findViewById(R.id.etEmailSignup);
        etPassSignup = findViewById(R.id.etPassSignup);
        btnSignup = findViewById(R.id.btnSignup);
        btnLogin = findViewById(R.id.btnLogin);

        btnSwitch = findViewById(R.id.btnSwitch);

        loginLinear = findViewById(R.id.loginLinear);
        signupLinear = findViewById(R.id.signupLinear);

        btnSwitch.setOnClickListener(view -> {
            if (loginLinear.getVisibility() == View.VISIBLE) {
                loginLinear.setVisibility(View.GONE);
                signupLinear.setVisibility(View.VISIBLE);
                btnSwitch.setText("Have an account already? Login here!");
            } else {
                loginLinear.setVisibility(View.VISIBLE);
                signupLinear.setVisibility(View.GONE);
                btnSwitch.setText("Don't have an account yet? Signup here!");
            }
        });

        btnSignup.setOnClickListener(view -> {
            String name = etNameSignup.getText().toString();
            String email = etEmailSignup.getText().toString();
            String pass = etPassSignup.getText().toString();

            signUp(name, email, pass);
        });

        btnLogin.setOnClickListener(view -> {
            String name = etNameLogin.getText().toString();
            String pass = etPassLogin.getText().toString();

            login(name, pass);
        });

        checkLoggedIn();

    }

    public void signUp(String name, String email, String pass) {
        String id = UUID.randomUUID().toString();
        CustomerModel customerModel = new CustomerModel(id, name, email, pass, "logged out");

        prefManager.addCustomer(id, customerModel);
        customerList = prefManager.loadCustomer();
        Toast.makeText(this, "Signed up successfully.", Toast.LENGTH_LONG).show();
    }

    public void login(String name, String pass) {

        if (!name.isEmpty() && !pass.isEmpty()) {
            for (int i = 0; i < customerList.size(); i++) {
                String jsonlog = new Gson().toJson(customerList.get(i));
                Log.d("auth", jsonlog);
                if ((customerList.get(i).getName().equals(name) || customerList.get(i).getEmail().equals(name))
                        && customerList.get(i).getPass().equals(pass)) {
                    customerList.get(i).setStatus("logged in");
                    LOGGEDUSER = customerList.get(i);
                    prefManager.updateCustomer(customerList.get(i).getId(), customerList.get(i));
                    String json = new Gson().toJson(customerList);
                    Log.d("auth", json);
                    Toast.makeText(this, "Logged in successfully.", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(this, MainActivity.class));
                }
            }
        }
    }

    public void checkLoggedIn() {
        String json = new Gson().toJson(customerList);
        Log.d("auth", json);

        // if (customerList.contains("logged in")) {
        // startActivity(new Intent(this, MainActivity.class));
        // }

        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getStatus().equals("logged in")) {
                LOGGEDUSER = customerList.get(i);
                startActivity(new Intent(this, MainActivity.class));
            } else {
                Toast.makeText(this, "Logged in successfully.", Toast.LENGTH_LONG).show();
            }
        }
    }

}