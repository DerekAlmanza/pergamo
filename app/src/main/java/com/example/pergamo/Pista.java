package com.example.pergamo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Pista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pista);
    }

    /**
     * Linkea el botón Ver puntuación a la pantalla Puntuación
     * @param view
     */
    public void verEscanear(View view) {
        Intent verEscaner = new Intent(this, EscanearQR.class);
        startActivity(verEscaner);

    }
}