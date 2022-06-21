package com.example.pergamo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;

public class Pista extends DialogFragment{

    public TextView pista, texto;
    public Button boton;

    /**
     * Crea el dialog de pista.
     * @param savedInstanceState
     * @return diálogo
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.activity_pista, null);

        builder.setView(view);

        pista = view.findViewById(R.id.verPista);
        texto = view.findViewById(R.id.texto);
        boton = view.findViewById(R.id.seguirBuscando);
        boton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        verEscanear(view);
                    }
                }
        );
        setCancelable(false);
        return builder.create();
    }

    /**
     * Linkea el botón Ver Escaner a la pantalla EscanearQR
     * @param view
     */
    public void verEscanear(View view) {
        getDialog().hide();
        EscanearQR actividadEscanear = (EscanearQR) getActivity();
        actividadEscanear.inciarEscaneo();
    }
}