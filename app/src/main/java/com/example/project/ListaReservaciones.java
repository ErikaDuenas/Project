package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project.data.databasesqlite;
import com.example.project.data.UsuarioInfo;

public class ListaReservaciones extends AppCompatActivity{

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ListaReservaciones.this, ActividadPrincipal.class);
        ListaReservaciones.this.finish();
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle estadoInstanciaGuardado) {
        super.onCreate(estadoInstanciaGuardado);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.actividad_lista_reservaciones);

        ListView vista =(ListView) findViewById(R.id.lview);
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        vista.setAdapter(listAdapter);

        databasesqlite dbs = new databasesqlite(this);
        int id = UsuarioInfo.user.getInt(0);
        boolean r = dbs.validarId(id);

        Button siguiente = (Button) findViewById(R.id.next);
        Button anterior = (Button) findViewById(R.id.previous);

        ImageView reserveimage = (ImageView)findViewById(R.id.reservation);
        ImageView errorimage = (ImageView) findViewById(R.id.errorimage);

        if (r == true)
        {
            mostrar(listAdapter);
            siguiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {
                    listAdapter.clear();
                    if(!UsuarioInfo.c_display.isLast()){
                        UsuarioInfo.c_display.moveToNext();
                    }
                    else{
                        UsuarioInfo.c_display.moveToFirst();
                    }
                    mostrar(listAdapter);
                }
            });
            anterior.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {
                    listAdapter.clear();
                    if(!UsuarioInfo.c_display.isFirst()){
                        UsuarioInfo.c_display.moveToPrevious();
                    }
                    else{
                        UsuarioInfo.c_display.moveToLast();
                    }
                    mostrar(listAdapter);
                }
            });

        }
        else
        {
            reserveimage.setVisibility(vista.INVISIBLE);
            errorimage.setVisibility(vista.VISIBLE);
            siguiente.setVisibility(vista.INVISIBLE);
            anterior.setVisibility(vista.INVISIBLE);
            Toast.makeText(this, "No tiene Reservaciones", Toast.LENGTH_SHORT).show();
        }
    }

    public void to_main(View view)
    {
        Intent intent = new Intent(ListaReservaciones.this, ActividadPrincipal.class);
        ListaReservaciones.this.finish();
        startActivity(intent);
    }


    public void mostrar(ArrayAdapter<String> listAdapter)
    {
        String numeroReserva =String.valueOf(UsuarioInfo.c_display.getInt(0));
        listAdapter.add("Numero de Reserva:  "+numeroReserva);
        String numeroPersonas =String.valueOf(UsuarioInfo.c_display.getInt(1));
        listAdapter.add("Numero de Personas: "+numeroPersonas);
        String date = UsuarioInfo.c_display.getString(2);
        listAdapter.add("Fecha de Reserva: " + date);
    }

}