package com.example.asm2.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "PRODUCT.db";
    private static final int DB_VERSION = 1;
    private static final String TB_USER = "user";
    private static final String TB_PROD = "product";


    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        create table
        String createUserTB ="CREATE TABLE " + TB_USER + "(" +
                "userName TEXT PRIMARY KEY ,"+
                "pass     TEXT NOT NULL ,"+
                "NAME     TEXT NOT NULL ) " ;
        String createProdTB =
                "CREATE TABLE " + TB_PROD + "("+
                "prodID   INTEGER PRIMARY KEY AUTOINCREMENT," +
                "prodName TEXT NOT NULL ," +
                "price INTEGER NOT NULL, " +
                "soLuong INTEGER NOT NULL)";
        db.execSQL(createUserTB);
        db.execSQL(createProdTB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTblProd = "DROP TABLE IF EXISTS " + TB_PROD;
        String dropTblUser = "DROP TABLE IF EXISTS " + TB_USER;

        db.execSQL(dropTblProd);
        db.execSQL(dropTblUser);
        onCreate(db);
    }
}
