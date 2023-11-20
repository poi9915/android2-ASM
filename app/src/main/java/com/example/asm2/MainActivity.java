package com.example.asm2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm2.Adapter.ProdAdapter;
import com.example.asm2.Model.Product;
import com.example.asm2.prodDAO.ProdDAO;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MaterialToolbar appBarHome;
    FloatingActionButton fab_Add;
    DrawerLayout drawer_layout;
    RecyclerView recView;

    ProdDAO prodDAO;
    ArrayList<Product> list;
    ProdAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appBarHome = findViewById(R.id.appBarHome);
        drawer_layout = findViewById(R.id.drawer_layout);
        fab_Add = findViewById(R.id.fab_Add);
        recView = findViewById(R.id.recView);

        prodDAO = new ProdDAO(MainActivity.this);
        list = prodDAO.getAllProd();
        adapter = new ProdAdapter(MainActivity.this, list);
        adapter.notifyDataSetChanged();
        recView.setAdapter(adapter);
        recView.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(appBarHome);

        appBarHome.setNavigationOnClickListener(view -> {
            drawer_layout.openDrawer(GravityCompat.START);
        });


        fab_Add.setOnClickListener(view -> {
            showAddDialog();
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    public void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_add_prod, null);

        builder.setView(v);

        TextInputLayout tpl_dia_Name = v.findViewById(R.id.tpl_dia_Name);
        TextInputLayout tpl_dia_Price = v.findViewById(R.id.tpl_dia_Price);
        TextInputLayout tpl_dia_Num = v.findViewById(R.id.tpl_dia_Num);


        Button btnAddProd = v.findViewById(R.id.btnAddProd);
        AlertDialog dialog = builder.create();
        btnAddProd.setOnClickListener(view -> {
            //Add logic
            String name = tpl_dia_Name.getEditText().getText().toString();
            String price = tpl_dia_Price.getEditText().getText().toString();
            String num = tpl_dia_Num.getEditText().getText().toString();
            Product product = new Product(name, price, num);
            ProdDAO prodDAO = new ProdDAO(this);

            if (prodDAO.insertProd(product) > 0) {
                Toast.makeText(this, "Insert thành công", Toast.LENGTH_SHORT).show();
                list.add(product);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            } else {
                Toast.makeText(this, "Insert Thất bại", Toast.LENGTH_SHORT).show();
            }

        });
        dialog.show();

    }
}