package com.example.miscontactos.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miscontactos.R;
import com.example.miscontactos.adapter.ContactoAdaptador;
import com.example.miscontactos.model.Contacto;

import java.util.ArrayList;


public class PerfilFragment extends Fragment {
    ArrayList<Contacto> contactos;
    private RecyclerView listaContactos;

    public PerfilFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_perfil, container, false);
        View v = inflater.inflate(R.layout.fragment_perfil,container,false);
        listaContactos = v.findViewById(R.id.rvContactos);

        GridLayoutManager llm = new GridLayoutManager(getActivity(),3);

        listaContactos.setLayoutManager(llm);


        inicializarListaContacto();
        inicializarAdaptador();
        return v;
    }

    public void inicializarAdaptador(){
        ContactoAdaptador adaptador = new ContactoAdaptador(contactos,getActivity());
        listaContactos.setAdapter(adaptador);
    }

    public void inicializarListaContacto(){
        contactos= new ArrayList<Contacto>();
        contactos.add(new Contacto(2,"Anahi Salgado", "5555555555", "anahi@example.com",R.drawable.coservipp,0));
        contactos.add(new Contacto(3,"Jose","255155","jose@example.com",R.drawable.falco,5));
        contactos.add(new Contacto(4,"Maria","65544","maria@example.com",R.drawable.coservipp,6));
        contactos.add(new Contacto(5,"Juan","18831","juan@example.com",R.drawable.falco,7));
        contactos.add(new Contacto(6,"Pedro","645623","pedro@example.com",R.drawable.coservipp,8));
    }

}