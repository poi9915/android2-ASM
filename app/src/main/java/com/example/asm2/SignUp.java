package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.asm2.Model.User;
import com.example.asm2.prodDAO.ProdDAO;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {
    MaterialToolbar appBarSignUp;
    TextInputLayout et_UsernameSignUp , et_PasswordSignUp , et_FullnameSignUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        appBarSignUp = findViewById(R.id.appBarSignUp);
        ProdDAO dao = new ProdDAO(this);
        ArrayList<User> list = dao.getALlUser();
        et_UsernameSignUp= findViewById(R.id.et_UsernameSignUp);
        et_PasswordSignUp = findViewById(R.id.et_passwordSignUp);
        et_FullnameSignUp = findViewById(R.id.et_FullnameSignUp);
        appBarSignUp.setNavigationOnClickListener(view ->{

            startActivity(new Intent(SignUp.this , Login.class));
        });
    }
}