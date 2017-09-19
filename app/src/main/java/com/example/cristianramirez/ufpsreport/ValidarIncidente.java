package com.example.cristianramirez.ufpsreport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import Util.Reporte;

public class ValidarIncidente extends AppCompatActivity implements View.OnClickListener {
    CheckBox el1, el2, el3, el4, el5, el6, el7, el8;
    Button btnEliminar, btnAprobar;
    String resultado = "";
    String accion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validar_incidente);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnAprobar = (Button) findViewById(R.id.btnAprobar);
        el1 = (CheckBox) findViewById(R.id.checkBox1);
        el2 = (CheckBox) findViewById(R.id.checkBox2);
        el3 = (CheckBox) findViewById(R.id.checkBox3);
        el4 = (CheckBox) findViewById(R.id.checkBox4);
        el5 = (CheckBox) findViewById(R.id.checkBox5);
        el6 = (CheckBox) findViewById(R.id.checkBox6);
        el7 = (CheckBox) findViewById(R.id.checkBox7);
        el8 = (CheckBox) findViewById(R.id.checkBox8);

        btnAprobar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);

        limpiarC();


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAprobar) {
            accion = "";
            AccesoRemoto a = new AccesoRemoto();
            a.execute();
            limpiarC();
            llenarChecs();
        }
        if (view.getId() == R.id.btnEliminar) {
            accion = "";
            AccesoRemoto a = new AccesoRemoto();
            a.execute();
            limpiarC();
            llenarChecs();
        }

    }


    public void llenarChecs() {

        int con = 0;
        accion = "/reporte/selectAll";
        try {
            AccesoRemoto a = new AccesoRemoto();
            a.execute();

            Gson miGson = new Gson();
            List<Reporte> aux = miGson.fromJson(resultado, new TypeToken<List<Reporte>>() {
            }.getType());
            for (Reporte r : aux) {
                if (con == 0) {
                    el1.setEnabled(true);
                    el1.setText(aux.get(con).toString());
                }
                if (con == 1) {
                    el2.setEnabled(true);
                    el2.setText(aux.get(con).toString());
                }
                if (con == 2) {
                    el3.setEnabled(true);
                    el3.setText(aux.get(con).toString());
                }
                if (con == 3) {
                    el4.setEnabled(true);
                    el4.setText(aux.get(con).toString());
                }
                if (con == 4) {
                    el5.setEnabled(true);
                    el5.setText(aux.get(con).toString());
                }
                if (con == 5) {
                    el6.setEnabled(true);
                    el6.setText(aux.get(con).toString());
                }
                if (con == 6) {
                    el7.setEnabled(true);
                    el7.setText(aux.get(con).toString());
                }
                if (con == 7) {
                    el8.setEnabled(true);
                    el8.setText(aux.get(con).toString());
                }
                con++;
            }
            con = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void limpiarC() {
        el1.setEnabled(false);
        el2.setEnabled(false);
        el3.setEnabled(false);
        el4.setEnabled(false);
        el5.setEnabled(false);
        el6.setEnabled(false);
        el7.setEnabled(false);
        el8.setEnabled(false);
    }


    private class AccesoRemoto extends AsyncTask<Void, Void, String> {

        protected String doInBackground(Void... argumentos) {
            URL url = null;
            String linea = "";
            int respuesta = 0;
            StringBuilder result = null;

            try {

                url = new URL("http://gidis.ufps.edu.co:8088/servicios_arch" + accion);
                HttpURLConnection conection = (HttpURLConnection) url.openConnection();
                respuesta = conection.getResponseCode();

                result = new StringBuilder();
                if (respuesta == HttpURLConnection.HTTP_OK) {
                    InputStream in = new BufferedInputStream(conection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    while ((linea = reader.readLine()) != null)
                        result.append(linea);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            resultado=s;

           Toast.makeText(getApplicationContext(),"Cargando Datos", Toast.LENGTH_LONG).show();
        }
    }

}