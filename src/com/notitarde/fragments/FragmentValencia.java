package com.notitarde.fragments;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.notitarde.lector.Downloader;
import com.notitarde.lector.NoticiasAdapter;
import com.notitarde.lector.NoticiasXmlPullParser;
import com.notitarde.lector.R;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.notitarde.utils.DetectarConectividad;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class FragmentValencia extends ListFragment {

	private String[] sistemas = { "Android", "Ubuntu", "Mac OSX", "Windows",
            "Solaris", "Windows 8", "Ubuntu 12.04", "Windows Phone",
            "Windows 7", "Kubuntu", "Ubuntu 12.10" };
	
	static final String URL_TITULARES ="http://www.notitarde.com/valores/a/5448902/stacksites.xml";
	static final String TAG ="Debug-Notitarde";	
	
	public FragmentValencia() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_valencia, container,false);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Establecemos el Adapter a la Lista del Fragment
//        setListAdapter(new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, sistemas));
		        
		setListAdapter(new NoticiasAdapter(getActivity(), -1, NoticiasXmlPullParser.getNoticiasFromFile(getActivity())));
	}
		
	private class NoticiasDownloadTask extends  AsyncTask<Void, Void,Void>
	{
		@Override
		protected Void doInBackground(Void... params) {
			try {
				Downloader.DownloadFromUrl(URL_TITULARES, openFileOutput("StackSites.xml", Context.MODE_PRIVATE));
				
			} catch (Exception e) {
				e.printStackTrace();
				Log.e(TAG,"Error al descargar!");
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			
			super.onPostExecute(result);
		}
		
		
		
	}

	public FileOutputStream openFileOutput(String string, int modePrivate) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
