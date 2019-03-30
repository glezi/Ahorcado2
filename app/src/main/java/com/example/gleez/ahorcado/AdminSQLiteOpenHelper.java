package com.example.gleez.ahorcado;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {


    private static final int VERSION = 1;
    private static final String NAME = "ahorcado.db";
    private static final String TABLE = "CREATE TABLE IF NOT EXISTS Word(Id INT, Name TEXT, Category TEXT)";
    private static final String TABLE2 = "CREATE TABLE IF NOT EXISTS Score(Points TEXT, Level TEXT, Category TEXT)";

    public AdminSQLiteOpenHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE);
        db.execSQL(TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE Word");
        db.execSQL("DROP TABLE Score");
        onCreate(db);
    }
}
