package com.innovagenesis.aplicaciones.android.activdadfinalunidadtres;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
}
