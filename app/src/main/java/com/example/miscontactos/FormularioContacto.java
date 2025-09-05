package com.example.miscontactos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
        Button btnGuardarReferencia = findViewById(R.id.main_btn_guardareferencia);
        btnGuardarReferencia.setOnClickListener(v -> {
            String nombre = campoNombre.getText().toString();
            String correo = campoCorreo.getText().toString();
            String mensaje =campoMensaje.getText().toString();
            guardarPreferencia(nombre,correo,mensaje);

        });
        Button btnMostrarReferencia = findViewById(R.id.main_btn_mostrarefrencia);
        btnMostrarReferencia.setOnClickListener(v -> {

            mostrarPreferencia();
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
    /*
    SHARED PREFERENCIAS, ARHCIVOS EPQUEÑOS XML DE CLAVE VALOR, PARA PALARAS DENTRO DE LA APP, COLORES ETC.
     */

    public void guardarPreferencia(String nombre, String correo, String mensaje){
        SharedPreferences sharedPreferences = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nombre",nombre);
        editor.putString("correo",correo);
        editor.putString("mensaje",mensaje);
        editor.apply();
        Toast.makeText(this, "Preferencias guardadas", Toast.LENGTH_SHORT).show();

    }
    public void mostrarPreferencia(){

        SharedPreferences sharedPreferences = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);
        String nombreGuardado = sharedPreferences.getString("nombre","No existe");
        String correoGuardado = sharedPreferences.getString("correo","No existe");
        String mensajeGuardado = sharedPreferences.getString("mensaje","No existe");

        TextView campoPreferencia = findViewById(R.id.text_referencia);
        campoPreferencia.setText("Nombre: " + nombreGuardado + "\nCorreo: " + correoGuardado + "\nMensaje: " +mensajeGuardado);

    }
}
