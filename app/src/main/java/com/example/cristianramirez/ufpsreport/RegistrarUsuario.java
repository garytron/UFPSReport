package com.example.cristianramirez.ufpsreport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistrarUsuario extends AppCompatActivity implements View.OnClickListener{

    Button btnRegistrar;
    EditText codigo,nombre,apellido,correo,contraseña,repcontraseña;
    Spinner tipo ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        codigo = (EditText) findViewById(R.id.textcodigo);
        nombre = (EditText) findViewById(R.id.textnombre);
        apellido = (EditText) findViewById(R.id.textapellido);
        correo = (EditText) findViewById(R.id.textcorreo);
        contraseña = (EditText) findViewById(R.id.textcontraseña);
        repcontraseña = (EditText) findViewById(R.id.textrepetircontraseña);
        tipo = (Spinner) findViewById(R.id.combous);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrarU);
        ArrayAdapter<CharSequence> adapter =
                new ArrayAdapter <CharSequence> (this, android.R.layout.simple_spinner_item );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add("Estudiante");
        adapter.add("Profesor");
        tipo.setAdapter(adapter);
        btnRegistrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
    final String tipoU = tipo.getSelectedItem().toString();
    String cod = codigo.getText().toString();
    String nombres= nombre.getText().toString();
    String apellidos = apellido.getText().toString();
    String email = correo.getText().toString();
    String contra = contraseña.getText().toString();
    String repContra = repcontraseña.getText().toString();

    if(!tipoU.isEmpty() && !cod.isEmpty() && !nombres.isEmpty() && !apellidos.isEmpty() && !email.isEmpty() &&
            email.contains("@ufps.edu.co") && contra.equals(repContra)){
        Toast.makeText(this, "Se esta registrando el usuario", Toast.LENGTH_SHORT).show();
    }else{
        Toast.makeText(this, "Datos incorrectos por favor verifique que las contraseñas coincidan ," +
                " no dejar campos vacios y que el correo sea institucional", Toast.LENGTH_SHORT).show();
    }
    }
}
