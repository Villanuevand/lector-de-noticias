package com.notitarde.fragments;


import com.notitarde.lector.Downloader;
import com.notitarde.lector.LeerActivity;
import com.notitarde.lector.NoticiasAdapter;
import com.notitarde.lector.NoticiasXmlPullParser;
import com.notitarde.lector.R;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class FragmentPais extends ListFragment {

	NoticiasAdapter nAdapter;
	Global g;

	
	public FragmentPais() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_pais, container,
				false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
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
			nAdapter = new NoticiasAdapter(getActivity(), -1, NoticiasXmlPullParser.getNoticiasFromFile(getActivity().getBaseContext(),Global.XML_PAIS));
		}
				
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(getActivity().getApplicationContext(),LeerActivity.class);
		i.putExtra("titulo", nAdapter.getItem(position).getTitulo());
		i.putExtra("seccion",nAdapter.getItem(position).getSeccion());
		i.putExtra("imagen", nAdapter.getItem(position).getImgUrl());
		i.putExtra("descripcion", nAdapter.getItem(position).getDescripcion());	
		i.putExtra("fecha",nAdapter.getItem(position).getFecha());
		i.putExtra("url",nAdapter.getItem(position).getUrl());
		startActivity(i);
		
	}

	private class NoticiasDownloadTask extends AsyncTask<Void, Void, Void>
	{
		@Override
		protected Void doInBackground(Void... params) {
			try {
				Log.d(Global.TAG," Metodo doInBackgroud");
				Downloader.DownloadFromUrl(Global.URL+Global.XML_PAIS, getActivity().openFileOutput(Global.XML_PAIS, Context.MODE_PRIVATE));		
			} catch (Exception e) {
				Log.d(Global.TAG,"Excepcion doInBackground: " +e.toString());					
			}				
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {			
			nAdapter = new NoticiasAdapter(getActivity().getBaseContext(), -1, NoticiasXmlPullParser.getNoticiasFromFile(getActivity().getBaseContext(),Global.XML_PAIS));
			setListAdapter(nAdapter);
			Log.d("Notitarde","Metodo onPOstExecute");
			
		}
		
		
		
	}

}
