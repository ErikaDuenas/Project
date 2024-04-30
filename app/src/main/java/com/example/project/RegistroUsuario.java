package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.project.data.databasesqlite;

public class RegistroUsuario extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegistroUsuario.this, IniciarSesion.class);
        RegistroUsuario.this.finish();
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.actividad_registrousuario);
    }

    public void iniciarSesion(View view) {
        Intent intent = new Intent(RegistroUsuario.this, IniciarSesion.class);
        RegistroUsuario.this.finish();
        startActivity(intent);
    }

    public void guardarDatos(View view) {
        databasesqlite dt =new databasesqlite(this);
        String genero = "";

        EditText nombre =(EditText) findViewById(R.id.name_signup);
        EditText telefono =(EditText) findViewById(R.id.phone_signup);
        EditText ciudad =(EditText) findViewById(R.id.District_signup);
        EditText email =(EditText) findViewById(R.id.email_signup);
        EditText contrasena =(EditText) findViewById(R.id.password_signup);

        RadioButton masculino =(RadioButton) findViewById(R.id.r_b_mal_update);
        RadioButton femenino =(RadioButton) findViewById(R.id.r_b_fmal_update);

        if (masculino.isChecked())
            genero = masculino.getText().toString();
        else if (femenino.isChecked())
            genero = femenino.getText().toString();

        String nombreStr = nombre.getText().toString();
        String telefonoStr = telefono.getText().toString();
        String ciudadStr = ciudad.getText().toString();
        String emailStr = email.getText().toString();
        String contrasenaStr = contrasena.getText().toString();

        String emailValido = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String texto = "[a-zA-Z0-9._-]+";

        if (nombreStr.isEmpty()|| telefonoStr.isEmpty() ||contrasenaStr.isEmpty() || ciudadStr.isEmpty() || emailStr.isEmpty() || genero =="")
        {
            Toast.makeText(this, "Información no valida", Toast.LENGTH_LONG).show();
        }
        else if (emailStr.matches(emailValido) && nombreStr.matches(texto) && ciudadStr.matches(texto))
        {
            String result = dt.insertarDatos(nombreStr, Integer.parseInt(telefonoStr), ciudadStr, emailStr, contrasenaStr, genero);
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();

            nombre.getText().clear();
            telefono.getText().clear();
            ciudad.getText().clear();
            email.getText().clear();
            contrasena.getText().clear();
            masculino.setChecked(false);
            femenino.setChecked(false);
            Intent intent = new Intent(RegistroUsuario.this, IniciarSesion.class);
            RegistroUsuario.this.finish();
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Por favor ingrese los datos correctos", Toast.LENGTH_LONG).show();
        }
    }
}