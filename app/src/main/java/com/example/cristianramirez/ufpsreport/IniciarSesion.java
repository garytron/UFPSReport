package com.example.cristianramirez.ufpsreport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IniciarSesion extends AppCompatActivity implements View.OnClickListener {

    EditText codigoUFPS, password;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        codigoUFPS = (EditText) findViewById(R.id.codigoUFPS);
        password = (EditText) findViewById(R.id.password);

        btnlogin = (Button) findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Thread tr = new Thread() {
            @Override
            public void run() {
                final String resultado = login(codigoUFPS.getText().toString(), password.getText().toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = verificaJSON(resultado);
                        String matricula = "";

                        if (r > 0) {
                            Intent i = new Intent(getApplicationContext(), MenuEstudiante.class);

                            try {
                                // Recogiendo valores del JSON
                                JSONArray arr = new JSONArray(resultado);
                                JSONObject jObj = arr.getJSONObject(0);
                                matricula = jObj.getString("codigoUFPS");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            i.putExtra("matricula", matricula);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "Usuario o Contraseña incorrectos", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        };

        tr.start();
    }

    public String login(String usu, String pas)
    {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try
        {
            url =  new URL("http://puertasabiertas.azurewebsites.net/apiREST.php?usuario="+usu+"&password="+pas);
            HttpURLConnection conection = (HttpURLConnection) url.openConnection();
            respuesta = conection.getResponseCode();

            result = new StringBuilder();
            if(respuesta==HttpURLConnection.HTTP_OK)
            {
                InputStream in = new BufferedInputStream(conection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while((linea = reader.readLine()) != null)
                    result.append(linea);
            }

        }catch(Exception e){}

        return result.toString();
    }

    public int verificaJSON(String response)
    {
        int res = 0;

        try{
            JSONArray json = new JSONArray(response);

            if(json.length()>0)
                res = 1;

        }catch (Exception e){}

        return res;
    }
}
