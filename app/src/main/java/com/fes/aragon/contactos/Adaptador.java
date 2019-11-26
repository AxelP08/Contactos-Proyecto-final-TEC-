package com.fes.aragon.contactos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fes.aragon.contactos.BD.Contacto;

import java.util.ArrayList;
import java.util.List;

public class Adaptador extends ArrayAdapter<Contacto> {

    private Context contexto;
    private List contactos;

    public Adaptador(Context contexto, List<Contacto> contactos) {
        super(contexto, 0, contactos);

        this.contexto = contexto;
        this.contactos = contactos;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(contexto);
        View item = inflater.inflate(R.layout.item_contacto, null);

        TextView tvNombre = item.findViewById(R.id.tvNombre);
        TextView numero = item.findViewById(R.id.tvNumero);
        ImageView btnLlamar = item.findViewById(R.id.btnLLamar);

        Contacto contacto = getItem(position);

        tvNombre.setText(contacto.getNombres().split(" ")[0] + " " + contacto.getApellidos().split(" ")[0]);
        numero.setText(contacto.getCelular());

        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contacto contacto = (Contacto) contactos.get(position);
                String telefono = contacto.getCelular();

                Intent llamada = new Intent(Intent.ACTION_CALL);
                llamada.setData(Uri.parse("tel: " + telefono));

                try {
                    contexto.startActivity(llamada);
                } catch (SecurityException e) {
                    Log.e("ERROR","Falta permiso");
                }
            }
        });

        return item;
    }
}
