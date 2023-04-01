package com.example.buttonactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity implements View.OnClickListener {

    Button btnRegister1;
    EditText etEmail, etPassword;
    Button btnLogin;

    @SuppressLint("WrongViewCast")
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
            Intent k = new Intent(Login.this, Register.class);
            startActivity(k);
        }
        if (view == btnLogin)
        {
            if(email.equals("")||password.equals(""))
            {
                Toast.makeText(Login.this, "email and password must contain something", Toast.LENGTH_SHORT).show();
                return;
            }
            if(db.login(email, password))
            {
                Toast.makeText(Login.this, "email exists!!!", Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Login.this, MainActivity.class);
                j.putExtra("whereToGo","home");
                startActivity(j);
            }
            else
            {
                Toast.makeText(Login.this, "email or password doesn't exists", Toast.LENGTH_SHORT).show();
            }
        }
    }
}