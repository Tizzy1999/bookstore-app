package com.example.swe1709151;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "User.db";
    public static final String TABLE_NAME = "user_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    private String createUser = "create table "+ TABLE_NAME+
            "(email text primary key,"+
            "password text)";

    private String selectByEmail = "select * from "+TABLE_NAME+" where email=?";
    private String selectUser = "select * from "+TABLE_NAME+" where email=? and password=?";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
    }

    /**
     * Registration
     * @param email user email
     * @param password user password
     * @return true if registration success
     */
    public boolean insert(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        long insert = db.insert(TABLE_NAME, null, values);
        if(insert == -1) return false;
        else return true;
    }

    /**
     * Check whether email is already registered
     * @param email user mail
     * @return false if email exists
     */
    public boolean checkEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectByEmail, new String[]{email});
        if(cursor.getCount() > 0) return false;
        else return true;
    }

    /**
     * Authenticate user's account
     * @param email login email
     * @param password login password
     * @return true if authentication success
     */
    public boolean userAuthentication(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectUser, new String[]{email, password});
        if(cursor.getCount()>0) return true;
        else return false;
    }
}
