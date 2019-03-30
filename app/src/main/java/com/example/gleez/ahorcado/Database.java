package com.example.gleez.ahorcado;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class Database {

    AdminSQLiteOpenHelper admin;
    SQLiteDatabase db;

    public Database(Context context) {
        admin = new AdminSQLiteOpenHelper(context);
    }

    public void connectDb() {
        db = admin.getWritableDatabase();
    }

    public void disconnectDb() {
        db.close();
    }

    public Cursor getData(String sql) {
        Cursor rows = db.rawQuery(sql, null);
        return rows;
    }

    public Long insertData(String table, ContentValues value) {
        Long row = db.insert(table, null, value);
        return row;
    }

    public int updateData(String table, String where, ContentValues value) {
        int row = db.update(table, value, where, null);
        return row;
    }


}
