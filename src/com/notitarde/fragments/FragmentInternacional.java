package com.notitarde.fragments;

//import com.notitarde.lector.Downloader;
import com.notitarde.lector.LeerActivity;
import com.notitarde.lector.NoticiasAdapter;
import com.notitarde.lector.NoticiasXmlPullParser;
import com.notitarde.lector.R;

//import android.content.Context;
import android.content.Intent;
//import android.os.AsyncTask;
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
public class FragmentInternacional extends ListFragment {
	
	NoticiasAdapter nAdapter;
	Global g;
	
	public FragmentInternacional() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_internacional,
				container, false);
	}
	
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
			try {
				nAdapter = new NoticiasAdapter(getActivity(), -1, NoticiasXmlPullParser.getNoticiasFromFile(getActivity().getBaseContext(),Global.XML_INTERNACIONAL));
				setListAdapter(nAdapter);
				Log.d(Global.TAG,"Fragment Internacional - Adaptador inflado OK");
			} catch (Exception e) {
				Log.e(Global.TAG,"Fragment Internacional - Error en adaptador "+e);
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

//	private class NoticiasDownloadTask extends AsyncTask<Void, Void, Void>
//	{
//		@Override
//		protected Void doInBackground(Void... params) {
//			try {
//				Log.d(Global.TAG," Metodo doInBackgroud");
//				Downloader.DownloadFromUrl(Global.URL+Global.XML_INTERNACIONAL, getActivity().openFileOutput(Global.XML_INTERNACIONAL, Context.MODE_PRIVATE));		
//			} catch (Exception e) {
//				Log.d(Global.TAG,"Excepcion doInBackground: " +e.toString());					
//			}				
//			return null;
//		}
//
//		@Override
//		protected void onPostExecute(Void result) {			
//			nAdapter = new NoticiasAdapter(getActivity().getBaseContext(), -1, NoticiasXmlPullParser.getNoticiasFromFile(getActivity().getBaseContext(),Global.XML_INTERNACIONAL));
//			setListAdapter(nAdapter);
//			Log.d("Notitarde","Metodo onPOstExecute");
//			
//		}
//				
//	}

}
