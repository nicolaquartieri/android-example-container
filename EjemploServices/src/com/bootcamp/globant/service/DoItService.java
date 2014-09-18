package com.bootcamp.globant.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class DoItService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		Log.e("INFO", "onCreate");
		
		super.onCreate();
	}
	
	
}
