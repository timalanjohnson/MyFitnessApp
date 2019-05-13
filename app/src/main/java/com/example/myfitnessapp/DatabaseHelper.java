package com.example.myfitnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myFitDB.db";
    public static final String TABLE_NAME = "users_table";
    public static final String Col1 = "ID";
    public static final String Col2 = "NAME";
    public static final String Col3 = "EMAIL";
    public static final String Col4 = "PASSWORD";
    public static final String Col5 = "WEIGHT";
    public static final String Col6 = "HEIGHT";
    public static final String Col7 = "TARGET";

    public DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = new StringBuilder()
                .append("CREATE TABLE ").append(TABLE_NAME)
                .append("(ID INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append("NAME TEXT,")
                .append("EMAIL TEXT,")
                .append("PASSWORD TEXT,")
                .append("WEIGHT TEXT,")
                .append("HEIGHT TEXT,")
                .append("TARGET TEXT)").toString();
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(new StringBuilder("DROP IF TABLE EXISTS ").append(TABLE_NAME).toString());
        onCreate(db);
    }

    public boolean addData(String name, String email, String password, String weight, String height, String target) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Col2, name);
        contentValues.put(Col3, email);
        contentValues.put(Col4, password);
        contentValues.put(Col5, weight);
        contentValues.put(Col6, height);
        contentValues.put(Col7, target);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == 1){
            return false;
        }
        else {
            return true;
        }
    }
}
