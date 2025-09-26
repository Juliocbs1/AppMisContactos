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
import com.example.miscontactos.model.Contacto;
import com.example.miscontactos.presenter.IIRecyclerViewFragmentPresenter;
import com.example.miscontactos.presenter.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView {

    ArrayList<Contacto> contactos;
    private RecyclerView listaContactos;
    private IIRecyclerViewFragmentPresenter iRecyclerViewFragmentPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        //fragment asocaido al recycler view
        View v = inflater.inflate(R.layout.fragment_recyclervirew,container,false);
        listaContactos = v.findViewById(R.id.rvContactos);
        iRecyclerViewFragmentPresenter = new RecyclerViewFragmentPresenter(this,getContext());


        return v;
    }


/*
    public void inicializarListaContacto(){
        contactos= new ArrayList<Contacto>();
        contactos.add(new Contacto(2,"Anahi Salgado", "5555555555", "anahi@example.com",R.drawable.coservipp,0));
        contactos.add(new Contacto(3,"Jose","255155","jose@example.com",R.drawable.falco,5));
        contactos.add(new Contacto(4,"Maria","65544","maria@example.com",R.drawable.coservipp,6));
        contactos.add(new Contacto(5,"Juan","18831","juan@example.com",R.drawable.falco,7));
        contactos.add(new Contacto(6,"Pedro","645623","pedro@example.com",R.drawable.coservipp,8));
    }

 */

    @Override
    public void generarLinearLayoutVertical() {
        GridLayoutManager llm = new GridLayoutManager(getActivity(),2);

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
