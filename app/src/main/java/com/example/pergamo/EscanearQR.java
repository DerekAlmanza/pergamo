package com.example.pergamo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class EscanearQR extends AppCompatActivity {

    TextView rutaQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escanear_qr);
        getSupportActionBar().hide();
        rutaQR = findViewById(R.id.rutaQR);
        inicializar();
    }

    public void inicializar() {
        IntentIntegrator integrator = new IntentIntegrator(EscanearQR.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setCameraId(0);
        integrator.setPrompt("Lector - Finitless");
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null) {
            if(result.getContents() == null)
                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                rutaQR.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}