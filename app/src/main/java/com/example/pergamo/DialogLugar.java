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

import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.BarcodeView;

import org.w3c.dom.Text;

public class DialogLugar extends DialogFragment {

    public TextView seccion1, pista, texto;
    public Button boton;
    private Pista dialogPista;
    private String valorLectura;
    private Button botonPista;

    /**
     * Crea el dialog de lugar.
     * @param savedInstanceState
     * @return diálogo
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.activity_dialog_lugar, null);

        botonPista = view.findViewById(R.id.pista);
        botonPista.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        diferenciarPista();
                    }
                }
        );

        builder.setView(view);
        seccion1 = view.findViewById(R.id.seccion1);
        return builder.create();
    }

    /**
     * Setter de dialogPista
     * @param dialogPista
     */
    public void setDialogPista(Pista dialogPista) {
        this.dialogPista = dialogPista;
    }

    /**
     * Setter de lectura
     * @param lectura
     */
    public void setValorLectura(String lectura) {
        valorLectura = lectura;
    }

    /**
     * A partir del result, diferencia qué código QR se está leyendo y modifica el TextView que
     * corresponde a dar la pista del lugar
     */
    public void diferenciarPista() {
        dialogPista.getDialog().show();
        this.getDialog().hide();
        pista = dialogPista.pista;
        texto = dialogPista.texto;
        boton = dialogPista.boton;
        int lapista = 0;
        try{
            lapista = Integer.parseInt(valorLectura);
        }catch(NumberFormatException nfe){
            System.out.println("no se pudo convertir a entero " + nfe);
            return;
        }
        switch (lapista) {
            case 1:
                pista.setText("Camina 7 pasos hacia adelante a partir de la hemeroteca, después gira a la derecha " +
                                "y después camina 4 pasos para encontrar la siguiente pista.");
                break;
            case 2:
                pista.setText("Camina 10 pasos hacia adelante a partir de la Sala Audiovisual, después gira a la izquierda " +
                             "y camina 3 pasos para encontrar la siguiente pista.");
                break;
            case 3:
                pista.setText("Camina 15 pasos hacia adelante a partir de la Sala de Cómputo, después gira a la izquierda " +
                              "y camina 10 pasos para encontrar la siguiente pista.");
                break;
            case 4:
                pista.setText("A partir de la Sala General gira a la derecha y camina 7 pasos, después gira de nuevo a la derecha " +
                              "y camina 4 pasos para encontrar la siguiente pista.");
                break;
            case 5:
                texto.setText("¡Felicidades!");
                pista.setText("Has encontrado todas las pistas y por lo tanto has terminado la búsqueda");
                boton.setText("Iniciar nueva búsqueda");

                break;
            default:
                pista.setText("El código QR escaneado es inválido o no existe una pista.");
        }
    }
}