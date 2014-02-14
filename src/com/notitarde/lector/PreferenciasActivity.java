package com.notitarde.lector;

import android.os.Bundle;
//import android.preference.ListPreference;
import android.preference.PreferenceActivity;

public class PreferenciasActivity extends PreferenceActivity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {				
		super.onCreate(savedInstanceState);				
		addPreferencesFromResource(R.xml.preferencias_principal);						
	}


		 	
}
