package com.bootcamp.globant.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MiSQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_PERSONA = "persona";
	public static final String PERSONA_ID = "_id";
	public static final String PERSONA_COLUMNA_NOMBRE = "nombre";
	public static final String PERSONA_COLUMNA_EDAD = "edad";
	
	public static final String DATABASE_NOMBRE = "facultad.db";
	public static final int DATABASE_VERSION = 1;
	
	public MiSQLiteHelper(Context context) {
		super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) { // Si la base no existe.
		db.execSQL("create table " + TABLE_PERSONA + "(" + PERSONA_ID + " integer primary key autoincrement, " + PERSONA_COLUMNA_NOMBRE + " text not null, " + PERSONA_COLUMNA_EDAD + " integer not null); "); 
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) { // Actualizar el schema.
		Log.e("INFO", "DB Actualizada.");
		
		db.execSQL("drop table id exists " + TABLE_PERSONA);
		onCreate(db);
	}

}
