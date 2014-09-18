package com.example.ejemplocontentprovider;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		getProviders();
	}
	
	private void getProviders() {
		for (PackageInfo pack : getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS)) {
	        ProviderInfo[] providers = pack.providers;
	        if (providers != null) {
	            for (ProviderInfo provider : providers) {
	                Log.i("Example", "Provider: " + provider.authority);
	            }
	        }
	    }
	}

	public static class PlaceholderFragment extends Fragment {

		ListView lv;
		SimpleCursorAdapter mAdapter;
		
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			
			// COLUMSN TO RETRIEVE FROM TE CONTENT PROVIDER
			String[] columns = new String[] {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME};
			// GET DATA FROM THE CONTAINER
			Cursor c = getActivity().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, 
																columns, 
																null, null, null);
			// UI COMPONENT TO RECEIVE DE DATA
			int[] to = new int[]{ android.R.id.text1 };
			// COLUMNS FROM DE CURSOR TO RELATE WITH DE UI COMPONENT
			String[] mycolumns = new String[] { ContactsContract.Contacts.DISPLAY_NAME};
			
			mAdapter = new SimpleCursorAdapter(rootView.getContext(), 
											   android.R.layout.simple_list_item_1,
											   c,
											   mycolumns,
											   to, 
											   0);
			
			ListView lv = (ListView) rootView.findViewById(R.id.listView);
			lv.setAdapter(mAdapter);
			
			return rootView;
		}
		
	}

}
