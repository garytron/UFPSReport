package com.example.cristianramirez.ufpsreport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ValidarIncidente extends AppCompatActivity implements  View.OnClickListener {
    CheckBox el1, el2, el3, el4, el5, el6, el7, el8;
    Button btnEliminar, btnAprobar;

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
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAprobar) {

        }
        if (view.getId() == R.id.btnEliminar) {

        }
    }

    public void llenarChecs() {
        final String respuesta = generarJson();
        ArrayList<Object> muestreo = new ArrayList<>();
        try {
            JSONArray jsons = new JSONArray(respuesta);
            for(int i = 0;i< jsons.length();i++){
             muestreo.add(jsons.get(i));
            }
            for(int j=0;j<8;j++){
            if(j==1){
            }
            }

        }catch (Exception e ){
            e.printStackTrace();
        }

    }
 public void limpiarC(){
     el1.setEnabled(false);
     el2.setEnabled(false);
     el3.setEnabled(false);
     el4.setEnabled(false);
     el5.setEnabled(false);
     el6.setEnabled(false);
     el7.setEnabled(false);
     el8.setEnabled(false);
 }

    public String generarJson() {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("");
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
}