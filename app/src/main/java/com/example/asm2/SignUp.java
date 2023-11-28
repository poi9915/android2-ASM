package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.asm2.Model.User;
import com.example.asm2.prodDAO.ProdDAO;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {
    MaterialToolbar appBarSignUp;
    TextInputLayout et_UsernameSignUp , et_PasswordSignUp , et_FullnessSignUp;
    Button btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        appBarSignUp = findViewById(R.id.appBarSignUp);
        ProdDAO dao = new ProdDAO(this);
        et_UsernameSignUp= findViewById(R.id.et_UsernameSignUp);
        et_PasswordSignUp = findViewById(R.id.et_passwordSignUp);
        et_FullnessSignUp = findViewById(R.id.et_FullnameSignUp);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(view ->{
            String username = et_UsernameSignUp.getEditText().getText().toString();
            String pass  = et_PasswordSignUp.getEditText().getText().toString();
            String fullname = et_FullnessSignUp.getEditText().getText().toString();

            if (dao.insertUser(new User(username,pass,fullname))> 0 ){
                startActivity(new Intent(SignUp.this, Login.class));
            }


        });
    }
}