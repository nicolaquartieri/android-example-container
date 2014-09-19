package com.bootcamp.globant;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

public class AlarmActivity extends Activity {
	
	private NotificationManager nm = null;
	private PendingIntent pi = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm_activity);
		
		pi = (PendingIntent) getLastNonConfigurationInstance();
		
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		Intent intent = new Intent(AlarmActivity.this, MainActivity.class);
		pi = PendingIntent.getActivity(AlarmActivity.this, 0, intent, 0);
		
		Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		
		NotificationCompat.Builder builder = new NotificationCompat.Builder(AlarmActivity.this)
							.setSmallIcon(R.drawable.ic_launcher)
							.setContentTitle("Alarma")
							.setContentText("Tiempo!")
							.setSound(alarmSound)
							.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_alarm))
							.setSmallIcon(R.drawable.ic_alarm)
							.setContentIntent(pi);
		
		Notification n = builder.build();
		n.flags = n.flags | Notification.FLAG_INSISTENT;
				
		nm.notify(0, n);
	}
	
	@Override
	public Object onRetainNonConfigurationInstance() {	
		return pi;
	}
	
	public void backToMain(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
