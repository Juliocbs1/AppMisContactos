package com.example.miscontactos.presenter;

import android.content.Context;

import com.example.miscontactos.db.ConstructorContactos;
import com.example.miscontactos.fragment.IRecyclerViewFragmentView;
import com.example.miscontactos.model.Contacto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RecyclerViewFragmentPresenter implements IIRecyclerViewFragmentPresenter  {
   private IRecyclerViewFragmentView iRecyclerViewFragmentView;

   private Context context;
   private ConstructorContactos constructorContactos;
   private ArrayList<Contacto> contactos;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView view, Context context){
        this.iRecyclerViewFragmentView = view;
        this.context = context;
        obtenerContactosBD();

   }
//Separamos la fuente de los datos con la presentacion de datos
    @Override
    public void obtenerContactosBD() {
        constructorContactos = new ConstructorContactos(context);
        contactos = constructorContactos.obtenerContactos();

        mostraContactosRV();
    }

    @Override
    public void mostraContactosRV() {
        iRecyclerViewFragmentView.inicializarDatos(iRecyclerViewFragmentView.crearAdaptador(contactos));
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }
}
