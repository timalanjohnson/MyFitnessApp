package com.example.myfitnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Some code in this class is from http://www.androidtutorialshub.com/android-login-and-register-with-sqlite-database-tutorial/

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myFitDB.db";

    public static final String TABLE_USERS = "users_table";
    public static final String COL_ID = "ID";
    public static final String COL_USERNAME = "USERNAME";
    public static final String COL_PASSWORD = "PASSWORD";
    public static final String COL_WEIGHT = "WEIGHT";
    public static final String COL_HEIGHT = "HEIGHT";
    public static final String COL_TARGET = "TARGET";

    public DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(new StringBuilder("DROP IF TABLE EXISTS ").append(TABLE_USERS).toString());
        String createTable = new StringBuilder()
                .append("CREATE TABLE ").append(TABLE_USERS)
                .append("(ID INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append("USERNAME TEXT,")
                .append("PASSWORD TEXT,")
                .append("WEIGHT TEXT,")
                .append("HEIGHT TEXT,")
                .append("TARGET TEXT)").toString();
        db.execSQL(createTable);
        addUser("root","pass","77","177","5000");
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

    // Makes user object of user with specified username
    public void makeUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();

        // array of columns to fetch
        String[] columns = {
                COL_PASSWORD,
                COL_WEIGHT,
                COL_HEIGHT,
                COL_TARGET
        };

        // selection criteria
        String selection = COL_USERNAME + " = ?";

        // selection argument
        String[] selectionArgs = {username};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_password, user_weight, user_target FROM users_table WHERE username = 'tim';
         */
        Cursor cursor = db.query(TABLE_USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        if (cursorCount == 1) {
            User.setUsername(username);
            User.setUserPassword(cursor.getString(0));
            User.setUserWeight(cursor.getString(1));
            User.setUserHeight(cursor.getString(2));
            User.setUserTarget(cursor.getString(3));
        }

        cursor.close();
        db.close();
    }

    // Check if user with specified username exists.
    public boolean checkUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        // array of columns to fetch
        String[] columns = {
                COL_ID
        };

        // selection criteria
        String selection = COL_USERNAME + " = ?";

        // selection argument
        String[] selectionArgs = {username};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT id FROM users_table WHERE username = 'tim';
         */
        Cursor cursor = db.query(TABLE_USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    // Check if user with specified username and password exists
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        // array of columns to fetch
        String[] columns = {
                COL_ID
        };

        // selection criteria
        String selection = COL_USERNAME + " = ?" + " AND " + COL_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {username, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT username FROM users_table WHERE username = 'tim' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}
