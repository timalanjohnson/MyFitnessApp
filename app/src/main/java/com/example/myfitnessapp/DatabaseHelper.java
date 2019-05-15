package com.example.myfitnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Some code in this class is from http://www.androidtutorialshub.com/android-login-and-register-with-sqlite-database-tutorial/

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myFitDB2.db";

    public static final String TABLE_USERS = "user";
    public static final String COL_ID = "id";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_WEIGHT = "weight";
    public static final String COL_HEIGHT = "height";
    public static final String COL_TARGET = "target";

    public DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = new StringBuilder()
                .append("CREATE TABLE ").append(TABLE_USERS)
                .append("(id INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append("username TEXT,")
                .append("password TEXT,")
                .append("weight TEXT,")
                .append("height TEXT,")
                .append("target TEXT)").toString();
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(new StringBuilder("DROP IF TABLE EXISTS ").append(TABLE_USERS).toString());
        onCreate(db);
    }

    public boolean addUser(String username, String password, String weight, String height, String target) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, username);
        contentValues.put(COL_PASSWORD, password);
        contentValues.put(COL_WEIGHT, weight);
        contentValues.put(COL_HEIGHT, height);
        contentValues.put(COL_TARGET, target);

        long result = db.insert(TABLE_USERS, null, contentValues);
        db.close();

        if (result == 1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean updateUser() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, User.getUsername());
        contentValues.put(COL_PASSWORD, User.getUserPassword());
        contentValues.put(COL_WEIGHT, User.getUserWeight());
        contentValues.put(COL_HEIGHT, User.getUserHeight());
        contentValues.put(COL_TARGET, User.getUserTarget());

        long result = db.update(TABLE_USERS, contentValues, COL_ID + " = ?", new String[]{String.valueOf(User.getUserID())});
        db.close();

        if (result == 1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkUser(String user) {
        // Check if record exists with username
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(new StringBuilder().append("SELECT id FROM user WHERE username = '").append(user).append("'").toString(), null);

        int count = cursor.getCount();
        cursor.moveToFirst();

        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUser(String user, String pass) {
        // Check if record exists with username and password
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(new StringBuilder().append("SELECT id FROM user WHERE username = '").append(user).append("' AND password = '").append(pass).append("'").toString(), null);

        int count = cursor.getCount();
        cursor.moveToFirst();

        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void makeUser(String user){
        // Get fields for username and populate User static class
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(new StringBuilder().append("SELECT password, weight, height, target FROM user WHERE username = '").append(user).append("'").toString(), null);

        cursor.moveToFirst();

        User.setUsername(user);
        User.setUserPassword(cursor.getString(0));
        User.setUserWeight(cursor.getString(1));
        User.setUserHeight(cursor.getString(2));
        User.setUserTarget(cursor.getString(3));

    }

}
