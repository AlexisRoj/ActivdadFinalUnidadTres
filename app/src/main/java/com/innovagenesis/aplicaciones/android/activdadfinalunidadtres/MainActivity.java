package com.innovagenesis.aplicaciones.android.activdadfinalunidadtres;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.activdadfinalunidadtres.dialog.EjecutarDialog;

import static com.innovagenesis.aplicaciones.android.activdadfinalunidadtres.R.string.actionBarInicial;

public class MainActivity extends AppCompatActivity
        implements ListaInstrumentos.IListaIntrumentos, EjecutarDialog.OnSimpleDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Como la vista por defecto es la tablet el fragmento en modo telefono no es mostrado
         * por eso es necesario implemtar la logica*/

        if (findViewById(R.id.contenedor) != null) {

            if (savedInstanceState != null)
                return;

            /** Instancia el fragment */
            ListaInstrumentos fragment = new ListaInstrumentos();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedor, fragment)
                    .commit();
        }

        this.setTitle(actionBarInicial);
    }

    @Override
    public void instrumentoSelecionado(int position) {

        /** Interface*/

        InstrumentosFragment fragment = (InstrumentosFragment)
                /** Si esta en la table instancia el fragmento detalle*/
                getSupportFragmentManager().findFragmentById(R.id.fragment_detalle);
        if (fragment != null) {

            fragment.actualizarVista(position);
        } else {
            /** Funciona exclusivo para el celular*/
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, InstrumentosFragment.getInstance(position))
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void onBottonClick(View view) {

        int id = view.getId();
        switch (id) {
            /** Selecciona mensaje de acuerdo al id del elemento, se declara en la propiedad
             * onClick de cada objeto*/

            case R.id.imgBtnPlayIzq:
                enviarToast("Reproduciendo sonido del instrumento");
                break;
            case R.id.imgBtnPlayDerch:
                enviarToast("Reproduciendo sonido del instrumento");
                break;
            case R.id.imgBtnFavIzq:
                enviarToast("Instrumento agregado a Favoritos");
                break;
            case R.id.imgBtnFavDerch:
                enviarToast("Instrumento agregado a Favoritos");
                break;
            case R.id.txtfootpageWeb:
                /** Ejecuta un intent hacia una url **/
                FragmentManager fragmentManager = getFragmentManager();
                new EjecutarDialog().show(fragmentManager, "SimpleDialog");
                break;
        }

    }

    private void enviarToast(String mensaje) {
        /** Ejecuta cualquier mensaje necesario*/
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        /** Retorma al titulo inicial cuando preciona back**/
        super.onBackPressed();
        this.setTitle(actionBarInicial);
    }

    @Override
    public void onPossitiveButtonClick() {
        /** Si preciona que si ejecuta el intent*/
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.nextu.com"));
        startActivity(intent);
    }

    @Override
    public void onNegativeButtonClick() {
    }
}
