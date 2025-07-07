package com.example.miscontactos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetalleContacto extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvTelefono;
    private TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        Bundle parametros = getIntent().getExtras();

        String nombre = parametros.getString(getString(R.string.pnombre));
        String email = parametros.getString(getString(R.string.pemail));
        String telefono = parametros.getString(getString(R.string.ptelefono));

        tvNombre = findViewById(R.id.tvNombre);
        tvNombre.setText(nombre);
        tvTelefono =  findViewById(R.id.tvTelefono);
        tvTelefono.setText(telefono);
        tvEmail =  findViewById(R.id.tvEmail);
        tvEmail.setText(email);

    }

    public void llamar(View view) {
        String telefono = tvTelefono.getText().toString();
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono)));
    }
    public void enviarEmail(View view) {
        String email = tvEmail.getText().toString();
        Intent emailIntent= new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL,email);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent,"Email"));

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(DetalleContacto.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;

        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, getString(R.string.onstart_contacto), Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, getString(R.string.onresume_contacto), Toast.LENGTH_SHORT).show();
    }

    //Actividad corriendo

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, getString(R.string.onrestart_contacto), Toast.LENGTH_SHORT).show();
    }

    //Actividad detenida

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, getString(R.string.onpause_contacto), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, getString(R.string.onstop_contacto), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, getString(R.string.ondestroy_contacto), Toast.LENGTH_SHORT).show();
    }
}