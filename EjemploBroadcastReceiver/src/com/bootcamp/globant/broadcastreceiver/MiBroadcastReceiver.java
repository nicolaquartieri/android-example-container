package com.bootcamp.globant.broadcastreceiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MiBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.e("INFO", "BR: recibo!");
		
		// Iniciar un activity
		//context.startActivity(intent);
		
		// Iniciar un servicio
//		Intent servicio = new Intent(context, Service.class);
//		context.startService(servicio);
	}

}
