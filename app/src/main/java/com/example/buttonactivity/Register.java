package com.example.buttonactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        etEmail = findViewById(R.id.etEmail1);
    }
    public void onClick(View view) {
        firebaseDB db = new firebaseDB();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();
        String fullname = etFullname.getText().toString();

        if (view == btnRegister) {
            if (!(db.fullnameValid(fullname)))
                Toast.makeText(getApplicationContext(), "Full name is not valid", Toast.LENGTH_SHORT).show();
            else if (!(db.passwordValid(password)))
                Toast.makeText(getApplicationContext(), "Password is not valid", Toast.LENGTH_SHORT).show();
            else if (!(db.emailValid(email)))
                Toast.makeText(getApplicationContext(), "Email is not valid", Toast.LENGTH_SHORT).show();
            else if (!(db.usernameValid(username)))
                Toast.makeText(getApplicationContext(), "username is not valid", Toast.LENGTH_SHORT).show();
            else {
                db.register(username,password, email, fullname);
                Intent i = new Intent(Register.this, MainActivity.class);
                startActivity(i);
            }
        }

//        if (view == btnRegister) {
//            db.register(email, password, firstname, username);
//            Intent i = new Intent(Register.this, MainActivity.class);
//            startActivity(i);
//        }


    }

}