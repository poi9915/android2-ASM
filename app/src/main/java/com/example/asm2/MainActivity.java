package com.example.asm2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.asm2.Framents.ManagerFragment;
import com.example.asm2.Framents.SettingFragment;
import com.example.asm2.prodDAO.ProdDAO;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    DrawerLayout drawer_layout;
    ProdDAO prodDAO;
    NavigationView naviView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        drawer_layout = findViewById(R.id.drawer_layout);
        prodDAO = new ProdDAO(this);
        naviView = findViewById(R.id.naviView);
        setFragment(new ManagerFragment());

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
                drawer_layout,
                toolbar,
                R.string.open,
                R.string.close
        );
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        drawer_layout.addDrawerListener(actionBarDrawerToggle);
        toolbar.setTitle("Quản Lý");
        naviView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.imnu_QL) {
                    toolbar.setTitle("Quản Lý");
                    setFragment(new ManagerFragment());
                } else if (item.getItemId() == R.id.imnu_Info) {
                    toolbar.setTitle("Giới Thiệu");
                    setFragment(new SettingFragment());
                } else if (item.getItemId() == R.id.imnu_setting) {
                    toolbar.setTitle("Cài đặt");
                    setFragment(new SettingFragment());
                } else if (item.getItemId() == R.id.imnu_logout) {
                    startActivity(new Intent(MainActivity.this, Login.class));
                }
                drawer_layout.close();
                return true;
            }
        });

    }

    public void setFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }

//    public void showAddDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = getLayoutInflater();
//        View v = inflater.inflate(R.layout.dialog_add_prod, null);
//
//        builder.setView(v);
//
//        TextInputLayout tpl_dia_Name = v.findViewById(R.id.tpl_dia_Name);
//        TextInputLayout tpl_dia_Price = v.findViewById(R.id.tpl_dia_Price);
//        TextInputLayout tpl_dia_Num = v.findViewById(R.id.tpl_dia_Num);
//
//
//        Button btnAddProd = v.findViewById(R.id.btnAddProd);
//        AlertDialog dialog = builder.create();
//        btnAddProd.setOnClickListener(view -> {
//            //Add logic
//            String name = tpl_dia_Name.getEditText().getText().toString();
//            String price = tpl_dia_Price.getEditText().getText().toString();
//            String num = tpl_dia_Num.getEditText().getText().toString();
//            Product product = new Product(name, price, num);
//            ProdDAO prodDAO = new ProdDAO(this);
//
//            if (prodDAO.insertProd(product) > 0) {
//                Toast.makeText(this, "Insert thành công", Toast.LENGTH_SHORT).show();
//                list.add(product);
//                adapter.notifyDataSetChanged();
//                dialog.dismiss();
//            } else {
//                Toast.makeText(this, "Insert Thất bại", Toast.LENGTH_SHORT).show();
//            }
//
//        });
//        dialog.show();
//
//    }
}