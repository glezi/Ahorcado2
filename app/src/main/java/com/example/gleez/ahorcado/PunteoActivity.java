package com.example.gleez.ahorcado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;



public class PunteoActivity extends AppCompatActivity {

    Juego gm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punteo);

        ListView lstPuntuaciones = (ListView)findViewById(R.id.lstPuntuaciones);
        gm = new Juego(this);
        ArrayList<String> results = gm.getScore();
        lstPuntuaciones.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results));

    }

    public void Regresar(View v)
    {
        finish();
    }

    public void Borrar(View v)
    {
        gm.deleteScore();
        finish();
    }


}
