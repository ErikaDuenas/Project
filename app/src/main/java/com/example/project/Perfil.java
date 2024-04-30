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
import com.example.project.data.UsuarioInfo;

public class Perfil extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Perfil.this, ActividadPrincipal.class);
        Perfil.this.finish();
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.actividad_perfil_usuario);

        EditText user_id = (EditText) findViewById(R.id.id_update);
        EditText na =(EditText) findViewById(R.id.name_update);
        EditText ph =(EditText) findViewById(R.id.phone_update);
        EditText dis =(EditText) findViewById(R.id.district_update);
        EditText em =(EditText) findViewById(R.id.email_update);
        EditText pass =(EditText) findViewById(R.id.password_update);
        RadioButton mal =(RadioButton) findViewById(R.id.r_b_mal_update);
        RadioButton fmal =(RadioButton) findViewById(R.id.r_b_fmal_update);

        user_id.setText(String.valueOf(UsuarioInfo.user.getInt(0)));
        na.setText(UsuarioInfo.user.getString(1));
        ph.setText(String.valueOf(UsuarioInfo.user.getInt(2)));
        dis.setText(UsuarioInfo.user.getString(3));
        em.setText(UsuarioInfo.user.getString(4));
        pass.setText(UsuarioInfo.user.getString(5));


        if(UsuarioInfo.user.getString(6).equals("Masculino")) {
            mal.setChecked(true);
        }
        else
            fmal.setChecked(true);

    }


    public void to_main(View vista) {
        Intent intent = new Intent(Perfil.this, ActividadPrincipal.class);
        Perfil.this.finish();
        startActivity(intent);
    }

    public void actualizar(View view) {

        databasesqlite dt =new databasesqlite(this);
        String genero = "";

        EditText na =(EditText) findViewById(R.id.name_update);
        EditText ph =(EditText) findViewById(R.id.phone_update);
        EditText dis =(EditText) findViewById(R.id.district_update);
        EditText em =(EditText) findViewById(R.id.email_update);
        EditText pass =(EditText) findViewById(R.id.password_update);
        RadioButton masculino =(RadioButton) findViewById(R.id.r_b_mal_update);
        RadioButton femenino =(RadioButton) findViewById(R.id.r_b_fmal_update);

        String nombre = na.getText().toString();
        String telefono = ph.getText().toString();
        String ciudad = dis.getText().toString();
        String Email = em.getText().toString();
        String contrasena = pass.getText().toString();

        String Valid_email = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String text = "[a-zA-Z0-9._-]+";

        if (masculino.isChecked())
            genero = masculino.getText().toString();
        else
            genero = femenino.getText().toString();


        if (nombre.isEmpty()|| telefono.isEmpty() ||contrasena.isEmpty() || ciudad.isEmpty() || Email.isEmpty() || genero == "")
        {
            Toast.makeText(this, "Ingrese los datos solicitados", Toast.LENGTH_LONG).show();
        }

        else if (Email.matches(Valid_email) && nombre.matches(text) && ciudad.matches(text))
        {
            String result = dt.actualizarDatos(nombre, Integer.parseInt(telefono), ciudad, Email, contrasena, genero);
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            dt.seleccionarDatos(UsuarioInfo.user.getInt(0));
        }
        else
        {
            Toast.makeText(this, "Por favor ingrese los datos correctos", Toast.LENGTH_LONG).show();
        }
    }
    public void delete(View view)
    {
        databasesqlite dt = new databasesqlite(this);
        int r = dt.eliminarDatos(UsuarioInfo.user.getInt(0));
        if (r >0) {
            Toast.makeText(this, "Cuenta Eliminada", Toast.LENGTH_LONG).show();
            UsuarioInfo.user.moveToFirst();
            Intent intent = new Intent(Perfil.this, EliminacionUsuario.class);
            Perfil.this.finish();
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "No se pudo eliminar la cuenta", Toast.LENGTH_LONG).show();

        }
    }

}