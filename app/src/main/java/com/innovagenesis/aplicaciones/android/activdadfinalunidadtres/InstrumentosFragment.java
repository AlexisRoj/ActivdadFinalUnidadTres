package com.innovagenesis.aplicaciones.android.activdadfinalunidadtres;


import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class InstrumentosFragment extends Fragment {

    //Se crea una constante estatica que va almacenar la posicion.
    private static final String LLAVE_POSITION = "posicion";

    //Variable privada que va a recibir la position, se inicializa en -1
    private int positionActual = -1;


    public static InstrumentosFragment getInstance(int postion) {
        /** Recibe la posicion del fragment he instancia si no ha sido
         * creado la vista*/

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
        View view = inflater.inflate(R.layout.fragment_instrugmentos, container, false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle argumentos = getArguments();

        if (argumentos != null) {
            actualizarVista(getArguments().getInt(LLAVE_POSITION));
        }else if (positionActual != -1){
            //Evita el crach de la tablet
            actualizarVista(positionActual);
        }

    }

    void actualizarVista(int position) {

        /** Cambia imagenes y titulos en la app*/

        TextView textDescripcion<

        String[] descripciones = getResources().getStringArray(R.array.descripciones);

        //((TextView)getActivity().findViewById(R.id.txt_descripcion)).setText(descripciones[position]);

        textDescripcion = (TextView)getActivity().findViewById(R.id.txt_descripcion);
        textDescripcion.setTypeface(Typeface.createFromAsset(getAssets(), "fuentes/FjallaOne.ttf"));
        textDescripcion.setText(descripciones[position]);


        Drawable instrumento = null;
        Drawable instrumento2 = null;

        switch (position) {

            case 0:
                getActivity().setTitle(getString(R.string.instCuerda));
                instrumento = ContextCompat.getDrawable(getContext(),R.drawable.guitarra);
                instrumento2 = ContextCompat.getDrawable(getContext(),R.drawable.violin);
                break;

            case 1:
                getActivity().setTitle(getString(R.string.instPercusion));
                instrumento = ContextCompat.getDrawable(getContext(),R.drawable.bateria);
                instrumento2 = ContextCompat.getDrawable(getContext(),R.drawable.timbales);
                break;

            case 2:
                getActivity().setTitle(getString(R.string.instViento));
                instrumento = ContextCompat.getDrawable(getContext(),R.drawable.trompeta);
                instrumento2 = ContextCompat.getDrawable(getContext(),R.drawable.saxofon);
                break;

            case 3:
                getActivity().setTitle(getString(R.string.instElectricos));
                instrumento = ContextCompat.getDrawable(getContext(),R.drawable.amplicador);
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
