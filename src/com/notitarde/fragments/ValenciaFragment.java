package com.notitarde.fragments;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;
import com.notitarde.lector.LeerActivity;
import com.notitarde.lector.NoticiasAdapter;
import com.notitarde.lector.NoticiasXmlPullParser;
import com.notitarde.lector.R;
import android.content.Intent;
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
public class ValenciaFragment extends ListFragment {

	private NoticiasAdapter nAdapter;
	private Tracker	tracker;
	
	public ValenciaFragment() {
		// Required empty public constructor
	}

	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {		
		super.onActivityCreated(savedInstanceState);
		this.tracker = EasyTracker.getInstance(getActivity());
	}
		
	@Override
	public void onResume() {		
		super.onResume();
		this.tracker.set(Fields.SCREEN_NAME, "Valencia");
		this.tracker.send(MapBuilder.createAppView().build());
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_valencia, container,false);		
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
			try {
				nAdapter = new NoticiasAdapter(getActivity(), -1, NoticiasXmlPullParser.getNoticiasFromFile(getActivity(),Global.XML_VALENCIA));
				setListAdapter(nAdapter);
				Log.d(Global.TAG, "Fragment Valencia - Adaptador inflado OK");
			} catch (Exception e) {
				Log.e(Global.TAG, "Fragment Valencia - Error en adaptador "+e);
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


}