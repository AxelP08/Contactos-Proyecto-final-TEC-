package com.fes.aragon.contactos.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDHelper extends SQLiteOpenHelper {

    private static final String CREAR_TABLA_CONTACTOS = "CREATE TABLE IF NOT EXISTS contactos(_id INTEGER" +
            " PRIMARY KEY AUTOINCREMENT, nombres TEXT, apellidos TEXT, celular TEXT, casa TEXT, email TEXT)";

    private static final String BD_NAME = "contactosFES.sqlite";
    private static final int BD_VERSION = 1;

    public BDHelper(Context contexto) {
        super(contexto, BD_NAME, null, BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREAR_TABLA_CONTACTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE contactos");

        onCreate(sqLiteDatabase);
    }
}
