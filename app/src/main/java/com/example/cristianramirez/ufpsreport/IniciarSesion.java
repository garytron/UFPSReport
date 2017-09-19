package com.example.cristianramirez.ufpsreport;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.util.StringBuilderPrinter;
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
    private SharedPreferences session;

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

                        if(!resultado.equals("failed"))
                        {
                            String tipo = "Alumno";
                            //Guardar la sesion del profesor o estudiante.
                            session = getApplicationContext().getSharedPreferences("Session",0);
                            SharedPreferences.Editor edit = session.edit();

                            //Colocamos los valores en sesión.
                            edit.putString("codigo",resultado);

                            edit.commit(); //Guardar cambios.

                            switch (tipo){
                                case "Alumno":
                                    Intent i = new Intent(getApplicationContext(), MenuEstudiante.class);
                                    startActivity(i);
                                    break;
                                case "Profesor":
                                    Intent i2 = new Intent(getApplicationContext(),MenuProfesor.class);
                                    startActivity(i2);
                                    break;
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Código o contraseña incorrecta.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        };

        tr.start();

    }

    public String login(String codigo, String password)
    {
        URL url;
        String cadena = "";
        StringBuilder result = null;

        try
        {
            url =  new URL("http://gidis.ufps.edu.co:8088/servicios_arch/persona/select");
            HttpURLConnection conection = (HttpURLConnection) url.openConnection();
            //Colocamos a la conexion que sea metodo POST
            conection.setRequestMethod("POST");
            // Ponemos los datos
            String data = "codigo="+codigo+"&password="+password;
            // Habilitamos la escritura
            conection.setDoOutput(true);
            // Escribimos los datos
            conection.getOutputStream().write(data.getBytes());
            //Recogemos la respuesta.
            InputStream responseBody = conection.getInputStream();
            InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
            JsonReader jsonReader = new JsonReader(responseBodyReader);

            jsonReader.beginObject(); // Start processing the JSON object
            while (jsonReader.hasNext()) { // Loop through all keys
                String key = jsonReader.nextName(); // Fetch the next key
                if (key.equals("result")) { // Check if desired key
                    // Fetch the value as a String
                    cadena = jsonReader.nextString();

                    // Do something with the value
                    // ...

                    break; // Break out of the loop
                }else if (key.equals("codigo"))
                {
                    cadena = jsonReader.nextString();
                }
                else {
                    jsonReader.skipValue(); // Skip values of other keys
                }
            }

        }catch(Exception e){ }

        return cadena;
    }
}
