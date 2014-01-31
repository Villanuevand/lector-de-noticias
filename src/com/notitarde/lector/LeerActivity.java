package com.notitarde.lector;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LeerActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leer);
		
		ActionBar ab = getSupportActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		
		Bundle b = getIntent().getExtras();
		TextView titulo = (TextView) findViewById(R.id.tv_tituloInternaNota);
		TextView seccion = (TextView) findViewById(R.id.tv_seccionInternaNota);		
		TextView descripcion = (TextView) findViewById(R.id.tv_descripcionInternaNota);
//		String url= b.getString("url");
		titulo.setText(b.getString("titulo"));
		seccion.setText(b.getString("seccion"));		
		descripcion.setText(b.getString("descripcion"));		
		getImageToDisplay(b.getString("imagen"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.leer, menu);
		return true;
	}
	
	/**
	 * Obtiene una direccion url donde se encuentra alojada una imagen
	 * para despues mostrarla.
	 * @param url - url donde se encuentra alojada la imagen
	 */
	private void getImageToDisplay(String url) {
		
		ImageLoader imageLoader;
		Log.d("Imagen","Cargando imagen...");
		
		DisplayImageOptions opciones;		
		ImageView img = (ImageView) findViewById(R.id.iv_imagenInternaNota);
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getBaseContext()).build();
		imageLoader = ImageLoader.getInstance();
		imageLoader.destroy();
		imageLoader.init(config);
		
			opciones = new DisplayImageOptions.Builder()
				.cacheInMemory(true)
				.cacheOnDisc(true)
				.build();
			
		ImageLoadingListener listener = new ImageLoadingListener() {
			
			@Override
			public void onLoadingStarted(String arg0, View arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLoadingCancelled(String arg0, View arg1) {
				// TODO Auto-generated method stub
				
			}
						
		};
		
		imageLoader.displayImage(url, img,opciones, listener);
		
		
	}

}
