package com.notitarde.lector;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;


public class DialogFuente extends DialogFragment {

	FragmentComunicador fc;		
	
	@Override
	public void onAttach(Activity activity) {	
		super.onAttach(activity);		
		fc = (FragmentComunicador) activity;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
			
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());					
		int opc = Integer.parseInt(sharedPref.getString("PREF_FUENTE", "0"));
		sharedPref = getActivity().getSharedPreferences("com.notitarde.lector_preferences", Context.MODE_PRIVATE);
		final SharedPreferences.Editor editor  = sharedPref.edit();
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Tamaño de Letra");			
		builder.setSingleChoiceItems(R.array.tamanofuente, opc, new DialogInterface.OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int value) {
				switch (value) {
				case 0:	
					editor.putString("PREF_FUENTE", Integer.toString(value));
					editor.commit();
					fc.tamanoFuente(value);					
					break;
				case 1:
					editor.putString("PREF_FUENTE", Integer.toString(value));
					editor.commit();
					fc.tamanoFuente(value);					
					break;
				case 2:
					editor.putString("PREF_FUENTE",Integer.toString(value));
					editor.commit();
					fc.tamanoFuente(value);					
					break;
				case 3:
					editor.putString("PREF_FUENTE",Integer.toString(value));
					editor.commit();
					fc.tamanoFuente(value);				
					break;
				default:
					break;
				}
				dismiss();
			}
		});
		Dialog dialog = builder.create();				
		return dialog;
	}

	interface FragmentComunicador{
	
		public void tamanoFuente(int tamano);		
	}
	
}
