package com.notitarde.lector;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DialogRefreshAlertFragment extends DialogFragment{

	FragmentCommunicator fc;
	
	@Override
	public void onAttach(Activity activity) {		
		super.onAttach(activity);
		fc = (FragmentCommunicator) activity;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.dialog_alert_descarga_inicial_titulo);
		builder.setMessage(R.string.error_descarga_inicial);
		builder.setPositiveButton(R.string.dialog_refresh_positive_button_titulo, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {				
				fc.refresh(true);
			}
		});
		Dialog d = builder.create();
		return d;
	}

	interface FragmentCommunicator{
		public boolean refresh(boolean flag);		
	}

	
}
