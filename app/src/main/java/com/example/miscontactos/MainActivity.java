package com.example.miscontactos;


import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Contacto> contactos;
    private RecyclerView listaContactos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



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



}
