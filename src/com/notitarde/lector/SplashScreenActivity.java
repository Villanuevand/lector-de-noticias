package com.notitarde.lector;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;

public class SplashScreenActivity extends Activity {
	
	public static final long DELAY_SPLASH_SCREEN = 2500;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AsyncDownload ad = new AsyncDownload();
		ad.execute();
		//Estableciendo orientación
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//Elminando Barra de Titulo
		requestWindowFeature(Window.FEATURE_NO_TITLE);		
		setContentView(R.layout.activity_splash_screen);
		
	}
	
	private class AsyncDownload extends AsyncTask<Void, Void, Void>
	{

		@Override
		protected Void doInBackground(Void... arg0) {
			Downloader.DownloadAllFiles(getApplicationContext(), 0,5);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {			
			super.onPostExecute(result);
			Toast.makeText(getBaseContext(), "OnPostExecute", Toast.LENGTH_SHORT).show();
			Intent i = new Intent().setClass(SplashScreenActivity.this, MainActivity.class);
			startActivity(i);
			finish();
		}
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			Toast.makeText(getBaseContext(), "OnPreExecute", Toast.LENGTH_SHORT).show();
		}
					
	}


}
