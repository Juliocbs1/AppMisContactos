package com.example.miscontactos;


import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.miscontactos.adapter.ContactoAdaptador;
import com.example.miscontactos.adapter.PageAdaptador;
import com.example.miscontactos.fragment.PerfilFragment;
import com.example.miscontactos.fragment.RecyclerViewFragment;
import com.example.miscontactos.pojo.Contacto;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {



    private Toolbar toolbar;

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         toolbar = findViewById(R.id.toolbar);
         if(toolbar != null){
             setSupportActionBar(toolbar);
         }



        tabLayout = findViewById(R.id.tabLa);
        viewPager = findViewById(R.id.viewPager);
        setUpViewPager();






    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());
        return fragments;

    }
    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdaptador(this,agregarFragments()));

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:

                            tab.setIcon(R.drawable.controlar);
                            break;
                        case 1:

                            tab.setIcon(R.drawable.usuario);
                            break;
                    }
                }
        ).attach();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.mAbout) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);

        }else if(itemId == R.id.mSettings){
                Intent intent2 = new Intent(this, SettingsActivity.class);
                startActivity(intent2);
        }else if(itemId == R.id.mContacto){
            Intent intent3 = new Intent(this, FormularioContacto.class);
            startActivity(intent3);
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contexto,menu);
    }


}
