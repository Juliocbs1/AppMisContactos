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
import com.example.miscontactos.presenter.IIRecyclerViewFragmentPresenter;
import com.example.miscontactos.presenter.RecyclerViewFragmentPresenter;

import java.util.ArrayList;


public class PerfilFragment extends Fragment implements IRecyclerViewFragmentView {
    ArrayList<Contacto> contactos;
    private RecyclerView listaContactos;
    private IIRecyclerViewFragmentPresenter iRecyclerViewFragmentPresenter;

    public PerfilFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_perfil, container, false);
        View v = inflater.inflate(R.layout.fragment_perfil,container,false);
        listaContactos = v.findViewById(R.id.rvContactos);
        iRecyclerViewFragmentPresenter = new RecyclerViewFragmentPresenter(this,getContext());



        return v;
    }



    @Override
    public void generarLinearLayoutVertical() {
        GridLayoutManager llm = new GridLayoutManager(getActivity(),3);

        listaContactos.setLayoutManager(llm);

    }

    @Override
    public ContactoAdaptador crearAdaptador(ArrayList<Contacto> contactos) {
        ContactoAdaptador adaptador = new ContactoAdaptador(contactos,getActivity());

        return adaptador;
    }

    @Override
    public void inicializarDatos(ContactoAdaptador adaptador) {
        listaContactos.setAdapter(adaptador);
    }
}