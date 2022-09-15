package com.example.smartattendence;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendence.Models.Employee;
import com.example.smartattendence.databinding.ActivityEmployeeRegisterBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.util.Objects;
import java.util.UUID;

public class employee_register extends AppCompatActivity {

    ActivityEmployeeRegisterBinding binding;
    private FirebaseAuth auth;
    ImageView ImageView1;
    StorageReference mstorageRef;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityEmployeeRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        auth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mstorageRef = FirebaseStorage.getInstance().getReference();

        ProgressDialog progressDialog = new ProgressDialog(employee_register.this);
        progressDialog.setTitle("Ceating Account");
        progressDialog.setMessage("We are creating your Account");

        binding.button3.setOnClickListener(view -> auth.createUserWithEmailAndPassword(binding.emEmail.getText().toString(),
                binding.emPassword.getText().toString()).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                Employee employee = new Employee(binding.employeeName2.getText().toString(),
                        binding.emEmail.getText().toString(),
                        binding.editTextPhone6.getText().toString(),
                        binding.emPassword.getText().toString());

                String id = Objects.requireNonNull(task.getResult().getUser()).getUid();
                database.getReference().child("Employee").child(id).setValue(employee);

                Toast.makeText(employee_register.this, "Employee Registered Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(employee_register.this, employee_login.class);
                startActivity(intent);

            } else {
                Toast.makeText(this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
            }

        })
        );


        ImageView1 = findViewById(R.id.imageView1);
        binding.button7.setOnClickListener(view -> {
            if (PackageManager.PERMISSION_GRANTED != checkSelfPermission(Manifest.permission.CAMERA))
            {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 101);
            }

            else {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == 101){
                assert data != null;
                onCaptureImageResult(data);
            }
        }

    }
    private void onCaptureImageResult(Intent data){
        Bitmap thumbnail = (Bitmap)  data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG,90,bytes);
        byte[] bb = bytes.toByteArray();
        //String file = Base64.encodeToString(bb, Base64.DEFAULT);
        ImageView1.setImageBitmap(thumbnail);

        uploadToFirebase(bb);
    }

    private void uploadToFirebase(byte[] bb){
        StorageReference sr = mstorageRef.child("EmployeeImage/"+ UUID.randomUUID().toString());
        sr.putBytes(bb).addOnSuccessListener(taskSnapshot -> Toast.makeText(employee_register.this,
                "Successfully Upload",
                Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(employee_register.this,
                ""+"Failed To Upload",
                Toast.LENGTH_SHORT).show());

    }

}