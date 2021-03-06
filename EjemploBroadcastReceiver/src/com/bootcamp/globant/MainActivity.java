package com.bootcamp.globant;

import com.bootcamp.globant.broadcastreceiver.MiBroadcastReceiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private MiBroadcastReceiver br = null;
	
	public static final String NEW_CALL_EXAMPLE = "com.bootcamp.globant.NEW_CALL_EXAMPLE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button boton = (Button) findViewById(R.id.button);
		boton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 1er Enviar el Intent Broadcast
				Intent intent = new Intent(NEW_CALL_EXAMPLE);
				intent.putExtra("mensaje", "Llamada de ejemplo.");
				sendBroadcast(intent);
			}
		});
	}
	
	@Override
	protected void onResume() {
		// Sin el registro del manifest, para este caso tenemos que borrar lo registrado en el manifest.
//		IntentFilter intent = new IntentFilter(NEW_CALL_EXAMPLE);
//		br = new MiBroadcastReceiver();
//		registerReceiver(br, intent);
		super.onResume();
	}
	
	@Override
	protected void onPause() {
//		unregisterReceiver(br);
		super.onPause();
	}
}
