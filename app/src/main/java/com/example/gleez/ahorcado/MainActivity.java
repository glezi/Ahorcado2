package com.example.gleez.ahorcado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.content.Context;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    String[] categorys;
    String[] descriptionCategorys;
    String[] levels;
    String[] descriptionLevels;
    Spinner categorySpinner, levelSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categorys = this.getResources().getStringArray(R.array.categoria);
        descriptionCategorys = this.getResources().getStringArray(R.array.descripcioncategoria);
        levels = this.getResources().getStringArray(R.array.nivel);
        descriptionLevels = this.getResources().getStringArray(R.array.descripcionnivel);
        categorySpinner = (Spinner)findViewById(R.id.category);
        levelSpinner = (Spinner)findViewById(R.id.nivel);
        categorySpinner.setAdapter(new SpinnerAdapter(this, R.layout.activity_soga, categorys));
        levelSpinner.setAdapter(new SpinnerAdapter2(this, R.layout.activity_soga, levels));


    }

    public void Navegar(View v)
    {
        switch(v.getId()) {
            case R.id.start:
                Intent i = new Intent(MainActivity.this, JuegoActivity.class);
                i.putExtra("categoria", categorySpinner.getSelectedItem().toString());
                i.putExtra("nivel", levelSpinner.getSelectedItem().toString());
                startActivity(i);
                break;
            case R.id.score:
                Intent i2 = new Intent(MainActivity.this, PunteoActivity.class);
                startActivity(i2);
                break;
        }
    }

    public class SpinnerAdapter extends ArrayAdapter<String>
    {

        public SpinnerAdapter(Context context, int textViewResourceId, String[] objects)
        {
            super(context, textViewResourceId, objects);
        }


        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent)
        {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.activity_soga, parent, false);
            TextView label=(TextView)row.findViewById(R.id.txt1);
            label.setText(categorys[position]);
            TextView sub=(TextView)row.findViewById(R.id.txt2);
            sub.setText(descriptionCategorys[position]);
            return row;
        }
    }

    public class SpinnerAdapter2 extends ArrayAdapter<String>
    {

        public SpinnerAdapter2(Context context, int textViewResourceId, String[] objects)
        {
            super(context, textViewResourceId, objects);
        }


        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent)
        {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.activity_soga, parent, false);
            TextView label=(TextView)row.findViewById(R.id.txt1);
            label.setText(levels[position]);
            TextView sub=(TextView)row.findViewById(R.id.txt2);
            sub.setText(descriptionLevels[position]);
            return row;
        }
    }

}
