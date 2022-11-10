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

    EditText etUserName, etPassword;
    Button btnLogin;
    TextView tvSignUpNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPassword = findViewById(R.id.etPassword);
        etUserName = findViewById(R.id.etUserName);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        tvSignUpNow = findViewById(R.id.tvSignUpText);
        tvSignUpNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == tvSignUpNow)
                {
                    Toast.makeText(Login.this,"nOTT YET",Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder adb = new AlertDialog.Builder(Login.this);
                    adb.setMessage("Title");
                    adb.show();

                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (etUserName.getText().toString().equals("user") && etPassword.getText().toString().equals("password"))
        {
            Toast.makeText(this,"login good",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"login bad",Toast.LENGTH_SHORT).show();
        }
        if (view == btnLogin) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }
    }
}