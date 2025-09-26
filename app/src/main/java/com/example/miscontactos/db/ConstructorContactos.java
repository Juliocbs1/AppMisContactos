package com.example.miscontactos.db;

import android.content.ContentValues;
import android.content.Context;

import com.example.miscontactos.R;
import com.example.miscontactos.model.Contacto;

import java.util.ArrayList;

public class ConstructorContactos {
    private Context context;

    public ConstructorContactos(Context context) {
        this.context = context;
    }

    /*
    public ArrayList<Contacto> obtenerContactos() {
        ArrayList<Contacto> contactos = new ArrayList<Contacto>();
        contactos.add(new Contacto(2, "Anahi Salgado", "5555555555", "anahi@example.com", R.drawable.coservipp, 0));
        contactos.add(new Contacto(3, "Jose", "255155", "jose@example.com", R.drawable.falco, 5));
        contactos.add(new Contacto(4, "Maria", "65544", "maria@example.com", R.drawable.coservipp, 6));
        contactos.add(new Contacto(5, "Juan", "18831", "juan@example.com", R.drawable.falco, 7));
        contactos.add(new Contacto(6, "Pedro", "645623", "pedro@example.com", R.drawable.coservipp, 8));
        return contactos;
    }
    */
    public ArrayList<Contacto> obtenerContactos() {
        BaseDatos baseDatos = new BaseDatos(context);
        insertarContactos(baseDatos);
        return baseDatos.obtenerContactos();
    }


    public void insertarContactos(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDat.COLUMN_NAME, "Anahi Salgado");
        contentValues.put(ConstantesBaseDat.COLUMN_PHONE, "5555555555");
        contentValues.put(ConstantesBaseDat.COLUMN_EMAIL, "anahi@example.com");
        contentValues.put(ConstantesBaseDat.COLUMN_IMAGE, R.drawable.coservipp);
        db.insertarContacto(contentValues);

        contentValues.put(ConstantesBaseDat.COLUMN_NAME, "Jose");
        contentValues.put(ConstantesBaseDat.COLUMN_PHONE, "255155");
        contentValues.put(ConstantesBaseDat.COLUMN_EMAIL, "jose@example.com");
        contentValues.put(ConstantesBaseDat.COLUMN_IMAGE, R.drawable.falco);

        db.insertarContacto(contentValues);

        contentValues.put(ConstantesBaseDat.COLUMN_NAME, "Maria");
        contentValues.put(ConstantesBaseDat.COLUMN_PHONE, "65544");
        contentValues.put(ConstantesBaseDat.COLUMN_EMAIL, "maria@example.com");
        contentValues.put(ConstantesBaseDat.COLUMN_IMAGE, R.drawable.coservipp);
        db.insertarContacto(contentValues);



    }


}
