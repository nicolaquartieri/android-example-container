package com.bootcamp.globant.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bootcamp.globant.dao.DAO;
import com.bootcamp.globant.model.Persona;
import com.bootcamp.globant.sql.MiSQLiteHelper;

public class PersonaDAOImp implements DAO {

	private MiSQLiteHelper sql;
	private SQLiteDatabase db;
	private String[] columnas = new String[] { MiSQLiteHelper.PERSONA_ID, MiSQLiteHelper.PERSONA_COLUMNA_NOMBRE, MiSQLiteHelper.PERSONA_COLUMNA_EDAD };
	
	
	public PersonaDAOImp(Context context) {
		this.sql = new MiSQLiteHelper(context);
	}

	@Override
	public void open() {
		this.db = sql.getWritableDatabase();
	}

	@Override
	public void close() {
		sql.close();
	}

	@Override
	public long insert(Map<String, ?> valores) {		
		ContentValues cv = new ContentValues();				
		cv.put("nombre", (String)valores.get("nombre"));
		cv.put("edad", (Integer)valores.get("edad"));
		
		long id = db.insert(MiSQLiteHelper.TABLE_PERSONA, null, cv);
		
		return id;
	}

	@Override
	public void delete(int id) {
		db.delete(MiSQLiteHelper.TABLE_PERSONA, MiSQLiteHelper.PERSONA_ID + " = " + id, null);
	}

	@Override
	public List<?> getAll() {
		List<Persona> lista = new ArrayList<Persona>();
		
		Cursor cursor = db.query(MiSQLiteHelper.TABLE_PERSONA, columnas, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Persona persona = new Persona(cursor.getString(cursor.getColumnIndex("nombre")), cursor.getInt(cursor.getColumnIndex("edad")));
			
			lista.add(persona);
			
			cursor.moveToNext();
		}
		cursor.close();
		
		return lista;
	}

}
