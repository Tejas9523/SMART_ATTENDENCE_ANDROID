package com.example.smartattendence;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendence.databinding.ActivityEmployeeLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class employee_login extends AppCompatActivity {

    ActivityEmployeeLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityEmployeeLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ProgressDialog progressDialog = new ProgressDialog(employee_login.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Login to your Account");
        Objects.requireNonNull(getSupportActionBar()).hide();
        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();


        binding.employeeloginButton.setOnClickListener(view -> {

            progressDialog.show();
            auth.signInWithEmailAndPassword(binding.emEmail.getText().toString(),binding.emPassword.getText().toString()).addOnCompleteListener(task -> {
                progressDialog.dismiss();
                if (task.isSuccessful()){
                    Intent intent = new Intent(employee_login.this,employee_dashboard.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
        binding.emregister.setOnClickListener(view -> {
            Intent intent = new Intent(employee_login.this, employee_register.class);
            startActivity(intent);
        });

        /*if (auth.getCurrentUser()!= null){
            Intent intent = new Intent(company_login.this, company_dashboard.class);
            startActivity(intent);
        }*/

    }
}