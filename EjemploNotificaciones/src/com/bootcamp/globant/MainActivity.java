package com.bootcamp.globant;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private NotificationManager nm;
	private Notification n;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button b = (Button) findViewById(R.id.button);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Obtenemos le notification manager.
				nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				
				// Primer Forma
				// Creando la notificacion
//				int icon = R.drawable.ic_launcher;
//				String text = "Atenci�n Ejemplo!";
//				long tiempo = System.currentTimeMillis();
				
//				n = new Notification(icon, text, tiempo);
//				Intent intent = new Intent(MainActivity.this, MainActivity.class);
//				PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
//				n.setLatestEventInfo(getApplicationContext(), "Titulo", "Texto", pi);
//				
//				nm.notify(0, n);
				
				// Segunda forma:
				
				Intent intent = new Intent(MainActivity.this, MainActivity.class);
				PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
				
				Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
				
				NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this)
						.setSmallIcon(R.drawable.ic_launcher)
						.setContentTitle("Titulo")
						.setContentText("Texto")
						.setSound(alarmSound)
						.setContentIntent(pi);
				
				nm.notify(0, builder.build());
			}
		});
	}

}
