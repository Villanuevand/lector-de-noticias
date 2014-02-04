package com.notitarde.lector;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
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
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Tamaño de Letra");		
		builder.setSingleChoiceItems(R.array.tamanofuente, -1, new DialogInterface.OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:					
					fc.tamanoFuente(14);
					break;
				case 1:
					fc.tamanoFuente(16);
					break;
				case 2:
					fc.tamanoFuente(20);
					break;
				case 3:
					fc.tamanoFuente(22);
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
