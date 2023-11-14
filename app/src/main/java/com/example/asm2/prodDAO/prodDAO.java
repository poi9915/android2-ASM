package com.example.asm2.prodDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm2.Database.DbHelper;
import com.example.asm2.Model.User;

import java.util.ArrayList;

public class prodDAO {
    private DbHelper dbHelper;
    private static final String TB_USER = "user";
    private static final String TB_PROD = "product";

    private SQLiteDatabase db;

    public prodDAO(Context context) {
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
}
