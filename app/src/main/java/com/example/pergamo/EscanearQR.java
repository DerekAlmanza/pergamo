package com.example.pergamo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.BarcodeView;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;

import java.util.Arrays;
import java.util.List;

public class EscanearQR extends AppCompatActivity {

    private BarcodeView barcodeView;

    private BarcodeCallback eventoEscaneo = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() == null) return;
            Toast.makeText(EscanearQR.this, result.getText(), Toast.LENGTH_SHORT).show();
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
        inicializar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.resume();
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


}