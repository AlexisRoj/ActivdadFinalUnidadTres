package com.innovagenesis.aplicaciones.android.activdadfinalunidadtres;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Alexis on 21/10/2016.
 * Implementa el listado de los fragments
 */

public class ListaInstrumentos extends ListFragment {

    private IListaIntrumentos implementacion;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {

            this.implementacion = (IListaIntrumentos)context;

        }catch (ClassCastException ex){
            throw new ClassCastException(context.toString() +
                    "Debe implementar ICertificacionesSelecionado");
        }
    }

    public interface IListaIntrumentos{

        /**
         * Interface que comunica los fragmentos
         */

        void instrumentoSelecionado (int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Trae la lista de certificados del array-string*/
        String[] instrumentos = getResources().getStringArray(R.array.instrumentos);
        /** Se utliza un template para cargar el listFragment*/
        setListAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,instrumentos));

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        implementacion.instrumentoSelecionado(position);
        getListView().setItemChecked(position,true);
    }
}
