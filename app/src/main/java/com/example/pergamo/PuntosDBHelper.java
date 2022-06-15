package com.example.pergamo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PuntosDBHelper extends SQLiteOpenHelper {
    public static final byte DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "puntos.db";

    public PuntosDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Define los atributos que tendrá la tabla de base de datos.
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREA_TABLA_PUNTOS = "CREATE TABLE " + PuntosDBContract.PuntosObtenidos.TABLE_NAME + " ("
                + PuntosDBContract.PuntosObtenidos._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PuntosDBContract.PuntosObtenidos.COLUMN_FECHA + " TEXT NOT NULL,"
                + PuntosDBContract.PuntosObtenidos.COLUMN_PUNTOS_OBTENIDOS + " INTEGER);";
        db.execSQL(SQL_CREA_TABLA_PUNTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
