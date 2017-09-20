package com.example.cristianramirez.ufpsreport;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class RegistrarIncidente extends AppCompatActivity implements View.OnClickListener{

    TextView infoQR;
    ImageView imagenQR;
    EditText descripcion;
    Button btnRegistrar;
    String dispositivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_incidente);

        Intent i = getIntent();

        dispositivo = i.getExtras().getString("computador");
        descripcion = (EditText) findViewById(R.id.descripcion);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        infoQR = (TextView) findViewById(R.id.infoQR);

        infoQR.setText(dispositivo);

        MultiFormatWriter multi = new MultiFormatWriter();

        /*
        try{
            BitMatrix matrix = multi.encode(dispositivo, BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder bar = new BarcodeEncoder();
            Bitmap map = bar.createBitmap(matrix);
            imagenQR.setImageBitmap(map);
        }catch (WriterException e) {
            e.printStackTrace();
        }*/

        btnRegistrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Toast.makeText(getApplicationContext(), "Registrado.", Toast.LENGTH_LONG).show();

       /* Thread tr = new Thread() {
            @Override
            public void run() {

                final String resultado = reportInsert(codigoUFPS.getText().toString(), password.getText().toString());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(resultado.equals("failed") && resultado.equals(null))
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

        tr.start();*/
    }
}
