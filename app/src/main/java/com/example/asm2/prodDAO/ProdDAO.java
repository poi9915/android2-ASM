package com.example.asm2.prodDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm2.Database.DbHelper;
import com.example.asm2.Model.Product;
import com.example.asm2.Model.User;

import java.util.ArrayList;

public class ProdDAO {
    private DbHelper dbHelper;
    private static final String TB_USER = "user";
    private static final String TB_PROD = "product";

    private SQLiteDatabase db;

    public ProdDAO(Context context) {
        this.dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertUser(User user) {
        ContentValues values = new ContentValues();
        values.put("userName", user.getUsername());
        values.put("pass", user.getPass());
        values.put("NAME", user.getFullName());
        return db.insert(TB_USER, null, values);
    }

    public ArrayList<User> getALlUser() {
        ArrayList<User> user = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TB_USER, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                String userName = cursor.getString(0);
                String pass = cursor.getString(1);
                String fullName = cursor.getString(2);

                user.add(new User(userName , pass ,fullName));
            } while (cursor.moveToNext());
        }
        return user;
    }

    public long insertProd(Product product){
        ContentValues values = new ContentValues();
        values.put("prodName" , product.getName());
        values.put("price" , product.getPrice());
        values.put("soLuong" , product.getSoLuong());
        return db.insert(TB_PROD , null , values);
    }
    public ArrayList<Product> getAllProd(){
        ArrayList<Product> list = new ArrayList<>();
        Cursor cursor =  db.rawQuery("SELECT * FROM " + TB_PROD , null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String price = cursor.getString(2);
                String soL = cursor.getString(3);
                list.add(new Product(id , name , price ,soL));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public int deleteProdByID(int id) {
        return db.delete(TB_PROD, "prodID = ?", new String[]{String.valueOf(id)});
    }
    public int deleteProdByObj(Product product){
        String[] id = new String[]{String.valueOf(product.getIdProd())};
        return db.delete(TB_PROD , "ID=?" , id);
    }
}
