package com.example.miscontactos.fragment;

import com.example.miscontactos.adapter.ContactoAdaptador;
import com.example.miscontactos.model.Contacto;

import java.util.ArrayList;

public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();
    public ContactoAdaptador crearAdaptador(ArrayList<Contacto> contactos);
    public void inicializarDatos(ContactoAdaptador adaptador);
}
