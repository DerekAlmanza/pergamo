package com.example.pergamo;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class PuntosDBContract {
    public static final String AUTHORITY = "com.example.pergamo";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" +AUTHORITY);
    private PuntosDBContract() {} // constructor privadp

    public static class PuntosObtenidos implements BaseColumns {
        public static final String PATH_PUNTOS = "puntos";
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PUNTOS).build();
        public static final String TABLE_NAME = PATH_PUNTOS;
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_FECHA = "fecha";
        public static final String COLUMN_PUNTOS_OBTENIDOS = "puntos_obtenidos";

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + PATH_PUNTOS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" +AUTHORITY +"/" + PATH_PUNTOS;
        public static Uri buildListSizeUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
