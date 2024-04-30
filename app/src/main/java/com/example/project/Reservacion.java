package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;
import com.example.project.data.databasesqlite;
import com.example.project.data.UsuarioInfo;
import java.text.ParseException;

public class Reservacion extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Reservacion.this, ActividadPrincipal.class);
        Reservacion.this.finish();
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.actividad_reserva);
    }

    public void go_to_user(View view) {
        Intent intent = new Intent(Reservacion.this, ActividadPrincipal.class);
        Reservacion.this.finish();
        startActivity(intent);
    }

    public void save_info(View view) throws ParseException {
        databasesqlite b= new databasesqlite(this);

        EditText numeroPersonas = (EditText) findViewById(R.id.num_of_persons);
        EditText fechaReserva = (EditText) findViewById(R.id.date_of_booking);
        int UserId = UsuarioInfo.user.getInt(0);

        String numeroPersonasStr = numeroPersonas.getText().toString();
        String fechaReservaStr = fechaReserva.getText().toString();
        String diaValido = "";
        String mesValido = "";

        ConvertidorDateString dsc = new ConvertidorDateString();

        if(!fechaReservaStr.isEmpty())
        {
            try {
                diaValido = Character.toString(fechaReservaStr.charAt(0)) + Character.toString(fechaReservaStr.charAt(1));
                mesValido = Character.toString(fechaReservaStr.charAt(3)) + Character.toString(fechaReservaStr.charAt(4));
                if((fechaReservaStr.length() < 8) || (((fechaReservaStr.charAt(2) != '-')&&(fechaReservaStr.charAt(5) != '-')) &&
                        ((fechaReservaStr.charAt(1) != '-')&&(fechaReservaStr.charAt(4) != '-')))||
                        (fechaReservaStr.length() > 10) || (Counter(fechaReservaStr) > 2) || (Integer.parseInt(diaValido) > 31 || Integer.parseInt(mesValido) > 12))
                {
                    Toast.makeText(this, "Fecha seleccionada no valida", Toast.LENGTH_LONG).show();
                    if(numeroPersonasStr.isEmpty())
                        Toast.makeText(this, "Por favor complete los requisitos de la Reserva", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            catch(Exception e) {
                Toast.makeText(this, "Fecha seleccionada no  valida", Toast.LENGTH_LONG).show();
                return;

        }

            if(!dsc.comprobarFecha(fechaReservaStr))
            {
                Toast.makeText(this, "Fecha seleccionada no valida", Toast.LENGTH_LONG).show();
                return;
            }
        }

        if(!numeroPersonasStr.isEmpty())
        {
            if(Integer.parseInt(numeroPersonasStr) > 10 || Integer.parseInt(numeroPersonasStr) <= 0)
            {
                Toast.makeText(this, "Numero de personas no valido(Max 10 personas por reserva)", Toast.LENGTH_LONG).show();
                return;
            }
        }
        if (numeroPersonasStr.isEmpty() || fechaReservaStr.isEmpty())
        {
            Toast.makeText(this, "Por favor complete los requisitos de la Reserva", Toast.LENGTH_LONG).show();
            return;
        }
        else{
            String  final_res = b.intertarInformacionReserva(Integer.parseInt(numeroPersonasStr) ,fechaReservaStr ,UserId);
            Toast.makeText( this, final_res, Toast.LENGTH_LONG).show();
            mostrarMensaje("Informacion de Reserva",final_res +
                    "\n"+ "Numero de Personas: "+numeroPersonasStr +
                    "\n" +"Fecha de Reserva: " +fechaReservaStr);
            numeroPersonas.getText().clear();
            fechaReserva.getText().clear();
        }

    }

    public void mostrarMensaje(String titulo, String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.show();
    }

    public int Counter(String s){
        int total = s.length();
        int removed = s.replace("-","").length();
        return total-removed;
    }
}