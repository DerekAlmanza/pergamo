package com.example.pergamo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final byte CODIGO_SOLICITUD_CAMARA = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); // Esconde al label de la aplicación
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.CAMERA}, 3);
        }
    }

    /**
     * Linkea el botón Ver puntuación a la pantalla Puntuación
     * @param view
     */
    public void verPuntacion(View view) {
        Intent verPuntos = new Intent(this, Puntos.class);
        startActivity(verPuntos);

    }

    /**
     * Linkea el botón Ver puntuación a la pantalla Puntuación
     * @param view
     */
    public void verEscanear(View view) {
        Intent verEscaner = new Intent(this, EscanearQR.class);
        startActivity(verEscaner);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODIGO_SOLICITUD_CAMARA) {
            // TODO Estos textos deberían ser recursos de cadena
            // TODO Los textos también podrían ser más descriptivos
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Ahora puede escanear códigos QR",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Necesita otorgar el permiso para usar la " +
                        "cámara para poder usar la aplicación", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
