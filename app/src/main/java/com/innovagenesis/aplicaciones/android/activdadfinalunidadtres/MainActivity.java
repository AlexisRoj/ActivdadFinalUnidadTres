package com.innovagenesis.aplicaciones.android.activdadfinalunidadtres;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
implements ListaInstrumentos.IListaIntrumentos{

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
                    .add(R.id.contenedor,fragment)
                    .commit();
        }
    }

    @Override
    public void instrumentoSelecionado(int position) {

        InstrumentosFragment fragment = (InstrumentosFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_detalle);
        if (fragment != null){
            fragment.actualizarVista(position);
        }else{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, InstrumentosFragment.getInstance(position))
                    .addToBackStack(null)
                    .commit();
        }

    }


    public void onBottonClick(View view){

        int id = view.getId();

        switch (id){

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
            case R.id.txtfootpageTitle:
                /** Ejecuta un intent hacia una url **/
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.nextu.com"));
                startActivity(intent);
                break;
        }

    }

    private void enviarToast(String mensaje) {

        Toast.makeText(this, mensaje,Toast.LENGTH_SHORT).show();
    }
}
