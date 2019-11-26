package com.fes.aragon.contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fes.aragon.contactos.BD.BDHelper;
import com.fes.aragon.contactos.BD.Contacto;
import com.fes.aragon.contactos.BD.ContactoRW;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contacto> contactos;

    //Variables de UI:
    private ListView lvContactos;
    private TextView tvSinContactos;
    private FloatingActionButton fabNuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar UI:
        lvContactos = findViewById(R.id.lvContactos);
        tvSinContactos =findViewById(R.id.tvSinContactos);
        fabNuevo = findViewById(R.id.fabNuevo);

        //Verificar permiso de teléfono y solicitarlo si no lo hay:
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            MainActivity.this.requestPermissions(new String[] {Manifest.permission.CALL_PHONE}, 1);
        }

        fabNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevo = new Intent(MainActivity.this, NuevoContacto.class);
                startActivity(nuevo);
            }
        });

        lvContactos.setClickable(true);

        lvContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contacto contacto = (Contacto) adapterView.getItemAtPosition(i);
                String id = contacto.getId();

                Intent detalle = new Intent(MainActivity.this, DetalleContacto.class);
                detalle.putExtra("ID", id);
                startActivity(detalle);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Obtener la lista de contactos:
        BDHelper bdHelper = new BDHelper(this);
        ContactoRW contactoRW = new ContactoRW(bdHelper);

        contactos = contactoRW.leerContactos();

        if(contactos.size() > 0) {
            tvSinContactos.setVisibility(View.GONE);

            Adaptador adaptador = new Adaptador(this, contactos);
            lvContactos.setAdapter(adaptador);
        } else {
            tvSinContactos.setVisibility(View.VISIBLE);
        }
    }
}
