package com.example.smartattendence;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendence.databinding.ActivityCompanyLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class company_login extends AppCompatActivity {

    ActivityCompanyLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityCompanyLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ProgressDialog progressDialog = new ProgressDialog(company_login.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Login to your Account");
        Objects.requireNonNull(getSupportActionBar()).hide();
        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();


        binding.companyloginButton1.setOnClickListener(view -> {

            progressDialog.show();
            auth.signInWithEmailAndPassword(binding.etEmail.getText().toString(),binding.etPassword.getText().toString()).addOnCompleteListener(task -> {
                progressDialog.dismiss();
                if (task.isSuccessful()){
                    Intent intent = new Intent(company_login.this,company_dashboard.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
        binding.button4.setOnClickListener(view -> {
            Intent intent = new Intent(company_login.this, register_company.class);
            startActivity(intent);
        });

        /*if (auth.getCurrentUser()!= null){
            Intent intent = new Intent(company_login.this, company_dashboard.class);
            startActivity(intent);
        }*/

    }
}