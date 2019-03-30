package com.example.gleez.ahorcado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.*;
import java.util.ArrayList;
import java.util.Random;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;


public class JuegoActivity extends AppCompatActivity {


    MediaPlayer mp;
    int intentos = 4;
    int puntos = 0;
    int wordalength;
    String worda, CATEGORY, LEVEL;
    LinearLayout lyt, lytbtns;
    TextView txtPuntos, txtIntentos, txtCategoria;
    ImageView img;
    ArrayList<View> btns;
    Juego gm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        lyt = (LinearLayout)findViewById(R.id.word);
        lytbtns = (LinearLayout)findViewById(R.id.footer);
        txtPuntos = (TextView)findViewById(R.id.txtPuntaje);
        txtIntentos = (TextView)findViewById(R.id.txtIntentos);
        txtCategoria = (TextView)findViewById(R.id.txtCategoria);
        img = (ImageView)findViewById(R.id.img);
        btns = lytbtns.getTouchables();
        Bundle extras = getIntent().getExtras();
        CATEGORY = extras.getString("categoria");
        LEVEL = extras.getString("nivel");
        gm = new Juego(this);
        Construye();

    }

    public void Verificar(View v)
    {
        Button b = (Button)v;
        String str = b.getText().toString();
        b.setEnabled(false);
        if(worda.contains(str))
        {
            for (int i = -1; (i = worda.indexOf(str, i + 1)) != -1; )
            {
                mp = MediaPlayer.create(this, R.raw.win);
                mp.start();
                TextView txt = (TextView) findViewById(i);
                txt.setText(str);
                wordalength--;
            }
            if(wordalength == 0)
            {
                Toast.makeText(this,"ganaste",Toast.LENGTH_LONG).show();
                Construye();
                puntos++;
                txtPuntos.setText(String.valueOf(puntos));
                gm.updateScore(String.valueOf(puntos), CATEGORY, LEVEL);
            }
        }
        else
        {
            intentos--;
            mp = MediaPlayer.create(this, R.raw.fail);
            mp.start();
            Vibrator vb = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            vb.vibrate(500);
            img.setImageDrawable(null);
            txtIntentos.setText(String.valueOf(intentos));
            if(LEVEL.equals("FÁCIL"))
            {
                switch (intentos)
                {
                    case 5:
                        img.setBackgroundResource(R.drawable.ima2);
                        break;
                    case 4:
                        img.setBackgroundResource(R.drawable.ima3);
                        break;
                    case 3:
                        img.setBackgroundResource(R.drawable.ima41);
                        break;
                    case 2:
                        img.setBackgroundResource(R.drawable.ima4);
                        break;
                    case 1:
                        img.setBackgroundResource(R.drawable.ima51);
                        break;
                    case 0:
                        img.setBackgroundResource(R.drawable.ima5);
                        Toast.makeText(this,"La palabra era: " + worda,Toast.LENGTH_LONG).show();
                        Construye();
                        break;
                }
            }
            else
            {
                switch (intentos)
                {
                    case 3:
                        img.setBackgroundResource(R.drawable.ima2);
                        break;
                    case 2:
                        img.setBackgroundResource(R.drawable.ima3);
                        break;
                    case 1:
                        img.setBackgroundResource(R.drawable.ima4);
                        break;
                    case 0:
                        img.setBackgroundResource(R.drawable.ima5);
                        Toast.makeText(this,"La palabra era: " + worda,Toast.LENGTH_LONG).show();
                        Construye();
                        break;
                }
            }
        }
    }

    void Construye()
    {
        if(LEVEL.equals("FÁCIL"))
        {
            intentos = 6;
            txtIntentos.setText("6");
        }
        else
        {
            intentos = 4;
            txtIntentos.setText("4");
        }
        txtCategoria.setText(CATEGORY);
        lyt.removeAllViews();
        img.setImageDrawable(null);
        img.setBackgroundResource(R.drawable.ima1);
        for(View touchable : btns)
        {
            if( touchable instanceof Button )
            {
                touchable.setEnabled(true);
            }
        }
        Random r = new Random();
        worda = gm.getWord(String.valueOf(r.nextInt(80)), CATEGORY);
        wordalength = worda.length();
        for(int i = 0; i < wordalength; i++)
        {
            TextView row = new TextView(this);
            row.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            row.setId(i);
            row.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            row.setPadding(0, 0, 10, 0);
            row.setWidth(40);
            row.setBackgroundResource(R.drawable.borde);
            lyt.addView(row);
        }
    }


}
