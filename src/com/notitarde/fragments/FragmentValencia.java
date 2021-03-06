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
public class FragmentValencia extends ListFragment {

	private NoticiasAdapter nAdapter;
	private Global g;

	
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
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		g = new Global(getActivity());
		Global.HAY_INTERNET = g.conexionInternet();
		if(Global.HAY_INTERNET){
			NoticiasDownloadTask dn = new NoticiasDownloadTask();
			dn.execute();			
		}else{
			nAdapter = new NoticiasAdapter(getActivity(), -1, NoticiasXmlPullParser.getNoticiasFromFile(getActivity(),Global.XML_VALENCIA));
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
				Downloader.DownloadFromUrl(Global.URL+Global.XML_VALENCIA, getActivity().openFileOutput(Global.XML_VALENCIA,Context.MODE_PRIVATE));		
			} catch (Exception e) {
				// TODO: handle exception
				Log.d(Global.TAG,"Excepcion doInBackground: " +e.toString());
						
			}
				
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {			
			nAdapter = new NoticiasAdapter(getActivity().getBaseContext(), -1, NoticiasXmlPullParser.getNoticiasFromFile(getActivity().getBaseContext(),"valencia.xml"));
			setListAdapter(nAdapter);
			Log.d("Notitarde","Metodo onPOstExecute");
			
		}				
	}
}
