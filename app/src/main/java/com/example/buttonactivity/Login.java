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


public class Login extends AppCompatActivity implements View.OnClickListener {

    Button btnRegister1;
    EditText etEmail, etPassword;
    Button btnLogin;
    //TextView tvSignUpNow;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView googleBtn;

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


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        googleBtn = findViewById(R.id.google_btn);
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });


    }
    void signIn()
    {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            Exception ed = task.getException();
            Toast.makeText(Login.this, ed.getMessage().toString(), Toast.LENGTH_SHORT).show();
            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(Login.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void navigateToSecondActivity() {
        finish();
        Intent intent = new Intent(Login.this,SecondActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

        firebaseDB db = new firebaseDB();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();

        //test:
//        Intent i = new Intent(Login.this, MainActivity.class);
//        startActivity(i);


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
                startActivity(j);
            }
            else
            {
                Toast.makeText(Login.this, "email or password doesn't exists", Toast.LENGTH_SHORT).show();
            }
        }
    }
}