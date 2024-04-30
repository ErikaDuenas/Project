package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;
import com.example.project.data.databasesqlite;
import com.example.project.data.UsuarioInfo;


public class IniciarSesion extends AppCompatActivity {

    public void onBackPressed() {
        Intent intent = new Intent(IniciarSesion.this, ComenzarAplicacion.class);
        IniciarSesion.this.finish();
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.actividad_inicio_sesion);
    }

    public void registrarse(View view) {
        Intent intent = new Intent(IniciarSesion.this, RegistroUsuario.class);
        IniciarSesion.this.finish();
        startActivity(intent);
    }

    public void iniciarSesion(View view ) {

        databasesqlite dt = new databasesqlite(this);
        EditText email_user = (EditText) findViewById(R.id.login_email);
        EditText password_user = (EditText) findViewById(R.id.password_login);
        boolean r = dt.validarDatos(email_user,password_user);
        if (r == false)
        {
            Toast.makeText(this, "¡Datos no válidos! Intente otra vez", Toast.LENGTH_SHORT).show();
            email_user.getText().clear();
            password_user.getText().clear();
        }
        else
        {
            Toast.makeText(this, "¡Inicia sesión exitosamente! Bienvenid@ " + UsuarioInfo.user.getString(1), Toast.LENGTH_SHORT).show();
            email_user.getText().clear();
            password_user.getText().clear();
            Intent intent = new Intent(IniciarSesion.this, ActividadPrincipal.class);
            IniciarSesion.this.finish();
            startActivity(intent);
        }
    }
}