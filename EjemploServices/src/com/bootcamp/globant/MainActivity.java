package com.bootcamp.globant;

import com.bootcamp.globant.service.DownloadService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
	}
	
	public void onClick(View view) {
		Intent serviceOne = new Intent(this, DownloadService.class);
		Intent serviceTwo = new Intent(this, DownloadService.class);
		
		Messenger messenger = new Messenger(target);
		serviceOne.putExtra("MESSENGER", messenger);
		serviceOne.setData(Uri.parse("http://www.vogella.com/index.html"));
		serviceOne.putExtra("urlpath", "http://www.vogella.com/index.html");
		
		// Dos formas de iniciar servicios.
		startService(serviceOne);
		bindService(serviceTwo, mServiceConex, Context.BIND_AUTO_CREATE);
	}
	
	private ServiceConnection mServiceConex = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			
		}
	};
	
	private Handler target = new Handler() {
		
		@Override
		public void handleMessage(Message msg) {
			try {
				if (msg.arg1 == Activity.RESULT_OK) {
					Thread.currentThread();
					Thread.sleep(100);
				
					Toast.makeText(MainActivity.this, "Done!", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
}
