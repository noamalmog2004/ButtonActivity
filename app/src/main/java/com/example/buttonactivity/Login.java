package com.example.buttonactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity implements View.OnClickListener {

    Button btnRegister1;
    EditText etEmail, etPassword;
    Button btnLogin;
    //TextView tvSignUpNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail1);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister1 = findViewById(R.id.btnRegister1);
        btnRegister1.setOnClickListener(this);
        btnLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        firebaseDB db = new firebaseDB();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();
        if (view == btnRegister1)
        {
            Intent i = new Intent(Login.this, Register.class);
            startActivity(i);
        }
        if (view == btnLogin)
        {
            if(db.login(email, password))
            {
                Toast.makeText(Login.this, "email exists!!!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(Login.this, "email or password doesn't exists", Toast.LENGTH_SHORT).show();
            }
        }
    }
}