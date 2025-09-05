package com.example.miscontactos.db;

import android.content.Context;

import com.example.miscontactos.R;
import com.example.miscontactos.model.Contacto;

import java.util.ArrayList;

public class ConstructorContactos {
    private Context context;

    public ConstructorContactos(Context context) {
        this.context = context;
    }

    public ArrayList<Contacto> obtenerContactos() {
        ArrayList<Contacto> contactos = new ArrayList<Contacto>();
        contactos.add(new Contacto(2, "Anahi Salgado", "5555555555", "anahi@example.com", R.drawable.coservipp, 0));
        contactos.add(new Contacto(3, "Jose", "255155", "jose@example.com", R.drawable.falco, 5));
        contactos.add(new Contacto(4, "Maria", "65544", "maria@example.com", R.drawable.coservipp, 6));
        contactos.add(new Contacto(5, "Juan", "18831", "juan@example.com", R.drawable.falco, 7));
        contactos.add(new Contacto(6, "Pedro", "645623", "pedro@example.com", R.drawable.coservipp, 8));
        return contactos;
    }

}
