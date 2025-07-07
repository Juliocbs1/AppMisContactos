package com.example.miscontactos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Contacto> contactos;
    private RecyclerView listaContactos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(miActionBar);



        listaContactos = (RecyclerView) findViewById(R.id.rvContactos);
        /*LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);*/
        GridLayoutManager llm = new GridLayoutManager(this,2);

        listaContactos.setLayoutManager(llm);

        inicializarListaContacto();
        inicializarAdaptador();




        /* ListView lstContactos = (ListView) findViewById(R.id.lstContactos);
        lstContactos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombresContactos));




        lstContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,DetalleContacto.class);
                intent.putExtra(getString(R.string.pnombre),contactos.get(position).getNombre());
                intent.putExtra(getString(R.string.ptelefono),contactos.get(position).getTelefono());
                intent.putExtra(getString(R.string.pemail),contactos.get(position).getEmail());

                startActivity(intent);
                finish();
            }
        });

*/


    }

    public void inicializarAdaptador(){
        ContactoAdaptador adaptador = new ContactoAdaptador(contactos,this);
        listaContactos.setAdapter(adaptador);
    }

    public void inicializarListaContacto(){
        contactos= new ArrayList<Contacto>();
        contactos.add(new Contacto("Anahi Salgado", "5555555555", "anahi@example.com",R.drawable.coservipp));
        contactos.add(new Contacto("Jose","255155","jose@example.com",R.drawable.falco));
        contactos.add(new Contacto("Maria","65544","maria@example.com",R.drawable.coservipp));
        contactos.add(new Contacto("Juan","18831","juan@example.com",R.drawable.falco));
        contactos.add(new Contacto("Pedro","645623","pedro@example.com",R.drawable.coservipp));
    }


    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, getString(R.string.onstart), Toast.LENGTH_SHORT).show();
        
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, getString(R.string.onresume), Toast.LENGTH_SHORT).show();
    }

    //Actividad corriendo

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, getString(R.string.onrestart), Toast.LENGTH_SHORT).show();
    }

    //Actividad detenida

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, getString(R.string.onpause), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, getString(R.string.onstop), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, getString(R.string.ondestroy), Toast.LENGTH_SHORT).show();
    }
}
