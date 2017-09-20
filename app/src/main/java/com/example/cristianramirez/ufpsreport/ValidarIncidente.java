package com.example.cristianramirez.ufpsreport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import java.util.ArrayList;
import java.util.List;

import Util.Reporte;

public class ValidarIncidente extends AppCompatActivity implements View.OnClickListener {
    CheckBox el1, el2, el3, el4, el5, el6, el7, el8;
    Button btnEliminar, btnAprobar,btnListar;
    String resultado = "";
    String accion = "";
    int mapeo=0;

    List<Reporte> aux;
    List<Reporte> retorno = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validar_incidente);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnAprobar = (Button) findViewById(R.id.btnAprobar);
        btnListar = (Button) findViewById(R.id.btnListar);
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
        btnListar.setOnClickListener(this);


        limpiarC();


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnAprobar) {
            if (mapeo > 0) {
                if (el1.isChecked()) {
                    retorno.add(aux.get(0));
                }
                if (el2.isChecked()) {
                    retorno.add(aux.get(1));
                }
                if (el3.isChecked()) {
                    retorno.add(aux.get(2));
                }
                if (el4.isChecked()) {
                    retorno.add(aux.get(3));
                }
                if (el5.isChecked()) {
                    retorno.add(aux.get(4));
                }
                if (el6.isChecked()) {
                    retorno.add(aux.get(5));
                }
                if (el7.isChecked()) {
                    retorno.add(aux.get(6));
                }
                if (el8.isChecked()) {
                    retorno.add(aux.get(7));
                }
                for (int t = 0; t < retorno.size(); t++) {
                    accion = "/reporte/updateEstado" + "?id_reporte=" + retorno.get(t).getId_reporte() + "&estado=validado";
                    AccesoRemoto a = new AccesoRemoto();
                    a.execute();
                }
                limpiarC();
                llenarChecs();
            } else {
                Log.e("prueba", "1");
                Toast.makeText(getApplicationContext(), "No Hay Reportes Disponibles", Toast.LENGTH_LONG).show();
            }


        }
        if (view.getId() == R.id.btnEliminar) {
            if (mapeo > 0) {
                if (el1.isChecked()) {
                    retorno.add(aux.get(0));
                }
                if (el2.isChecked()) {
                    retorno.add(aux.get(1));
                }
                if (el3.isChecked()) {
                    retorno.add(aux.get(2));
                }
                if (el4.isChecked()) {
                    retorno.add(aux.get(3));
                }
                if (el5.isChecked()) {
                    retorno.add(aux.get(4));
                }
                if (el6.isChecked()) {
                    retorno.add(aux.get(5));
                }
                if (el7.isChecked()) {
                    retorno.add(aux.get(6));
                }
                if (el8.isChecked()) {
                    retorno.add(aux.get(7));
                }
                for (int t = 0; t < retorno.size(); t++) {
                    accion = "/reporte/delete" + "?id_reporte=" + retorno.get(t).getId_reporte();
                    AccesoRemoto a = new AccesoRemoto();
                    a.execute();
                }
                limpiarC();
                llenarChecs();
            }
            if (view.getId() == R.id.btnListar) {
                limpiarC();
                llenarChecs();
            }

        }

    }
    public void llenarChecs() {

        int con = 0;
        accion = "/reporte/selectAll";

        try {
            AccesoRemoto a = new AccesoRemoto();
            a.execute();

            Gson miGson = new Gson();
            aux = miGson.fromJson(resultado, new TypeToken<List<Reporte>>() {
            }.getType());
            for (Reporte r : aux) {
                if (r.getEstado().equalsIgnoreCase("noValidado")) {
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
            }
            mapeo = aux.size();
            con = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void limpiarC() {
        el1.setEnabled(false);
        el1.setText("");
        el2.setEnabled(false);
        el2.setText("");
        el3.setEnabled(false);
        el3.setText("");
        el4.setEnabled(false);
        el4.setText("");
        el5.setEnabled(false);
        el5.setText("");
        el6.setEnabled(false);
        el6.setText("");
        el7.setEnabled(false);
        el7.setText("");
        el8.setEnabled(false);
        el8.setText("");
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