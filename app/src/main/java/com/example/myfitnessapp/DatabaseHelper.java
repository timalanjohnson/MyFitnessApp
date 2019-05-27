package com.example.myfitnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Some code in this class is from http://www.androidtutorialshub.com/android-login-and-register-with-sqlite-database-tutorial/

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myFitDB2.db";

    public static final String TABLE_USERS = "tbl_user";
    public static final String COL_ID = "id";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_WEIGHT = "weight";
    public static final String COL_HEIGHT = "height";
    public static final String COL_WEIGHT_GOAL = "weight_goal";
    public static final String COL_STEP_GOAL = "step_goal";

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
                .append("weight_goal TEXT,")
                .append("step_goal TEXT)").toString();
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(new StringBuilder("DROP IF TABLE EXISTS ").append(TABLE_USERS).toString());
        onCreate(db);
    }

    public boolean addUser(String username, String password, String weight, String height, String weight_goal, String step_goal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, username);
        contentValues.put(COL_PASSWORD, password);
        contentValues.put(COL_WEIGHT, weight);
        contentValues.put(COL_HEIGHT, height);
        contentValues.put(COL_WEIGHT_GOAL, weight_goal);
        contentValues.put(COL_STEP_GOAL, step_goal);

        long result = db.insert(TABLE_USERS, null, contentValues);
        db.close();

        if (result == 1){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean updateUser() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, User.getUsername());
        contentValues.put(COL_PASSWORD, User.getUserPassword());
        contentValues.put(COL_WEIGHT, User.getUserWeight());
        contentValues.put(COL_HEIGHT, User.getUserHeight());
        contentValues.put(COL_WEIGHT_GOAL, User.getUserWeightGoal());
        contentValues.put(COL_STEP_GOAL, User.getUserStepGoal());

        long result = db.update(TABLE_USERS, contentValues, COL_ID + " = ?", new String[]{Integer.toString(User.getUserID())});
        db.close();

        if (result == 1){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkUser(String user) {
        // Check if record exists with username
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(new StringBuilder().append("SELECT id FROM " + TABLE_USERS + " WHERE username = '").append(user).append("'").toString(), null);

        int count = cursor.getCount();
        cursor.moveToFirst();

        cursor.close();

        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUser(String user, String pass) {
        // Check if record exists with username and password
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(new StringBuilder().append("SELECT id FROM " + TABLE_USERS + " WHERE username = '").append(user).append("' AND password = '").append(pass).append("'").toString(), null);

        int count = cursor.getCount();
        cursor.moveToFirst();

        cursor.close();

        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void makeUser(String user){
        // Get fields for username and populate User static class
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(new StringBuilder().append("SELECT id, password, weight, height, weight_goal, step_goal FROM " + TABLE_USERS + " WHERE username = '").append(user).append("'").toString(), null);

        cursor.moveToFirst();

        User.setUsername(user);
        User.setUserID(cursor.getInt(0));
        User.setUserPassword(cursor.getString(1));
        User.setUserWeight(cursor.getString(2));
        User.setUserHeight(cursor.getString(3));
        User.setUserWeightGoal(cursor.getString(4));
        User.setUserStepGoal(cursor.getString(5));

        cursor.close();

    }

}
