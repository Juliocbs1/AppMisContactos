package com.example.miscontactos;


import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import androidx.viewpager2.widget.ViewPager2;

import com.example.miscontactos.adapter.PageAdaptador;
import com.example.miscontactos.fragment.PerfilFragment;
import com.example.miscontactos.fragment.RecyclerViewFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;

    // Launcher para mostrar el diálogo del sistema que enciende el Bluetooth
    private ActivityResultLauncher<Intent> enableBluetoothLauncher;

    // Launcher para solicitar permisos en tiempo de ejecución (Android 12+)
    private ActivityResultLauncher<String[]> btPermissionLauncher;

    private static final int CODIGO_SOLICITUD_PERMISO =1 ;
    private Context context;

    private Toolbar toolbar;

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Estaremos revisando el archivo manifest
        context = getApplicationContext();
        activity = this;


         toolbar = findViewById(R.id.toolbar);
         if(toolbar != null){
             setSupportActionBar(toolbar);
         }



        tabLayout = findViewById(R.id.tabLa);
        viewPager = findViewById(R.id.viewPager);
        setUpViewPager();


        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // 1) Registrar launcher para habilitar Bluetooth (reemplaza startActivityForResult)
        enableBluetoothLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Toast.makeText(this, "Bluetooth activado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Bluetooth no activado", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        // 2) Registrar launcher para solicitar permisos (Android 12+)
        btPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                result -> {
                    Boolean granted = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        granted = result.getOrDefault(Manifest.permission.BLUETOOTH_CONNECT, false);
                    }
                    if (Boolean.TRUE.equals(granted)) {
                        // Si se concedió el permiso, volvemos a intentar activar Bluetooth
                        pedirActivarBluetooth();
                    } else {
                        Toast.makeText(this, "Permiso de Bluetooth denegado", Toast.LENGTH_SHORT).show();
                    }
                }
        );





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
        }else if(itemId == R.id.mBluetooh){
         habilitarBluetooh();
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contexto,menu);
    }
    //BLUETOOTH

    //PERMISOS BLUETOOTH EN ANDROID 12+ SE DEBE ESPECIFICAR PERMISO ENJ EL MANIFEST COMO SCAN O CONNECT

    //Recordar añadir en el manifest para los permiso
    public void habilitarBluetooh(){
        //Gestionar si el dispositivo tiene bluetooh

        if(bluetoothAdapter == null){
            Toast.makeText(this,"El dispositivo no tiene bluetooh", Toast.LENGTH_SHORT).show();
            return;
        }
        // 1) Si es Android 12+ verifica/solicita permisos en tiempo de ejecución
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !tienePermisosBluetooth()) {
            solicitarPermisosBluetooth();
            return; // Espera al callback del launcher
        }

        // 2) Con permisos OK (o no necesarios), pide encender Bluetooth
        pedirActivarBluetooth();


    }
    // Lógica para lanzar el intent del sistema que enciende Bluetooth
    private void pedirActivarBluetooth() {
        if (!bluetoothAdapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            enableBluetoothLauncher.launch(intent);
        } else {
            Toast.makeText(this, "Bluetooth ya estaba activado", Toast.LENGTH_SHORT).show();
        }
    }

    // ====== Permisos ======

    private boolean tienePermisosBluetooth() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            int connect = ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT);
            // Si además vas a escanear dispositivos, comprueba también BLUETOOTH_SCAN
            // int scan = ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN);
            // return connect == PackageManager.PERMISSION_GRANTED && scan == PackageManager.PERMISSION_GRANTED;
            return connect == PackageManager.PERMISSION_GRANTED;
        } else {
            // En Android 11- no hay permisos peligrosos para encender Bluetooth
            return true;
        }
    }
    private void solicitarPermisosBluetooth() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // Agrega BLUETOOTH_SCAN si vas a escanear
            btPermissionLauncher.launch(new String[] {
                    Manifest.permission.BLUETOOTH_CONNECT
                    // , Manifest.permission.BLUETOOTH_SCAN
            });
        }
    }




}
