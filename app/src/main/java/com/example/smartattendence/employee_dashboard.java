package com.example.smartattendence;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.smartattendence.databinding.ActivityEmployeeDashboardBinding;
import com.google.firebase.auth.FirebaseAuth;

public class employee_dashboard extends AppCompatActivity {
    FirebaseAuth auth;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEmployeeDashboardBinding binding;
        binding = ActivityEmployeeDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        replaceFragment(new comhomeFragment());

        binding.bottomnav2.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){

                case R.id.emphome:
                    replaceFragment(new emphomeFragment());
                    break;
                case R.id.empdata:
                    replaceFragment(new empdataFragment());
                    break;
                case R.id.emprofile:
                    replaceFragment(new empprofileFragment());
                    break;
            }
            return true;
        });

        binding.floatingActionButton.setOnClickListener(view -> {
            Intent intent=new Intent(employee_dashboard.this,cam.class);
            startActivity(intent);
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout2,fragment);
        fragmentTransaction.commit();
    }
    }
