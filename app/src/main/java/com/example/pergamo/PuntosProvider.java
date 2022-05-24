package com.example.pergamo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PuntosProvider extends ContentProvider {

    private PuntosDBHelper dbHelper;
    public static final byte COLECCION_PUNTOS = 100;
    public static final byte COLECCION_PUNTOS_CON_ID = 101;

    private static final UriMatcher CONTENT_MATCHER;

    static {
        CONTENT_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        CONTENT_MATCHER.addURI(PuntosDBContract.AUTHORITY, PuntosDBContract.PuntosObtenidos.PATH_PUNTOS, COLECCION_PUNTOS);
        CONTENT_MATCHER.addURI(PuntosDBContract.AUTHORITY,PuntosDBContract.PuntosObtenidos.PATH_PUNTOS +"/#", COLECCION_PUNTOS_CON_ID);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new PuntosDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor result;

        switch (CONTENT_MATCHER.match(uri)) {
            case COLECCION_PUNTOS:
                result = db.query(PuntosDBContract.PuntosObtenidos.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case COLECCION_PUNTOS_CON_ID:
                result = db.query(PuntosDBContract.PuntosObtenidos.TABLE_NAME, projection, PuntosDBContract.PuntosObtenidos._ID + " =?",
                        new String[] {uri.getPathSegments().get(1)}, null, null, null);
                break;
            default:
                throw new UnsupportedOperationException("Invalid URI: " + uri);
        }
        Context context = getContext();
        if (context != null)
            result.setNotificationUri(context.getContentResolver(), uri);
        return result;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long newId;
        switch(CONTENT_MATCHER.match(uri)) {
            case COLECCION_PUNTOS:
                newId = db.insert(PuntosDBContract.PuntosObtenidos.TABLE_NAME, null, contentValues);
                break;
            case COLECCION_PUNTOS_CON_ID:
                if(contentValues == null) contentValues = new ContentValues();
                contentValues.put(PuntosDBContract.PuntosObtenidos._ID, uri.getPathSegments().get(1));
                newId = db.insert(PuntosDBContract.PuntosObtenidos.TABLE_NAME, null, contentValues);
                break;
            default:
                throw new UnsupportedOperationException("No se puede insertar en " + uri);
        }
        if(newId <= 0) throw new SQLException("No se puede insertar ");
        return ContentUris.withAppendedId(PuntosDBContract.PuntosObtenidos.CONTENT_URI, newId);
    }

    /**
     * No fue implementado este método ya que nuestra base de datos únicamente necesita de la operación insert.
     * **/
    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    /**
     * No fue implementado este método ya que nuestra base de datos únicamente necesita de la operación insert.
     * **/
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch(CONTENT_MATCHER.match(uri)){
            case COLECCION_PUNTOS:
            case COLECCION_PUNTOS_CON_ID:
                return PuntosDBContract.PuntosObtenidos.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("tipo desconocido para " + uri);
        }
    }

}
