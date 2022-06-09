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
    private TextView seccion1;
    private DialogLugar nuevo;
    private Pista dialogPista;


    private BarcodeCallback eventoEscaneo = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() == null) return;
            //Toast.makeText(EscanearQR.this, result.getText(), Toast.LENGTH_SHORT).show();
            diferenciarLugar(result);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO Este texto debería ser un recurso de cadena
            // TODO El texto también podría ser más descriptivo
            //Toast.makeText(this, "Debe otorgar el permiso para usar la cámara",
                    //Toast.LENGTH_SHORT).show();
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
        nuevo.setDialogPista(dialogPista);
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

    public void diferenciarLugar(BarcodeResult result) {
        nuevo.setValorLectura(result.getText());
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
                seccion1.setText("Has llegado a la Hemeroteca.\n" +
                                 "En esta sección puede encontrar colecciones de revistas, diarios y publicaciones periódicas.");
                break;
            case 2:
                seccion1.setText("Has llegado a la Sala Audiovisual. \n" +
                                "En esta sección puedes encontrar colecciones de películas.");
                break;
            case 3:
                seccion1.setText("Has llegado a la Sala de Cómputo. \n" +
                                "En esta sección puedes hacer uso de las computadoras disponibles.");
                break;
            case 4:
                seccion1.setText("Has llegado a la Sala General. \n" +
                                "En esta sección puedes encontrar textos de multiples temas, entre ellos Filosofía, Religión, Ciencias Naturales.");
                break;
            case 5:
                seccion1.setText("Has llegado a la sección de Publicaciones Locales. \n"+
                                "En esta sección encontrarás las tesis de alumnos egresados que puedes consultar en cualquier momento.");
                break;
            default:
                seccion1.setText("QR no identificado correctamente");
        }
    }
}