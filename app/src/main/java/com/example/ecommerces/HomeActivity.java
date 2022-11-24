package com.example.ecommerces;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.ecommerces.fragment.HomeFragment;

public class HomeActivity extends AppCompatActivity {
    Fragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeFragment =new HomeFragment();
        loadFragment(homeFragment);
        
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.home_container, homeFragment).addToBackStack(null).commit();


    }


}