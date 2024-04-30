package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class EliminacionUsuario extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EliminacionUsuario.this, IniciarSesion.class);
        EliminacionUsuario.this.finish();
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.actividad_eliminar_usuario);
    }

    public void exit(View view)
    {
        System.exit(0);
    }
}