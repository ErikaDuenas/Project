package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.data.databasesqlite;

public class CancelacionReservas extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CancelacionReservas.this, ActividadPrincipal.class);
        CancelacionReservas.this.finish();
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle estadoInstanciaGuardado) {
        super.onCreate(estadoInstanciaGuardado);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.actividad_cancelacion);
    }

    public void to_user(View vista) {
        Intent intent = new Intent(CancelacionReservas.this, ActividadPrincipal.class);
        CancelacionReservas.this.finish();
        startActivity(intent);
    }


    public void cancel(View vista){
        databasesqlite d = new databasesqlite(this);
        EditText bnumtexto = (EditText) findViewById(R.id.cancel_num);
        String reservaNum = bnumtexto.getText().toString();


        if(reservaNum.isEmpty()){
            Toast.makeText(this,"¡Por favor ingrese el número de Reserva!",Toast.LENGTH_LONG).show();
        }
        else{
            if(d.eliminarReserva(reservaNum)==-1){
                Toast.makeText(this,"¡Número de Reserva no encontrado",Toast.LENGTH_LONG).show();
                bnumtexto.getText().clear();
            }
            else
            {
                Toast.makeText(this,"¡Cancelacion de Reserva realizada con éxito!",Toast.LENGTH_LONG).show();
                bnumtexto.getText().clear();
            }
        }
    }
}