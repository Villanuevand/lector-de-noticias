package com.notitarde.lector;


import com.notitarde.fragments.Global;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;

public class SplashScreenActivity extends Activity {	
	
	Global g;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		g = new Global(getBaseContext());
		
		Log.i(Global.TAG,"***** Iniciando Aplicación! =) *****");
		Log.d(Global.TAG,"Conexión a Internet: " + g.conexionInternet());
		//Estableciendo orientación
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//Elminando Barra de Titulo
		requestWindowFeature(Window.FEATURE_NO_TITLE);		
		setContentView(R.layout.activity_splash_screen);		
		try {
			if(g.conexionInternet()){
				initDownload id = new initDownload();
				id.execute();
			}			
		} catch (Exception e) {
			Log.e(Global.TAG,"Splash Activity - Error en conexion CATCH "+ e);
			Toast.makeText(getBaseContext(), R.string.error_conexion_internet, Toast.LENGTH_LONG).show();
			Intent i = new Intent().setClass(SplashScreenActivity.this, MainActivity.class);
			i.putExtra("exceptionFromSplashScreen", true);
			startActivity(i);
								
		}			
	}
	
	
	class initDownload extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			Downloader.DownloadAllFiles(getBaseContext(), 0,6);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {			
			super.onPostExecute(result);
			Intent i = new Intent().setClass(SplashScreenActivity.this, MainActivity.class);
			i.putExtra("okFromSplashScreen", true);
			startActivity(i);
			finish();	
		}				
	}
					
}


