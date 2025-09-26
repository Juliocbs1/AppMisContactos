package com.example.miscontactos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.miscontactos.model.Contacto;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    //Verifica si se crea o no la base de datos
    public BaseDatos(Context context) {
        super(context, ConstantesBaseDat.DATABASE_NAME, null, ConstantesBaseDat.DATABASE_VERSION);
        this.context    = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String queryCreate = "CREATE TABLE " + ConstantesBaseDat.TABLE_CONTACTS + "("
                    + ConstantesBaseDat.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ConstantesBaseDat.COLUMN_NAME + " TEXT, " +
                    ConstantesBaseDat.COLUMN_PHONE + " TEXT, " +
                    ConstantesBaseDat.COLUMN_EMAIL + " TEXT, " +
                    ConstantesBaseDat.COLUMN_IMAGE + " INTEGER" + ")";
            db.execSQL(queryCreate);
            String queryCreateLikesContactos = "CREATE TABLE " + ConstantesBaseDat.TABLE_LIKES_CONTACTS + "("
                    + ConstantesBaseDat.COLUMN_LIKES_ID_CONTACTO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ConstantesBaseDat.COLUMN_ID_CONTACTO + " INTEGER, " +
                    ConstantesBaseDat.COLUMN_LIKE + " INTEGER, " +
                    "FOREIGN KEY (" + ConstantesBaseDat.COLUMN_ID_CONTACTO + ") " +
                    "REFERENCES " + ConstantesBaseDat.TABLE_CONTACTS + "(" + ConstantesBaseDat.COLUMN_ID + ")" +
                    ")";

            db.execSQL(queryCreateLikesContactos);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDat.TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDat.TABLE_LIKES_CONTACTS);
        onCreate(db);

    }

    public ArrayList<Contacto> obtenerContactos(){
        ArrayList<Contacto> contactos = new ArrayList<Contacto>();
        String query = "SELECT * FROM " + ConstantesBaseDat.TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query, null);
//Llenar lista
        while (registros.moveToNext()){
            Contacto contactoActual = new Contacto();
            contactoActual.setId(registros.getInt(0));
            contactoActual.setNombre(registros.getString(1));
            contactoActual.setTelefono(registros.getString(2));
            contactoActual.setEmail(registros.getString(3));
            contactoActual.setFoto(registros.getInt(4));
            String queryLikes = "SELECT COUNT(" + ConstantesBaseDat.COLUMN_LIKE + ") FROM " + ConstantesBaseDat.TABLE_LIKES_CONTACTS +
                    " WHERE " + ConstantesBaseDat.COLUMN_ID_CONTACTO + "=" + contactoActual.getId();
            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()) {
                contactoActual.setLike(registrosLikes.getInt(0));
            } else {
                contactoActual.setLike(0);
            }

            contactos.add(contactoActual);


    }
        //Cerra conexion
        db.close();
        return contactos;
    }
    public void insertarContacto(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
       db.insert(ConstantesBaseDat.TABLE_CONTACTS, null, contentValues);
       db.close();
    }

    public void insertarLikeContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDat.TABLE_LIKES_CONTACTS, null, contentValues);
        db.close();

    }

    public int obtenerLikesContacto(Contacto contacto) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(" + ConstantesBaseDat.COLUMN_LIKE + ") FROM " + ConstantesBaseDat.TABLE_LIKES_CONTACTS +
                " WHERE " + ConstantesBaseDat.COLUMN_ID_CONTACTO + "=" + contacto.getId();
        Cursor registros = db.rawQuery(query, null);
        if (registros.moveToNext()) {
            return registros.getInt(0);
        }
        db.close();
        return 0;

    }

}
