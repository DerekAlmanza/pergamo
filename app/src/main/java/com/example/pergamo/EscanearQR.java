package com.example.pergamo;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.BarcodeView;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;

import java.util.Arrays;
import java.util.List;

public class EscanearQR extends AppCompatActivity {

    private BarcodeView barcodeView;
    private TextView seccion1, pista;
    private DialogLugar nuevo;
    private Pista dialogPista;

    private BarcodeCallback eventoEscaneo = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() == null) return;
            Toast.makeText(EscanearQR.this, result.getText(), Toast.LENGTH_SHORT).show();
            diferenciarLugar(result);
            diferenciarPista(result);
            //mostrarDialog();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO Este texto debería ser un recurso de cadena
            // TODO El texto también podría ser más descriptivo
            Toast.makeText(this, "Debe otorgar el permiso para usar la cámara",
                    Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        setContentView(R.layout.activity_escanear_qr);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
        barcodeView = findViewById(R.id.vista_escaner_bv);
        nuevo = mostrarDialogLugar();
        dialogPista = mostrarDialogPista();
        inicializar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.resume();
        nuevo.getDialog().hide();
        dialogPista.getDialog().hide();
    }

    @Override
    protected void onPause() {
        barcodeView.pause();
        super.onPause();
    }

    private void inicializar() {
        List<BarcodeFormat> formatoQr = Arrays.asList(BarcodeFormat.QR_CODE);
        barcodeView.setDecoderFactory(new DefaultDecoderFactory(formatoQr));
        barcodeView.decodeContinuous(eventoEscaneo);
    }

    public void regresoHome(View view) {
        Intent regreso = new Intent(this, MainActivity.class);
        startActivity(regreso);
    }

    /**
     * Linkea el botón Ver puntuación a la pantalla Puntuación
     * @param view
     */
    public void verEscanear(View view) {
        Intent verEscaner = new Intent(this, EscanearQR.class);
        startActivity(verEscaner);
    }

    public DialogLugar mostrarDialogLugar() {
        DialogLugar newFragment = new DialogLugar();
        newFragment.show(getSupportFragmentManager(), "MostrarDialog");
        return newFragment;
    }

    public Pista mostrarDialogPista() {
        Pista newFragment = new Pista();
        newFragment.show(getSupportFragmentManager(), "MostrarDialog");
        return newFragment;
    }

    public void diferenciarPista(BarcodeResult result) {
        dialogPista.getDialog().show();
        pista = dialogPista.pista;
        int lapista = 0;
        try{
            lapista = Integer.parseInt(result.getText().toString());
        }catch(NumberFormatException nfe){
            System.out.println("no se pudo convertir a entero " + nfe);
        }
        switch (lapista) {
            case 1:
                pista.setText("Pista número 1");
                break;
            case 2:
                pista.setText("Pista número 2");
                break;
            case 3:
                pista.setText("Pista número 3");
                break;
            case 4:
                pista.setText("Pista número 4");
                break;
            case 5:
                pista.setText("Pista número 5");
                break;
            default:
                pista.setText("Pista no encontrada");
        }
    }


    public void diferenciarLugar(BarcodeResult result) {
        nuevo.getDialog().show();
        seccion1 = nuevo.seccion1;
        int lapista = 0;
        try{
            lapista = Integer.parseInt(result.getText().toString());
        }catch(NumberFormatException nfe){
            System.out.println("no se pudo convertir a entero " + nfe);
        }
        switch (lapista) {
            case 1:
                seccion1.setText("Está funcionando y llegaste a la sección 1");
                break;
            case 2:
                seccion1.setText("Está funcionando y llegaste a la sección 2");
                break;
            case 3:
                seccion1.setText("Está funcionando y llegaste a la sección 3");
                break;
            case 4:
                seccion1.setText("Está funcionando y llegaste a la sección 4");
                break;
            case 5:
                seccion1.setText("Está funcionando y llegaste a la sección 5");
                break;
            default:
                seccion1.setText("QR no identificado correctamente");
        }
    }

}