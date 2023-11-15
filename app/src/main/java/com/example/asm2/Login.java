package com.example.asm2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Login extends AppCompatActivity {
    TextView tvLogin_SignUp;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        tvLogin_SignUp = findViewById(R.id.tvLogin_SignUp);
        btnLogin = findViewById(R.id.btnLogin);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        tvLogin_SignUp.setOnClickListener(view -> {
            startActivity(new Intent(Login.this, SignUp.class));
        });
        btnLogin.setOnClickListener(view ->{
            startActivity(new Intent(Login.this , MainActivity.class));
        });
    }
}