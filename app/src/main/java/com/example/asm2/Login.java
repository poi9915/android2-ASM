package com.example.asm2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {
    TextView tvLogin_SignUp;
    Button btnLogin;
    TextInputLayout et_LoginUsername ,et_Password;

    //test
    private String TestUser ="ph45090";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_LoginUsername = findViewById(R.id.et_LoginUsername);
        et_Password = findViewById(R.id.et_LoginPassword);

        tvLogin_SignUp = findViewById(R.id.tvLogin_SignUp);
        btnLogin = findViewById(R.id.btnLogin);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        tvLogin_SignUp.setOnClickListener(view -> {
            Toast.makeText(Login.this , "Sai username và password" , Toast.LENGTH_SHORT).show();

            startActivity(new Intent(Login.this, SignUp.class));
        });
        btnLogin.setOnClickListener(view ->{
            if (
                    !et_LoginUsername.getEditText().getText().toString().equals(TestUser) ||
                            !et_Password.getEditText().getText().toString().equals(TestUser)
            ){
                return;
            }
            startActivity(new Intent(Login.this , MainActivity.class));
        });
    }
}