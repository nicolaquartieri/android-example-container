package com.example.blureffect;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null)
			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private ArrayAdapter<String> mAdapter;
		private String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
		        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
		        "Linux", "OS/2","Android", "iPhone", "WindowsMobile",
		        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
		        "Linux", "OS/2","Android", "iPhone", "WindowsMobile",
		        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
		        "Linux", "OS/2" };
		
		private ListView mList;
		private ImageView mImage;
		private View headerView;
		
		private float alpha;
		
		private static final int TOP_HEIGHT = 200;
		
		
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			
			mImage = (ImageView) rootView.findViewById(R.id.imageView);
			
			headerView = new View( getActivity() );
			headerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, TOP_HEIGHT));
			
			mAdapter = new ArrayAdapter<String>( getActivity(), android.R.layout.simple_list_item_1, values );
			mList = (ListView) rootView.findViewById( R.id.listView );
			mList.addHeaderView(headerView);
			mList.setAdapter(mAdapter);
			
			mList.setOnScrollListener(new OnScrollListener() {
				
				@Override
				public void onScrollStateChanged(AbsListView view, int scrollState) {
					
				}
				
				@Override
				public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
					
					alpha = (float) -headerView.getTop() / (float) TOP_HEIGHT;
					// Apply a ceil
					if (alpha > 1) {
						alpha = 1;
					}
					
					mImage.setAlpha(alpha);
					mImage.setTop( mList.getTop() / 2);
				}
			});
			
			return rootView;
		}
	}

}
