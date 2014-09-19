package com.bootcamp.globant;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.bootcamp.globant.contentprovider.FacultarContentProvider;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Insert
//		ContentValues cv = new ContentValues();
//		cv.put("nombre", "Leo");
//		cv.put("edad", "20");
//		getContentResolver().insert(FacultarContentProvider.CONTENT_URI, cv);
				
		// Delete
		int rows = getContentResolver().delete(FacultarContentProvider.CONTENT_URI.buildUpon().appendPath(Long.toString(1)).build(), null , null);		
		
		// Recorrer los elementos.
		Cursor cursor = managedQuery(FacultarContentProvider.CONTENT_URI , null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Log.e("INFO", "Nombre: " + cursor.getString(1) + " ;  Edad: " + cursor.getString(2));
			cursor.moveToNext();
		}
		
		Log.e("INFO", "Total registros: " + cursor.getCount() + " ; " + " Nro Columnas: " + cursor.getColumnCount() + " ; columna 1: " + cursor.getColumnName(0) + " columna 2: " + cursor.getColumnName(1) + " columna 3: " + cursor.getColumnName(2) );			
		
		cursor.close();		
	}			 
	
}
