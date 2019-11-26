package com.fes.aragon.contactos;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fes.aragon.contactos.BD.BDHelper;
import com.fes.aragon.contactos.BD.Contacto;
import com.fes.aragon.contactos.BD.ContactoRW;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class NuevoContacto extends AppCompatActivity {

    //Variables de UI:
    private TextInputEditText etNombre;
    private TextInputEditText etApellido;
    private TextInputEditText etCelular;
    private TextInputEditText etCasa;
    private TextInputEditText etEmail;
    private FloatingActionButton fabGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto);

        ActionBar barra = getSupportActionBar();
        barra.setTitle("Nuevo contacto");
        barra.setDisplayHomeAsUpEnabled(true);

        //Inicializar UI:
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etCelular = findViewById(R.id.etCelular);
        etCasa = findViewById(R.id.etCasa);
        etEmail = findViewById(R.id.etEmail);
        fabGuardar = findViewById(R.id.fabGuardar);

        //Preparar la base de datos para guardar:
        BDHelper bdHelper = new BDHelper(this);
        final ContactoRW contactoRW = new ContactoRW(bdHelper);

        //Click a botón guardar:
        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etNombre.getText().toString().equals("") || etNombre.getText().toString().equals(" ") ||
                        etApellido.getText().toString().equals("") || etApellido.getText().toString().equals(" ") ||
                        etCelular.getText().toString().equals("") || etCelular.getText().toString().equals(" ") ||
                        etCasa.getText().toString().equals("") || etCasa.getText().toString().equals(" ") ||
                        etEmail.getText().toString().equals("") || etEmail.getText().toString().equals(" ")) {


                    Snackbar.make(etEmail, "Ningún campo puede quedar vacío.", Snackbar.LENGTH_LONG).show();
                } else {
                    Contacto contacto = new Contacto();

                    contacto.setNombres(etNombre.getText().toString());
                    contacto.setApellidos(etApellido.getText().toString());
                    contacto.setCelular(etCelular.getText().toString());
                    contacto.setCasa(etCasa.getText().toString());
                    contacto.setEmail(etEmail.getText().toString());

                    contactoRW.escribirContacto(contacto);

                    Snackbar.make(fabGuardar, "Contacto guardado", Snackbar.LENGTH_SHORT).show();

                    etNombre.setText("");
                    etApellido.setText("");
                    etCelular.setText("");
                    etCasa.setText("");
                    etEmail.setText("");

                    Contacto guardado = contactoRW.leerContactoPorCelular(contacto.getCelular());

                    Intent detalles = new Intent(NuevoContacto.this, DetalleContacto.class);
                    detalles.putExtra("ID", guardado.getId());
                    startActivity(detalles);
                }
            }
        });
    }
}
