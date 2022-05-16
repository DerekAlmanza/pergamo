package com.example.pergamo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Puntos extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puntuacion);
        getSupportActionBar().hide();
    }

    /**
     * Permite regresar a la Pantalla Principal
     * @param view
     */
    public void regresoHome(View view) {
        Intent regreso = new Intent(this, MainActivity.class);
        startActivity(regreso);
    }
}
