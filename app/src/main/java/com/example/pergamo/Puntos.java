package com.example.pergamo;

import android.content.ContentValues;
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
    private String puntos;
    private TextView textView;
    //
    private Button button;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puntuacion);
        getSupportActionBar().hide();

        inicializar();
        mostrarPuntos();
    }

    /**
     * Permite regresar a la Pantalla Principal
     * @param view
     */
    public void regresoHome(View view) {
        Intent regreso = new Intent(this, MainActivity.class);
        startActivity(regreso);
    }

    public void inicializar(){
        //LayoutInflater inflater = getLayoutInflater();

        //View view = inflater.inflate(R.layout.activity_pista, null);

        //Button button = view.findViewById(R.id.seguirBuscando); // este botón debería de funcionar pero no funciona,
                                                                // se encuentra en el layout activity_pista

        textView = (TextView) findViewById(R.id.puntos);

        button = (Button) findViewById(R.id.insercion); //este botón solo es de prueba, se encuentra en el layout puntuacion

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fecha = obtenerFecha();
                puntos = "10";
                insertarPuntos();
                mostrarPuntos();
            }
        });
    }

    private String obtenerFecha(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = format.format(cal.getTime());
        return fechaActual;
    }


    private void insertarPuntos(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(PuntosDBContract.PuntosObtenidos.COLUMN_FECHA, fecha);
        contentValues.put(PuntosDBContract.PuntosObtenidos.COLUMN_PUNTOS_OBTENIDOS, puntos);
        Uri newUri = getContentResolver().insert(PuntosDBContract.PuntosObtenidos.CONTENT_URI, contentValues);
    }

    private void mostrarPuntos(){
        String puntosActuales = "";
        String [] projection = {PuntosDBContract.PuntosObtenidos._ID};
        Cursor cursor = getContentResolver().query(PuntosDBContract.PuntosObtenidos.CONTENT_URI, projection, null, null, null);
        int puntosColumnIndex = cursor.getColumnIndex(PuntosDBContract.PuntosObtenidos._ID);
        while(cursor.moveToNext()){
            int multiplicacion = puntosColumnIndex*10;
            puntosActuales = cursor.getString(puntosColumnIndex);//puntos column index
        }
        textView = findViewById(R.id.puntos);
        textView.setText(puntosActuales+"0 pts");
        cursor.close();
    }

}
