package com.example.project;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;

public class ConvertidorDateString {
    public boolean comprobarFecha(String StringDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = sdf.parse(StringDate);
        Date fechaActual = Calendar.getInstance().getTime();
        String fechaActualStr = sdf.format(fechaActual);
        fechaActual = sdf.parse(fechaActualStr);
        if(fecha.after(fechaActual)) {
            return true;
        }
        else{
            return false;
        }
    }
}
