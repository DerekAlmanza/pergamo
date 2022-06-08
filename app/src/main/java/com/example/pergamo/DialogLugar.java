package com.example.pergamo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.journeyapps.barcodescanner.BarcodeResult;

import org.w3c.dom.Text;

public class DialogLugar extends DialogFragment {

    public TextView seccion1, pista;
    private Pista dialogPista;
    private String valorLectura;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.activity_dialog_lugar, null);

        Button verPista = view.findViewById(R.id.pista);
        verPista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diferenciarPista();
            }
        });

        builder.setView(view);
        seccion1 = view.findViewById(R.id.seccion1);
        return builder.create();
    }

    public void setDialogPista(Pista dialogPista) {
        this.dialogPista = dialogPista;
    }

    public void setValorLectura(String lectura) {
        valorLectura = lectura;
    }

    public void diferenciarPista() {
        dialogPista.getDialog().show();
        this.getDialog().hide();
        pista = dialogPista.pista;
        int lapista = 0;
        try{
            lapista = Integer.parseInt(valorLectura);
        }catch(NumberFormatException nfe){
            System.out.println("no se pudo convertir a entero " + nfe);
        }
        switch (lapista) {
            case 1:
                pista.setText("Camina 7 pasos hacia adelante a partir de la hemeroteca, después gira a la derecha " +
                                "y después camina 4 pasos para encontrar la siguiente pista.");
                break;
            case 2:
                pista.setText("Camina 10 pasos hacia adelante a partir de la sección, después gira a la izquierda " +
                             "y camina 3 pasos para encontrar la siguiente pista.");
                break;
            case 3:
                pista.setText("Camina 15 pasos hacia adelante a partir de la sección, después gira a la izquiersa " +
                              "y camina 10 pasos para encontrar la siguiente pista.");
                break;
            case 4:
                pista.setText("A partir de la sección gira a la derecha y camina 7 pasos, después gira de nuevo a la derecha " +
                              "y camina 4 pasos para encontrar la siguiente pista.");
                break;
            case 5:
                pista.setText("Camina 15 pasos hacia adelante a partir de la sección, después gira a la derecha " +
                              "y camina 20 pasos para encontrar la siguiente pista.");
                break;
            default:
                pista.setText("El código QR escaneado es inválido o no existe una pista.");
        }
    }
}