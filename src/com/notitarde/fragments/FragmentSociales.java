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

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class FragmentSociales extends ListFragment {

	NoticiasAdapter nAdapter;
	Global g;
	
	public FragmentSociales() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_sociales, container,
				false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {		
		super.onActivityCreated(savedInstanceState);
		g = new Global(getActivity());
		Global.HAY_INTERNET = g.conexionInternet();
		Log.d(Global.TAG + " - Hay Internet", Global.HAY_INTERNET.toString());
		if(Global.HAY_INTERNET){			
			NoticiasDownloadTask dn = new NoticiasDownloadTask();
			dn.execute();
		}else{
			nAdapter = new NoticiasAdapter(getActivity(), -1, NoticiasXmlPullParser.getNoticiasFromFile(getActivity().getBaseContext(),Global.XML_SOCIALES));
		}
				
		
	}
	

	private class NoticiasDownloadTask extends AsyncTask<Void, Void, Void>
	{
		@Override
		protected Void doInBackground(Void... params) {
			try {
				Log.d(Global.TAG," Metodo doInBackgroud");
				Downloader.DownloadFromUrl(Global.URL+Global.XML_SOCIALES, getActivity().openFileOutput(Global.XML_SOCIALES, Context.MODE_PRIVATE));		
			} catch (Exception e) {
				Log.d(Global.TAG,"Excepcion doInBackground: " +e.toString());					
			}				
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {			
			nAdapter = new NoticiasAdapter(getActivity().getBaseContext(), -1, NoticiasXmlPullParser.getNoticiasFromFile(getActivity().getBaseContext(),Global.XML_SOCIALES));
			setListAdapter(nAdapter);
			Log.d("Notitarde","Metodo onPOstExecute");
			
		}		
		
	}
}
