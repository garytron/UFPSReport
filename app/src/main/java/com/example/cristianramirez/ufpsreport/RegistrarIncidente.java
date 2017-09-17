package com.example.cristianramirez.ufpsreport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RegistrarIncidente extends AppCompatActivity {

    TextView infoQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_incidente);

        Intent i = getIntent();


        infoQR = (TextView) findViewById(R.id.infoQR);

        infoQR.setText(i.getExtras().getString("computador"));
    }
}
