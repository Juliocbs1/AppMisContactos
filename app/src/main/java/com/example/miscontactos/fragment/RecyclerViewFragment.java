package com.example.miscontactos.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miscontactos.R;
import com.example.miscontactos.adapter.ContactoAdaptador;
import com.example.miscontactos.pojo.Contacto;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment {

    ArrayList<Contacto> contactos;
    private RecyclerView listaContactos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        //fragment asocaido al recycler view
        View v = inflater.inflate(R.layout.fragment_recyclervirew,container,false);
        listaContactos = v.findViewById(R.id.rvContactos);

        GridLayoutManager llm = new GridLayoutManager(getActivity(),2);

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
        contactos.add(new Contacto("Anahi Salgado", "5555555555", "anahi@example.com",R.drawable.coservipp));
        contactos.add(new Contacto("Jose","255155","jose@example.com",R.drawable.falco));
        contactos.add(new Contacto("Maria","65544","maria@example.com",R.drawable.coservipp));
        contactos.add(new Contacto("Juan","18831","juan@example.com",R.drawable.falco));
        contactos.add(new Contacto("Pedro","645623","pedro@example.com",R.drawable.coservipp));
    }
}
