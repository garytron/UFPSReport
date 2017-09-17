package com.example.cristianramirez.ufpsreport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MenuProfesor extends AppCompatActivity implements View.OnClickListener{


    Button btnValidar, btnCerrar, btnEscanear ;
    String textoQr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_profesor);
        btnValidar = (Button) findViewById(R.id.btnValidar);
        btnCerrar = (Button) findViewById(R.id.btnCerrar);
        btnEscanear = (Button) findViewById(R.id.btnEscanear);

     ;

    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.btnCerrar){

        }
        if(view.getId()==R.id.btnEscanear){
            final Activity act = this;
            IntentIntegrator integrator = new IntentIntegrator(act);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
            integrator.setPrompt("Escanear código QR de la computadora");
            integrator.setCameraId(0);
            integrator.setBeepEnabled(false);
            integrator.setBarcodeImageEnabled(false);
            integrator.initiateScan();
        }
        if(view.getId()==R.id.btnValidar){
        Intent validar = new Intent(getApplicationContext(),ValidarIncidente.class);
            startActivity(validar);
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
