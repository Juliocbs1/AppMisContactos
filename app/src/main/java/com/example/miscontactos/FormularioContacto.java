package com.example.miscontactos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.FileOutputStream;

public class FormularioContacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_contacto);
        Toolbar toolbar = findViewById(R.id.toolbarnorm);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Formulario Contactos");


        EditText campoNombre = findViewById(R.id.main_campo_nombre);
        EditText campoCorreo = findViewById(R.id.main_campo_correo);
        EditText campoMensaje = findViewById(R.id.main_campo_mensaje);
        Button btnEnviar = findViewById(R.id.main_btn_enviar);
        btnEnviar.setOnClickListener(v -> {
            String nombre = campoNombre.getText().toString();
            String correo = campoCorreo.getText().toString();
            String mensaje ="Nombre: " + nombre + "\nCorreo: " + correo + "\nMensaje: " +campoMensaje.getText().toString();

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822"); // Tipo MIME para email
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{correo}); // Cambia por tu correo
            intent.putExtra(Intent.EXTRA_SUBJECT, "Formulario de contacto");
            intent.putExtra(Intent.EXTRA_TEXT, mensaje);

            try {
                startActivity(Intent.createChooser(intent, "Enviar correo contacto"));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this, "No hay clientes de correo instalados.", Toast.LENGTH_SHORT).show();
            }

        });
        Button btnGuardarFile = findViewById(R.id.main_btn_guardarfile);
        btnGuardarFile.setOnClickListener(v -> {
            String nombre = campoNombre.getText().toString();
            String correo = campoCorreo.getText().toString();
            String mensaje ="Nombre: " + nombre + "\nCorreo: " + correo + "\nMensaje: " +campoMensaje.getText().toString();
            generarArhivo(mensaje);
        });

    }
    /*
GENERAR FILEIO
Se debe declarar los pèrmisos en el android manifest
 */
    public void generarArhivo(String mensaje){
        String texto="";
        try {
            FileOutputStream outputStream = null;
            // Mode private remplaza el texto y mode appende ajunta mas
            outputStream =openFileOutput("MiContactos.txt", Context.MODE_APPEND);
            texto = mensaje;
            outputStream.write(texto.getBytes());
            outputStream.close();
            Toast.makeText(this, "Archivo creado", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error al crear el archivo", Toast.LENGTH_SHORT).show();
        }
    }
}
