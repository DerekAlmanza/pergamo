package com.example.pergamo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); // Esconde al label de la aplicación
    }

    /**
     * Linkea el botón Ver puntuación a la pantalla Puntuación
     * @param view
     */
    public void verPuntacion(View view) {
        Intent verPuntos = new Intent(this, Puntos.class);
        startActivity(verPuntos);

    }
}
