package com.example.ottosimple;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class MainActivity extends ActionBarActivity {

	private static Bus bus = new Bus();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// 1st Step: Register
		bus.register(this);
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}	
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		
		public PlaceholderFragment() {
			
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			
			Button b = (Button) rootView.findViewById(R.id.button1);
			b.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// 3er Step: Publish
					bus.post(new ButtonEvent(1));
				}
			});
			
			Button b2 = (Button) rootView.findViewById(R.id.button2);
			b2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// 3er Step: Publish
					bus.post(new ButtonEvent(2));
				}
			});	
			
			return rootView;
		}
	}
	
	// 2nd Step: Handler
	@Subscribe
	public void handlerButtonPress(ButtonEvent e) {
		int id = e.getMyId();
		
		switch (id) {
		case 1:
			Toast.makeText(this, "Oh Yeah! " + id, Toast.LENGTH_SHORT).show();

			break;

		case 2:
			Toast.makeText(this, "Oh Yeah! Other Button" + id, Toast.LENGTH_SHORT).show();

			break;
		default:
			break;
		}
	}
	
	
	@Override
	protected void onStop() { 
		// Liberar recursos del activito pero NO de UI
		
		super.onStop();
	}
	
	@Override
	public void onTrimMemory(int level) { // onLowMemory()
		
		Log.d("INFO", "Nivel de memoria: " + level);
		
		switch (level) {
		case TRIM_MEMORY_UI_HIDDEN: // Liberar recursos de UI
			
			break;
		
		case TRIM_MEMORY_RUNNING_MODERATE:
			
			break;
		default:
			break;
		}
		
		super.onTrimMemory(level);
	}
}
