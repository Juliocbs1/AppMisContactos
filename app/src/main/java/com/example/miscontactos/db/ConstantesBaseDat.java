package com.example.miscontactos.db;

public class ConstantesBaseDat {

    public static final String DATABASE_NAME = "contactos";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CONTACTS = "contacto";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "nombre";
    public static final String COLUMN_PHONE = "telefono";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_IMAGE = "foto";

    public static final String TABLE_LIKES_CONTACTS = "contacto_likes";
    public static final String COLUMN_LIKES_ID_CONTACTO = "id";
    public static final String COLUMN_ID_CONTACTO = "id_contacto";
    public static final String COLUMN_LIKE = "NUMERO_like";


}
