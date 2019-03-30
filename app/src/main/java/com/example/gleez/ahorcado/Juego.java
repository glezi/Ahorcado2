package com.example.gleez.ahorcado;
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;


public class Juego {

    String[] categorys;
    String[] categorysData;
    String[] levels;
    Database db;
    Cursor cr;
    Context cntx;

    public Juego(Context context)
    {
        cntx = context;
        db = new Database(cntx);
        if(firstTime())
        {
            insertInitialData();
        }
    }

    public void insertInitialData()
    {
        categorys = cntx.getResources().getStringArray(R.array.categoria);
        levels = cntx.getResources().getStringArray(R.array.nivel);
        db.connectDb();
        for (String category : categorys)
        {
            int catint = cntx.getResources().getIdentifier(category, "array", cntx.getPackageName());
            categorysData = cntx.getResources().getStringArray(catint);
            for (int j = 0; j < categorysData.length; j++)
            {
                ContentValues value = new ContentValues();
                value.put("Id", j);
                value.put("Name", categorysData[j]);
                value.put("Category", category);
                db.insertData("Word", value);
            }
        }
        for (String level : levels)
        {
            for (String category : categorys)
            {
                ContentValues value = new ContentValues();
                value.put("Points", "0");
                value.put("Level", level);
                value.put("Category", category);
                db.insertData("Score", value);
            }
        }
        db.disconnectDb();
    }

    Boolean firstTime()
    {
        Boolean set;
        db.connectDb();
        cr = db.getData("SELECT COUNT(*) FROM Word");
        if (cr.moveToFirst())
        {
            if(cr.getInt(0) > 0)
            {
                set = false;
            }
            else
            {
                set = true;
            }
        }
        else
        {
            set = true;
        }
        cr.close();
        db.disconnectDb();
        return set;
    }

    public String getWord(String num, String category)
    {
        String str = null;
        db.connectDb();
        cr = db.getData("SELECT Name FROM Word WHERE Id = " + num + " AND Category = '" + category + "'");
        if (cr.moveToFirst())
        {
            str = cr.getString(0);
        }
        else
        {
            str = null;
        }
        cr.close();
        db.disconnectDb();
        return str;
    }

    public ArrayList<String> getScore()
    {
        ArrayList<String> str = new ArrayList<String>();
        db.connectDb();
        cr = db.getData("SELECT * FROM Score GROUP BY Level, Category");
        if (cr.moveToFirst())
        {
            do
            {
                String cat = cr.getString(cr.getColumnIndex("Category"));
                String lev = cr.getString(cr.getColumnIndex("Level"));
                String pto = cr.getString(cr.getColumnIndex("Points"));
                str.add("Nivel: " + lev + "\nCategoría: " + cat + "\nPuntos: " + pto);
            }
            while (cr.moveToNext());
        }
        else
        {
            str = null;
        }
        cr.close();
        db.disconnectDb();
        return str;
    }

    public int updateScore(String val, String category, String level)
    {
        int row;
        db.connectDb();
        ContentValues value = new ContentValues();
        value.put("Points", val);
        row = db.updateData("Score", "Level='" + level + "' AND Category ='" + category + "'", value);
        db.disconnectDb();
        return row;
    }

    public int deleteScore()
    {
        int row;
        db.connectDb();
        ContentValues value = new ContentValues();
        value.put("Points", "0");
        row = db.updateData("Score", null, value);
        db.disconnectDb();
        return row;
    }





}
