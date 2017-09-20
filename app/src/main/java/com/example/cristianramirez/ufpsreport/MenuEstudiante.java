package com.example.cristianramirez.ufpsreport;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MenuEstudiante extends AppCompatActivity implements View.OnClickListener{

    TextView codigotxt;
    Button btnEscanear, btnlogout;
    String textoQr;
    private SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_estudiante);

        btnEscanear = (Button) findViewById(R.id.btnEscanearAlumno);
        btnlogout = (Button) findViewById(R.id.btnlogout);
        codigotxt = (TextView) findViewById(R.id.textView2);

        //Traemos la sesion del usuario.
        pref = getSharedPreferences("Session",0);

        //Imprimos el codigo del alumno/profesor.
        codigotxt.setText("Hola " + pref.getString("codigo",null));
        btnEscanear.setOnClickListener(this);
        btnlogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent i;
        switch (v.getId())
        {
            case R.id.btnEscanearAlumno:
                final Activity act = this;
                IntentIntegrator integrator = new IntentIntegrator(act);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Escanear código QR de la computadora");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            break;

            case R.id.btnlogout:
                i = new Intent(getApplicationContext(),IniciarSesion.class);
                startActivity(i);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(result != null)
        {
            if(result.getContents() == null)
            {
                Toast.makeText(this, "Se canceló el escaneo", Toast.LENGTH_SHORT).show();
            }
            else
            {
                textoQr = result.getContents();
                Intent i = new Intent(getApplicationContext(),RegistrarIncidente.class);
                i.putExtra("computador",textoQr);
                startActivity(i);


            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
