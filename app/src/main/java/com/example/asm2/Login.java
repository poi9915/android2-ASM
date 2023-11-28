package com.example.asm2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.asm2.Model.User;
import com.example.asm2.prodDAO.ProdDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    TextView tvLogin_SignUp;
    Button btnLogin;
    TextInputLayout et_LoginUsername, et_Password;

    //test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_LoginUsername = findViewById(R.id.et_LoginUsername);
        et_Password = findViewById(R.id.et_LoginPassword);

        tvLogin_SignUp = findViewById(R.id.tvLogin_SignUp);
        btnLogin = findViewById(R.id.btnLogin);
        ProdDAO dao = new ProdDAO(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        tvLogin_SignUp.setOnClickListener(view -> {

            startActivity(new Intent(Login.this, SignUp.class));
        });

        ArrayList<User> listUser = dao.getALlUser();
        btnLogin.setOnClickListener(view -> {
            String username = et_LoginUsername.getEditText().getText().toString();
            String pass = et_Password.getEditText().getText().toString();
            for (User u :
                    listUser) {
                if (u.getUsername().equals(username) && u.getPass().equals(pass)) {
                    startActivity(new Intent(Login.this, MainActivity.class));
                    Toast.makeText(this, "Đăng nhập thành công!!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            Toast.makeText(this, "Sai mật khẩu hoặc tài khoản!!", Toast.LENGTH_SHORT).show();
        });
    }
}