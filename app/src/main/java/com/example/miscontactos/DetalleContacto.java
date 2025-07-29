package com.example.miscontactos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetalleContacto extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvTelefono;
    private TextView tvEmail;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        toolbar = findViewById(R.id.toolbarnorm);
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }




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

        registerForContextMenu(tvNombre);

        //Menu popup
        ImageView imgContacto = findViewById(R.id.imgContacto);
        imgContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                levantaMenuPopup(view);
            }
        });
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

            return true;

        }
        return super.onKeyDown(keyCode, event);

    }

    //Menu de contexto
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contexto,menu);
    }
    //Verificar que opcion se selecciono

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.mEdit){
            Toast.makeText(this,getString(R.string.menu_edit),Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.mDelete){
            Toast.makeText(this,getString(R.string.menu_delete),Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

    //Acciones menu popup
    public void levantaMenuPopup(View view){
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
               if(menuItem.getItemId() == R.id.mView){
                   Toast.makeText(DetalleContacto.this,getString(R.string.menu_view),Toast.LENGTH_SHORT).show();
               }else if(menuItem.getItemId() == R.id.mViewDetail){
                   Toast.makeText(DetalleContacto.this,getString(R.string.menu_view_detail),Toast.LENGTH_SHORT).show();
               }
                return false;

            }
        });
        popupMenu.show();
        }
    }
