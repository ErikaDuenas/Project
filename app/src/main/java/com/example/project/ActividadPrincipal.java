package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import com.example.project.data.UsuarioInfo;
import android.widget.AdapterView;

public class ActividadPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.actividad_inicio);

        ImageButton Ib = findViewById(R.id.profile);

        if (UsuarioInfo.user.getString(6).equals("Masculino")) {
            Ib.setImageResource(R.drawable.male);
        } else {
            Ib.setImageResource(R.drawable.female);
        }

        // Define el adaptador personalizado para el spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opciones_menu, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Encuentra el spinner en tu diseño
        Spinner spinner = findViewById(R.id.spinner_menu);

        // Establece el adaptador personalizado para el spinner
        spinner.setAdapter(adapter);

        // Manejar la selección del spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Reservar")) {
                    reservacion(view);
                }

                if (selectedItem.equals("Cancelar Reservacion")) {
                    cancelacionReserva(view);

                }

                if (selectedItem.equals("Mis Reservaciones")) {
                    mostrar_mesas(view);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Este método se llama cuando no se ha seleccionado ningún elemento del spinner
            }
        });
    }

    //Actividad para cerrar sesion de usuario
    public void log_out(View vista) {
        UsuarioInfo.user.moveToFirst();
        Intent intent = new Intent(ActividadPrincipal.this, IniciarSesion.class);
        ActividadPrincipal.this.finish();
        startActivity(intent);
    }

    public void mostrar_mesas(View vista) {
        Intent intent = new Intent(ActividadPrincipal.this, ListaReservaciones.class);
        ActividadPrincipal.this.finish();
        startActivity(intent);
    }

    //Actividad para mostrar el perfil del usuario
    public void perfil(View vista) {
        Intent intent = new Intent(ActividadPrincipal.this, Perfil.class);
        ActividadPrincipal.this.finish();
        startActivity(intent);
    }

    //Actividad para realizar reservacion
    public void reservacion(View vista) {
        Intent intent = new Intent(ActividadPrincipal.this, Reservacion.class);
        ActividadPrincipal.this.finish();
        startActivity(intent);
    }

    //Actividad para realizar la cancelacion de las reservas realizadas con anterioridad por el usuario
    public void cancelacionReserva(View vista) {
        Intent intent = new Intent(ActividadPrincipal.this, CancelacionReservas.class);
        ActividadPrincipal.this.finish();
        startActivity(intent);
    }
}
