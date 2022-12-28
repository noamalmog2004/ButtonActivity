package com.example.buttonactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener {


    Button btnRegister;
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
        etEmail = findViewById(R.id.etEmail);
    }
    public void onClick(View view) {
        firebaseDB db = new firebaseDB();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();
        String firstname = etFullname.getText().toString();
        if (view == btnRegister)
            db.register(email, password, firstname,username);

    }
}