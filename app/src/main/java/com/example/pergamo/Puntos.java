package com.example.pergamo;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.widget.TextView;
import android.widget.Button;

public class Puntos extends AppCompatActivity {

    private String fecha;
    private static String puntos;
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puntuacion);
        getSupportActionBar().hide();

        inicializar();
        mostrarPuntos(this);
        textView = findViewById(R.id.puntos);
        textView.setText(puntos + "pts");
    }

    /**
     * Linkea el botón "casita" a la pantalla principal.
     * @param view
     */
    public void regresoHome(View view) {
        Intent regreso = new Intent(this, MainActivity.class);
        startActivity(regreso);
    }

    /**
     * Una vez que se clickea el botón ver_pista, el método obtiene la fecha en la que fueron obtenidos los puntos,
     * al igual que inserta los puntos para finalmente mostrar los respectivos puntos.
     */
    public void inicializar(){
        textView = (TextView) findViewById(R.id.puntos);
    }

    /**
     * Obtiene fecha en la que fueron obtenidos los puntos.
     * @return fecha actual.
     */
    private static String obtenerFecha(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = format.format(cal.getTime());
        return fechaActual;
    }

    /**
     * Inserta los puntos en la Base de Datos.
     */
    public static void insertarPuntos(Context context){
        ContentValues contentValues = new ContentValues();
        contentValues.put(PuntosDBContract.PuntosObtenidos.COLUMN_FECHA, obtenerFecha());
        contentValues.put(PuntosDBContract.PuntosObtenidos.COLUMN_PUNTOS_OBTENIDOS, 10);
        context.getContentResolver().insert(PuntosDBContract.PuntosObtenidos.CONTENT_URI, contentValues);
    }

    /**
     * Obtiene los puntos de la Base de Datos y muestra la puntuación total.
     */
    public static String mostrarPuntos(Context context){
        String puntosActuales = "";
        String [] projection = {PuntosDBContract.PuntosObtenidos._ID};
        Cursor cursor = context.getContentResolver().query(PuntosDBContract.PuntosObtenidos.CONTENT_URI, projection, null, null, null);
        int puntosColumnIndex = cursor.getColumnIndex(PuntosDBContract.PuntosObtenidos._ID);
        int puntuacion = 0;
        while(cursor.moveToNext()){
            puntuacion += cursor.getInt(puntosColumnIndex);
        }
        puntosActuales = String.valueOf(puntuacion);
        puntos = puntosActuales;
        cursor.close();
        return puntosActuales;
    }

}
