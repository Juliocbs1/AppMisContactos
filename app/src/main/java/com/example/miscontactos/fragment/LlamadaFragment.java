package com.example.miscontactos.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.miscontactos.R;

public class LlamadaFragment extends Fragment {


    private Button button; // Referencia al botón "Mostrar Llamadas"

    // Launcher moderno para pedir UN permiso en tiempo de ejecución.
    // Se registra una sola vez como campo; el callback 'isGranted' llega cuando el usuario responde.
    private final ActivityResultLauncher<String> requestReadCallLog =
            registerForActivityResult(
                    new ActivityResultContracts.RequestPermission(), // Contrato para pedir un permiso
                    isGranted -> { // Callback: true si el usuario concedió, false si denegó
                        if (isGranted) { // Si el permiso fue otorgado
                            Toast.makeText(requireContext(), "Permiso READ_CALL_LOG otorgado", Toast.LENGTH_SHORT).show(); // Aviso
                            //consultarContentProviderLlamadas(); // Llamamos al método que lee el registro
                        } else { // Si el permiso fue denegado
                            // shouldShowRequestPermissionRationale = false significa que el usuario marcó "No volver a preguntar" (o política del dispositivo)
                            if (!shouldShowRequestPermissionRationale(Manifest.permission.READ_CALL_LOG)) {
                                Toast.makeText(requireContext(),
                                        "Permiso denegado permanentemente. Habilítalo en Ajustes.",
                                        Toast.LENGTH_LONG).show(); // Advertimos que debe ir a Ajustes
                                abrirAjustes(); // Abrimos la pantalla de detalles de la app
                            } else {
                                // Denegación normal (aún podemos volver a solicitar en el futuro)
                                Toast.makeText(requireContext(),
                                        "Permiso denegado. No puedo leer el registro.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            );

    public LlamadaFragment() {
        // Constructor vacío requerido por el sistema. No hagas trabajo pesado aquí.
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla el layout XML del Fragment y devuelve la raíz de la vista
        // Debe coincidir con res/layout/fragment_llamada.xml
        return inflater.inflate(R.layout.fragment_llamada, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Se llama justo después de que la vista fue creada; es buen lugar para setear listeners
        super.onViewCreated(view, savedInstanceState);

        // Obtenemos la referencia al botón definido en el XML por su id
        button = view.findViewById(R.id.btnMostrarLLamadas);

        // Asignamos el click listener al botón
        button.setOnClickListener(v -> {
            // Al pulsar, verificamos si ya tenemos el permiso
            if (tieneReadCallLog()) { // Si el permiso ya está concedido
                Toast.makeText(requireContext(), "Permiso ya otorgado", Toast.LENGTH_SHORT).show(); // Aviso
                //consultarContentProviderLlamadas(); // Leemos el registro directamente
            } else { // Si NO está concedido
                solicitarPermisoReadCallLog(); // Iniciamos el flujo para solicitarlo
            }
        });
    }

    private void solicitarPermisoReadCallLog() {
        // Si debemos mostrar una explicación previa (rationale) al usuario
        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CALL_LOG)) {
            // Aquí podrías mostrar un diálogo bonito explicando para qué quieres el permiso
            Toast.makeText(requireContext(),
                    "Necesito leer tu registro de llamadas para poder mostrarlo en la app.",
                    Toast.LENGTH_LONG).show(); // Mensaje simple como ejemplo
        }
        // Lanzamos la solicitud del permiso usando el launcher moderno
        requestReadCallLog.launch(Manifest.permission.READ_CALL_LOG);
    }

    private boolean tieneReadCallLog() {
        // Comprueba si el permiso READ_CALL_LOG está concedido para este contexto
        return ContextCompat.checkSelfPermission(
                requireContext(), // Contexto asociado al Fragment (lanza excepción si está desacoplado)
                Manifest.permission.READ_CALL_LOG // El permiso a verificar
        ) == PackageManager.PERMISSION_GRANTED; // Compara contra la constante "concedido"
    }

    private void abrirAjustes() {
        // Construye un Intent al detalle de la app en Ajustes del sistema
        Intent i = new Intent(
                android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, // Acción para abrir detalles de la app
                Uri.parse("package:" + requireContext().getPackageName()) // URI con el packageName de tu app
        );
        // Inicia la Activity de Ajustes; el usuario podrá ir a Permisos y habilitarlo manualmente
        startActivity(i);
    }

    public void consultarCPLLlamadas() {
       // TextView textView = requireView().findViewById(R.id.textView);

    }


}
