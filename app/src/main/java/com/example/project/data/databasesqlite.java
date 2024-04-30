package com.example.project.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class databasesqlite extends SQLiteOpenHelper {

    public static final String databaseName ="app.db";

    public databasesqlite(Context con)
    {
        super(con,databaseName,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase Database) {
        Database.execSQL("create table booking (booking_number INTEGER primary key AUTOINCREMENT,NumberOfPersons INTEGER,DateOfBooking TEXT,UserId INTEGER)");
        Database.execSQL("create table user (Id INTEGER primary key AUTOINCREMENT ,Name TEXT ,Phone INTEGER, District TEXT ,Email TEXT,Password TEXT ,Gender TEXT )");


    }

    @Override
    public void onUpgrade(SQLiteDatabase Database, int i, int i1) {
        Database.execSQL("DROP TABLE IF EXISTS booking");
        Database.execSQL("DROP TABLE IF EXISTS user");

        onCreate(Database);

    }

    public String insertarDatos(String name, int phone , String dis , String email, String pass, String gender)
    {
        SQLiteDatabase sdata =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name",name);
        values.put("Phone",phone);
        values.put("District",dis);
        values.put("Email",email);
        values.put("Password",pass);
        values.put("Gender",gender);
        long r = sdata.insert("user",null,values);
        if (r == -1)
            return "Datos no validos";
        else
            return "Datos Guardados";
    }

    public String intertarInformacionReserva(int numberofpersons, String dateofbooking, int userid)
    {
        SQLiteDatabase bdata =this.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put("NumberOfPersons",numberofpersons);
        content.put("DateOfBooking",dateofbooking);
        content.put("UserId",userid);

        long c = bdata.insert("booking",null,content);
        if(c ==-1)
        {
            return "Datos no validos";
        }
        else {
            Reservacion_info.numeroReservacion = c;
            Reservacion_info.fechaReserva = dateofbooking;
            Reservacion_info.numeroPersonas = numberofpersons;
            return "Numero de Reserva: " + c;
        }
    }


    public long eliminarReserva(String bookingnum) {
        SQLiteDatabase mydata = this.getWritableDatabase();
        if(!(validarNumeroReserva(bookingnum)))
            return -1;
        mydata.delete("booking", "booking_number = ?",new String[] {bookingnum});
        return 1;
    }

    public boolean validarNumeroReserva(String numeroReserva){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cu = database.rawQuery("SELECT * FROM booking WHERE booking_number = ?", new String[]{numeroReserva});
        if(cu.moveToFirst()) {
            UsuarioInfo.reservaInfoCursor = cu;
            return true;
        }
        return false;
    }


    public String actualizarDatos(String nombre, int telefono , String ciudad , String email, String contrasena, String genero) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",nombre);
        contentValues.put("Phone",telefono);
        contentValues.put("District",ciudad);
        contentValues.put("Email",email);
        contentValues.put("Password",contrasena);
        contentValues.put("Gender",genero);
        int flag = db.update("user", contentValues, "Id = ?",new String[] { String.valueOf(UsuarioInfo.user.getInt(0)) });
        if(flag == 1)
            return "Información Actualizada";
        else
            return "La información no fue actualizada";
    }

    public Boolean validarDatos(EditText email, EditText pass)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cu = database.rawQuery("SELECT * FROM user WHERE Email = ? and Password = ?", new String[]{email.getText().toString(),pass.getText().toString()});
        if(cu.moveToFirst()) {
            UsuarioInfo.user = cu;
            return true;
        }
        return false;
    }

    public Boolean validarId(int id)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM booking WHERE UserId = ?",new String[]{String.valueOf(UsuarioInfo.user.getInt(0))});
        if(c.moveToFirst())
        {
            UsuarioInfo.c_display = c;
            return true;
        }
        return false;
    }


    public void seleccionarDatos(int id)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cu = database.rawQuery("SELECT * FROM user WHERE Id = ?", new String[]{String.valueOf(id)});
        if(cu.moveToFirst()) {
            UsuarioInfo.user = cu;
        }
    }

    public Integer eliminarDatos(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("user", "Id = ?",new String[] {String.valueOf(id)});
    }

}

