package com.bootcamp.globant.service;

import android.app.Activity;
import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/*
 * Los intent services se utilizan para realizar una tarea y fin de la historia.
 */
public class DownloadService extends IntentService {

	private int result = Activity.RESULT_CANCELED;
	
	public DownloadService() {
		super("MiDownloadService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		result = Activity.RESULT_OK;
		
		Bundle bundle = intent.getExtras();
		Messenger messanger = (Messenger) bundle.get("MESSENGER");
		Message message = Message.obtain();
		message.arg1 = result;
		
		try {
			messanger.send(message);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		Log.e("INFO", "onHandleIntent");
	}
	
	@Override
	public void onCreate() {
		// TODO Accion a desarrollar cuando el Servicio is created.
		Log.e("INFO", "onCreate");
		super.onCreate();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Aqui ponemos la implementación del servicio bindeado.
		Log.e("INFO", "onBind");
		return super.onBind(intent);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Este metodo es disparado cuando llamamos la al startService y nos permite devolver un valor para que el sistema sepa que hacer si el servicio es matado por el runtime.
		Log.e("INFO", "onStartCommand");
		return Service.START_STICKY;
	}
}
