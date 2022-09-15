package com.example.smartattendence;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendence.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

            binding.button.setOnClickListener(view1 -> {
                Intent intent=new Intent(MainActivity.this,company_login.class);
                startActivity(intent);
        });

        binding.button2.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,employee_login.class);
            startActivity(intent);
        });
    }

    private long backPressTime;

    @Override
    public void onBackPressed() {
        if (backPressTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
        }
        else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressTime = System.currentTimeMillis();
    }

}




