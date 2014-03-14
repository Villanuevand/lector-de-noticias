package com.notitarde.lector;


import com.notitarde.fragments.Global;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;

public class SplashScreenActivity extends Activity {	
		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(Global.TAG,"***** Iniciando Aplicación! =) *****");
		//Log.d(Global.TAG,"Conexión a Internet: " + g.conexionInternet());
		//Estableciendo orientación
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//Elminando Barra de Titulo
		requestWindowFeature(Window.FEATURE_NO_TITLE);		
		setContentView(R.layout.activity_splash_screen);		
		try {
			initDownload id = new initDownload();
			id.execute();
		} catch (Exception e) {			
			Intent i = new Intent().setClass(SplashScreenActivity.this, MainActivity.class);
			i.putExtra("exceptionFromSplashScreen", true);
			startActivity(i);
			finish();
			Log.e(Global.TAG,"Splash Activity - Error en conexion CATCH "+ e);
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


