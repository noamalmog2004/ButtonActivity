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
    public EditText etPassword, etEmail, etFirstnanme, etAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //btnRegister = findViewById(R.id.btnRegister);
        //btnRegister.setOnClickListener(this);
        etAge = findViewById(R.id.etAge);
        etPassword = findViewById(R.id.etPassword);
        etFirstnanme = findViewById(R.id.etFirstname);
        etEmail = findViewById(R.id.etEmail);
    }
    public void onClick(View view) {
        firebaseDB db = new firebaseDB();
        String age = etAge.getText().toString();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();
        String firstname = etFirstnanme.getText().toString();
        if(!(db.firstnameValid(firstname)))
            Toast.makeText(getApplicationContext(),"First name is not valid",Toast.LENGTH_SHORT).show();
        else if(!(db.emailValid(email)))
            Toast.makeText(getApplicationContext(),"Email is not valid",Toast.LENGTH_SHORT).show();
        else if(!(db.ageValid(age)))
            Toast.makeText(getApplicationContext(),"Age is not valid",Toast.LENGTH_SHORT).show();
        else if(!(db.passwordValid(password)))
            Toast.makeText(getApplicationContext(),"Password is not valid",Toast.LENGTH_SHORT).show();
        else
            db.register(password,email,age,firstname);

    }
}