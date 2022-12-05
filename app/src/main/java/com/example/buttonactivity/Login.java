package com.example.buttonactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button btnRegister1;
    EditText etUserName, etPassword;
    Button btnLogin;
    //TextView tvSignUpNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPassword = findViewById(R.id.etPassword);
        etUserName = findViewById(R.id.etUserName);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister1 = findViewById(R.id.btnRegister1);
        btnRegister1.setOnClickListener(this);
        btnLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin)
        {
            Intent i = new Intent(Login.this, MainActivity.class);
            startActivity(i);
        }
        if (view == btnRegister1)
        {
            Intent i = new Intent(Login.this, Register.class);
            startActivity(i);
        }
    }
}