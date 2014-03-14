package com.notitarde.fragments;

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
public class SocialesFragment extends ListFragment {

	NoticiasAdapter nAdapter;
	
	public SocialesFragment() {
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
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		try {
			nAdapter = new NoticiasAdapter(getActivity(), -1, NoticiasXmlPullParser.getNoticiasFromFile(getActivity().getBaseContext(),Global.XML_SOCIALES));
			setListAdapter(nAdapter);
			Log.d(Global.TAG,"Fragment Sociales - Adaptador inflado OK");
		} catch (Exception e) {
			Log.e(Global.TAG,"Fragment Sociales - Error al inflar adaptador "+e);
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
