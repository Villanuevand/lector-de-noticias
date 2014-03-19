package com.notitarde.lector;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DialogRefreshFragment extends DialogFragment {

	FragmentComunicator fc;
	
	
	@Override
	public void onAttach(Activity activity) {	
		super.onAttach(activity);
		fc = (FragmentComunicator) activity;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {	
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.dialog_refresh_titulo);
		//builder.setMessage(R.string.dialog_refresh_mensaje);
		builder.setPositiveButton(R.string.dialog_refresh_positive_button_titulo, new DialogInterface.OnClickListener() {				
				@Override
				public void onClick(DialogInterface dialog, int which) {				
					fc.updateAll(true);
				}
			});
		
		builder.setNegativeButton(R.string.dialog_refresh_negative_button_titulo, new DialogInterface.OnClickListener() {		
			@Override
			public void onClick(DialogInterface dialog, int which) {			
				dialog.cancel();
			}
		});
		
		Dialog dialog = builder.create();
		return dialog;
	}
	
	interface FragmentComunicator{
		public boolean updateAll(boolean flag);
	}

	
}
