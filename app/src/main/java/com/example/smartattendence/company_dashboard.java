package com.example.smartattendence;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.smartattendence.databinding.ActivityCompanyDashboardBinding;
import com.google.firebase.auth.FirebaseAuth;

public class company_dashboard extends AppCompatActivity {
    FirebaseAuth auth;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompanyDashboardBinding binding;
        binding = ActivityCompanyDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        replaceFragment(new comhomeFragment());

        binding.bottomnav1.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){

                case R.id.comhome:
                    replaceFragment(new comhomeFragment());
                    break;
                case R.id.comdata:
                    replaceFragment(new comdataFragment());
                    break;
                case R.id.comprofile:
                    replaceFragment(new comprofileFragment());
                    break;
            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout1,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.company_menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.setting:
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.logout:
                auth.signOut();
                Intent intent =  new Intent(company_dashboard.this,MainActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}