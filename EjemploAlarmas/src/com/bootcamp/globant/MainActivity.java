package com.bootcamp.globant;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;

public class MainActivity extends Activity {

	public static final String NEW_CALL_EXAMPLE = "com.bootcamp.globant.NEW_CALL_EXAMPLE";
	private Calendar now= null;
	private TimePicker mTimePicker = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Set TimePicker to now.
		now = Calendar.getInstance();
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minutes = now.get(Calendar.MINUTE);
		mTimePicker = (TimePicker) findViewById(R.id.timePicker);
		mTimePicker.setIs24HourView(true);
		mTimePicker.setCurrentHour(hour);
		mTimePicker.setCurrentMinute(minutes);
		
		// Set Button
		Button setButton = (Button) findViewById(R.id.buttonSet);
		setButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Get TimePicker
				mTimePicker = (TimePicker) findViewById(R.id.timePicker);
				
				now = Calendar.getInstance();
				int toHour = 0;
				int toMinutes = 0;
				if (mTimePicker.getCurrentHour() > now.get(Calendar.HOUR_OF_DAY)) {
					toHour =    mTimePicker.getCurrentHour() - now.get(Calendar.HOUR_OF_DAY);
					toMinutes = mTimePicker.getCurrentMinute() - now.get(Calendar.MINUTE);
				} else {
					toHour =    now.get(Calendar.HOUR_OF_DAY) - mTimePicker.getCurrentHour();
					toMinutes = now.get(Calendar.MINUTE) - mTimePicker.getCurrentMinute();
				}
				
				Log.e("INFO", "Time Picker: "+mTimePicker.getCurrentHour()+":"+mTimePicker.getCurrentMinute());
				Log.e("INFO", "Now: "+now.get(Calendar.HOUR_OF_DAY)+":"+now.get(Calendar.MINUTE));
				Log.e("INFO", "Diferencia: "+toHour+":"+toMinutes);
				
				PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, new Intent(MainActivity.this, AlarmActivity.class), 0);
				Date date = new Date();
				long setTime = System.currentTimeMillis() + toHour * 60 * 60 * 1000 + toMinutes * 60 * 1000;
				Log.e("INFO", "Current Time: "+System.currentTimeMillis());
				Log.e("INFO", "Set Time    : "+setTime);
				date.setTime(setTime);
				// Creamos una Alarma predefinida.
				AlarmManager alarms = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
				alarms.set(AlarmManager.RTC_WAKEUP, date.getTime(), pi);
			}
		});
		
		// Cancelamos la alarma, debemos proveer el PendingIntent relacionado.
		//alarms.cancel(pi);
	}

}
