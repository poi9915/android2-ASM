package com.example.asm2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    MaterialToolbar appBarHome ;
    FloatingActionButton fab_Add;
    DrawerLayout drawer_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appBarHome = findViewById(R.id.appBarHome);
        drawer_layout = findViewById(R.id.drawer_layout);
        fab_Add = findViewById(R.id.fab_Add);

        setSupportActionBar(appBarHome);

        appBarHome.setNavigationOnClickListener(view ->{
            drawer_layout.openDrawer(GravityCompat.START);
        });
        fab_Add.setOnClickListener(view ->{
            showAddDialog();
        });
    }
    public void showAddDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View v  = inflater.inflate(R.layout.dialog_add_prod ,null);

        builder.setView(v);

        TextInputLayout tpl_dia_Name = v.findViewById(R.id.tpl_dia_Name);
        TextInputLayout tpl_dia_Price = v.findViewById(R.id.tpl_dia_Price);
        TextInputLayout tpl_dia_Num = v.findViewById(R.id.tpl_dia_Num);

        Button btnAddProd = v.findViewById(R.id.btnAddProd);

        btnAddProd.setOnClickListener(view ->{
            //Add logic
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}