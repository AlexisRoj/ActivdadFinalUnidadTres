package com.innovagenesis.aplicaciones.android.activdadfinalunidadtres;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class InstrumentosFragment extends Fragment {

    //Se crea una constante estatica que va almacenar la posicion.
    private static final String LLAVE_POSITION = "posicion";

    //Variable privada que va a recibir la position, se inicializa en -1
    private int positionActual = -1;


    public static InstrumentosFragment getInstance(int postion) {
        /** Recibe la posicion del fragment */

        InstrumentosFragment fragment = new InstrumentosFragment();
        Bundle argumentos = new Bundle();
        argumentos.putInt(LLAVE_POSITION, postion);
        fragment.setArguments(argumentos);
        return fragment;
    }


    public InstrumentosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /** Valida si la position inicial es nula*/
        if (savedInstanceState != null) {
            positionActual = savedInstanceState.getInt(LLAVE_POSITION);
        }
        return inflater.inflate(R.layout.fragment_instrugmentos, container, false);




    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle argumentos = getArguments();

        if (argumentos != null) {
            actualizarVista(getArguments().getInt(LLAVE_POSITION));
        }else{
            actualizarVista(positionActual);
        }

    }

    void actualizarVista(int position) {

        String[] descripciones = getResources().getStringArray(R.array.descripciones);

        ((TextView)getActivity().findViewById(R.id.txt_descripcion)).setText(descripciones[position]);

        Drawable instrumento = null;
        Drawable instrumento2 = null;

        switch (position) {

            case 0:
                instrumento = ContextCompat.getDrawable(getContext(),R.drawable.guitarra);
                instrumento2 = ContextCompat.getDrawable(getContext(),R.drawable.electricguitar);
                break;
        }
        ((ImageView)getActivity().findViewById(R.id.imgIzquierda)).setImageDrawable(instrumento);
        ((ImageView)getActivity().findViewById(R.id.imgDerecha)).setImageDrawable(instrumento2);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        /** Este metodo almacena la position actual*/
        outState.putInt(LLAVE_POSITION, positionActual);
    }
}
