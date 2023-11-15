package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;

public class SignUp extends AppCompatActivity {
    MaterialToolbar appBarSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        appBarSignUp = findViewById(R.id.appBarSignUp);
        appBarSignUp.setNavigationOnClickListener(view ->{
            startActivity(new Intent(SignUp.this , Login.class));
        });
    }
}