package com.example.pergamo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
    private Button boton;
    private PuntosDBHelper pDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puntuacion);
        getSupportActionBar().hide();

        inicializar();
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
        pDBHelper = new PuntosDBHelper(this);
        textView = (TextView) findViewById(R.id.puntos);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fecha = obtenerFecha();
                insertarPuntos();
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
        SQLiteDatabase db = pDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PuntosDBContract.PuntosObtenidos.COLUMN_FECHA, fecha);
        contentValues.put(PuntosDBContract.PuntosObtenidos.COLUMN_PUNTOS_OBTENIDOS, puntos);
        db.insert(PuntosDBContract.PuntosObtenidos.TABLE_NAME, null, contentValues);
    }

    private void mostrarPuntos(){
        SQLiteDatabase db = pDBHelper.getReadableDatabase();
        String [] projection = {PuntosDBContract.PuntosObtenidos.COLUMN_PUNTOS_OBTENIDOS};
        Cursor cursor = db.query(PuntosDBContract.PuntosObtenidos.TABLE_NAME, projection, null, null, null, null, null);
        int puntosColumnIndex = cursor.getColumnIndex(PuntosDBContract.PuntosObtenidos.COLUMN_PUNTOS_OBTENIDOS);
        while(cursor.moveToNext()){
            String puntosActuales = cursor.getString(puntosColumnIndex);
            textView.append((puntos));
        }
        cursor.close();
    }

}
