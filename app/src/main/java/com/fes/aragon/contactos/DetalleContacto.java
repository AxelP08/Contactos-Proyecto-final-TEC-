package com.fes.aragon.contactos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.fes.aragon.contactos.BD.BDHelper;
import com.fes.aragon.contactos.BD.Contacto;
import com.fes.aragon.contactos.BD.ContactoRW;

public class DetalleContacto extends AppCompatActivity {

    //Variables de BD:
    private String id;
    private BDHelper bdHelper;
    private ContactoRW contactoRW;

    //Variables de UI:
    private TextView tvDNombre;
    private TextView tvDCelular;
    private TextView tvDCasa;
    private TextView tvDEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        //Inicializar UI:
        tvDNombre = findViewById(R.id.tvDNombre);
        tvDCelular = findViewById(R.id.tvDCelular);
        tvDCasa = findViewById(R.id.tvDCasa);
        tvDEmail = findViewById(R.id.tvDEmail);

        //Obtener el id del contacto:
        Bundle extra = getIntent().getExtras();
        id = extra.getString("ID");

        //Obtener informacion del contacto de base de datos:
        bdHelper = new BDHelper(this);
        contactoRW = new ContactoRW(bdHelper);

        Contacto contacto = contactoRW.leerContactoPorId(id);

        //Rellenar la pantalla con datos del contacto:
        ActionBar barra = getSupportActionBar();
        barra.setTitle(contacto.getNombres().split(" ")[0] + " " + contacto.getApellidos().split(" ")[0]);

        tvDNombre.setText(contacto.getNombres() + " " + contacto.getApellidos());
        tvDCelular.setText(contacto.getCelular());
        tvDCasa.setText(contacto.getCasa());
        tvDEmail.setText(contacto.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contacto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemBorrar) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirmar eliminación")
                    .setMessage("¿Estás seguro de que quieres eliminar el contacto?")
                    .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            contactoRW.eliminarContactoPorId(id);
                            onBackPressed();
                        }
                    })
                    .setNegativeButton("Cancelar", null);

            builder.create().show();
        } else if (item.getItemId() == android.R.id.home) {
            Intent inicio = new Intent(DetalleContacto.this, MainActivity.class);
            inicio.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(inicio);
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        Intent inicio = new Intent(DetalleContacto.this, MainActivity.class);
        inicio.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(inicio);
    }
}
