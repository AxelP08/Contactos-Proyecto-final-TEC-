package com.fes.aragon.contactos.BD;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ContactoRW {

    private BDHelper bdHelper;

    public ContactoRW(BDHelper bdHelper) {
        this.bdHelper = bdHelper;
    }

    public void escribirContacto(Contacto contacto) {
        SQLiteDatabase bd = bdHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("nombres", contacto.getNombres());
        cv.put("apellidos", contacto.getApellidos());
        cv.put("celular", contacto.getCelular());
        cv.put("casa", contacto.getCasa());
        cv.put("email", contacto.getEmail());

        bd.insert("contactos", null, cv);

        bd.close();
    }

    public ArrayList<Contacto> leerContactos() {
        ArrayList<Contacto> contactos = new ArrayList<Contacto>();

        SQLiteDatabase bd = bdHelper.getReadableDatabase();

        String query = "SELECT * FROM contactos";

        Cursor cursor = bd.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            contactos = new ArrayList<Contacto>();

            do {
                Contacto contacto = new Contacto();

                contacto.setId(cursor.getString(0));
                contacto.setNombres(cursor.getString(1));
                contacto.setApellidos(cursor.getString(2));
                contacto.setCelular(cursor.getString(3));
                contacto.setCasa(cursor.getString(4));
                contacto.setEmail(cursor.getString(5));

                contactos.add(contacto);
            } while (cursor.moveToNext());
        }

        bd.close();

        return contactos;
    }

    public Contacto leerContactoPorId(String id) {
        Contacto contacto = null;

        SQLiteDatabase bd = bdHelper.getReadableDatabase();

        String query = "SELECT * FROM contactos WHERE _id=" + id;

        Cursor cursor = bd.rawQuery(query, null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            contacto = new Contacto();

            contacto.setId(cursor.getString(0));
            contacto.setNombres(cursor.getString(1));
            contacto.setApellidos(cursor.getString(2));
            contacto.setCelular(cursor.getString(3));
            contacto.setCasa(cursor.getString(4));
            contacto.setEmail(cursor.getString(5));
        }

        bd.close();

        return contacto;
    }

    public void eliminarContactoPorId(String id) {
        SQLiteDatabase bd = bdHelper.getWritableDatabase();

        bd.execSQL("DELETE FROM contactos WHERE _id=" + id);

        bd.close();
    }

    public Contacto leerContactoPorCelular(String celular) {
        SQLiteDatabase bd = bdHelper.getReadableDatabase();

        Contacto contacto = new Contacto();

        String query = "SELECT * FROM contactos WHERE celular=" + celular;

        Cursor cursor = bd.rawQuery(query, null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            contacto.setId(cursor.getString(0));
            contacto.setNombres(cursor.getString(1));
            contacto.setApellidos(cursor.getString(2));
            contacto.setCelular(cursor.getString(3));
            contacto.setCasa(cursor.getString(4));
            contacto.setEmail(cursor.getString(5));
        }

        bd.close();

        return contacto;
    }
}
