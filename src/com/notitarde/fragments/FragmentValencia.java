package com.notitarde.fragments;


import com.notitarde.lector.Downloader;
import com.notitarde.lector.NoticiasAdapter;
import com.notitarde.lector.NoticiasXmlPullParser;
import com.notitarde.lector.R;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ArrayAdapter;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class FragmentValencia extends ListFragment {

	final static String URL ="https://googledrive.com/host/0ByoQ8u8IrvxGcVdVbXgzbVNMTFk/noticias.xml";
	NoticiasAdapter nAdapter;
	
//	private String[] sistemas = { "Android", "Ubuntu", "Mac OSX", "Windows",
//            "Solaris", "Windows 8", "Ubuntu 12.04", "Windows Phone",
//            "Windows 7", "Kubuntu", "Ubuntu 12.10" };
//	
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
		//setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, sistemas));
		try {
			Log.d("Notitarde"," OnCreate");
			NoticiasDownloadTask dn =  new NoticiasDownloadTask();
			Log.d("Notitarde","Descarga inicializada");
			dn.execute();
			Log.d("Notitarde","Descarga ejecutada");
			nAdapter = new NoticiasAdapter(getActivity(), -1, NoticiasXmlPullParser.getNoticiasFromFile(getActivity().getBaseContext()));
			Log.d("Notitarde","llenado list");
			setListAdapter(nAdapter);
			Log.d("Notitarde","List llenado");
		} catch (Exception e) {
			Log.d("Notitarde","Excepcion onCreate: " +e.toString());
		}
		
		
		
		   
		//setListAdapter(new NoticiasAdapter(getActivity(), -1, NoticiasXmlPullParser.getNoticiasFromFile(getActivity())));
	}

	private class NoticiasDownloadTask extends AsyncTask<Void, Void, Void>
	{

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Log.d("Notitarde"," metodo doInBackgroud");
				Downloader.DownloadFromUrl(URL, getActivity().openFileOutput("Noticias.xml", Context.MODE_PRIVATE));		
			} catch (Exception e) {
				// TODO: handle exception
				Log.d("Notitarde","Excepcion doInBackground: " +e.toString());
						
			}
				
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {			
			nAdapter = new NoticiasAdapter(getActivity().getBaseContext(), -1, NoticiasXmlPullParser.getNoticiasFromFile(getActivity().getBaseContext()));
			setListAdapter(nAdapter);
			Log.d("Notitarde","Metodo onPOstExecute");
			
		}
		
		
		
	}
}
