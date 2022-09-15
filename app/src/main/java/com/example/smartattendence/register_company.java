package com.example.smartattendence;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendence.Models.Company;
import com.example.smartattendence.databinding.ActivityRegisterCompanyBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class register_company extends AppCompatActivity {

     ActivityRegisterCompanyBinding binding;
     private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityRegisterCompanyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        auth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        ProgressDialog progressDialog = new ProgressDialog(register_company.this);
        progressDialog.setTitle("Ceating Account");
        progressDialog.setMessage("We are creating your Account");

        binding.button5.setOnClickListener(view -> auth.createUserWithEmailAndPassword(binding.etEmail.getText().toString() ,
                binding.etPassword.getText().toString() ).addOnCompleteListener(task -> {

                    if (task.isSuccessful()){
                        Company company = new Company(binding.companyname.getText().toString(),
                                binding.etEmail.getText().toString(),
                                binding.etPassword.getText().toString());

                        String id = Objects.requireNonNull(task.getResult().getUser()).getUid();
                        database.getReference().child("Company").child(id).setValue(company);

                        Toast.makeText(register_company.this, "Company Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(register_company.this,company_login.class);
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }

                })
        );

        binding.button6.setOnClickListener(view -> {
            Intent intent = new Intent(register_company.this, company_login.class);
            startActivity(intent);
        });

    }
}