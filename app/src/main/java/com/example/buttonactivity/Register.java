package com.example.buttonactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.File;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button btnRegister, btnBack;
    public EditText etPassword, etEmail, etFullname, etUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.loginbtn);
        btnRegister.setOnClickListener(this);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etFullname = findViewById(R.id.etFullname);
        etEmail = findViewById(R.id.etEmail1);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
    }
    public void onClick(View view) {
        //TODO: Copy stuff from friebaseDB in here to register here and not in firebaseDB
        //TODO: Then only import validation functions
        //TODO: GET HELP FROM MICHAL
        firebaseDB db = new firebaseDB();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();
        String fullname = etFullname.getText().toString();




        if (view == btnRegister) {
//            if (!(db.fullnameValid(fullname)))
//                Toast.makeText(getApplicationContext(), "Full name is not valid", Toast.LENGTH_SHORT).show();
//            else if (!(db.passwordValid(password)))
//                Toast.makeText(getApplicationContext(), "Password is not valid", Toast.LENGTH_SHORT).show();
//            else if (!(db.emailValid(email)))
//                Toast.makeText(getApplicationContext(), "Email is not valid", Toast.LENGTH_SHORT).show();
//            else if (!(db.usernameValid(username)))
//                Toast.makeText(getApplicationContext(), "username is not valid", Toast.LENGTH_SHORT).show();
//            else {
            try {
                db.register(email, password, fullname, username);
//              Uri imageUri2;
//                File imageFile = new File(Environment.getExternalStorageDirectory(), "profile2.jpg");
//                imageUri2 = Uri.fromFile(imageFile);
                Uri imageUri2 = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.profile2);
                StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                String userEmail2 = db.auth.getCurrentUser().getEmail();
                StorageReference imageRef = storageRef.child("images/"+userEmail2+".jpg");

                UploadTask uploadTask = imageRef.putFile(imageUri2);

                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Image was successfully uploaded to Firebase Storage
                        Toast.makeText(Register.this, "uploaded defualt pic", Toast.LENGTH_SHORT).show();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Failed to upload image to Firebase Storage
                        Toast.makeText(Register.this, "Failed to upload image to Firebase Storage\n", Toast.LENGTH_SHORT).show();

                    }
                });
                Intent i = new Intent(Register.this, MainActivity.class);
                startActivity(i);
            }
                catch (Exception e){
                    Toast.makeText(Register.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
//        }
        }
        if (view == btnBack)
        {
            Intent i = new Intent(Register.this, Login.class);
            startActivity(i);
        }
    }
}