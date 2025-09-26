package com.example.miscontactos.adapter;

import static android.widget.Toast.makeText;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miscontactos.db.ConstructorContactos;
import com.example.miscontactos.model.Contacto;
import com.example.miscontactos.DetalleContacto;
import com.example.miscontactos.R;

import java.util.ArrayList;

public class ContactoAdaptador extends RecyclerView.Adapter<ContactoAdaptador.ContactoViewHolder> {

    private ArrayList<Contacto> contactos;
    private Activity act;

    public ContactoAdaptador(ArrayList<Contacto> contactos, Activity act) {
        this.contactos = contactos;
        this.act = act;
    }


    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {// Inflar el Layout y lo pasara al viewholder para que obtenga los views
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto, parent, false); // Inflar la vista
        return new ContactoViewHolder(v); // Regresar el viewholder con la vista inflada
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {// Obtener el elemento desde la lista
        Contacto contacto = contactos.get(position);
        holder.imgFoto.setImageResource(contacto.getFoto());
        holder.tvNombre.setText(contacto.getNombre());
        holder.tvTelefono.setText(contacto.getTelefono());
        holder.tvLike.setText(String.valueOf(contacto.getLike()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(act, DetalleContacto.class);
               intent.putExtra(act.getString(R.string.pnombre),contacto.getNombre());
               intent.putExtra(act.getString(R.string.ptelefono),contacto.getTelefono());
               intent.putExtra(act.getString(R.string.pemail),contacto.getEmail());
               act.startActivity(intent);


            }
        });

        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(act, "Diste Like a " + contacto.getNombre(), Toast.LENGTH_SHORT).show();



                ConstructorContactos constructorContactos = new ConstructorContactos(act);
                constructorContactos.insertarLikeContacto(contacto.getId(), contacto.getLike());

                holder.tvLike.setText(String.valueOf(constructorContactos.obtenerLikesContacto(contacto)));

            }

        });

    }

    @Override
    public int getItemCount() { // Cantidad de Elementos que contiene mi lista
        return contactos.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder{// Clase interna para crear los viewholders

        private ImageView imgFoto;
        private TextView tvNombre;
        private TextView tvTelefono;

        private TextView tvLike;

        private ImageButton btnLike;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
            tvTelefono = (TextView) itemView.findViewById(R.id.tvTelefono);
            btnLike = (ImageButton) itemView.findViewById(R.id.btnLike);
            tvLike = (TextView) itemView.findViewById(R.id.tvLike);
        }
    }
}
