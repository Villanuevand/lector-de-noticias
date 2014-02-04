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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LeerActivity extends ActionBarActivity implements DialogFuente.FragmentComunicador{

	TextView titulo;
	TextView seccion;		
	TextView descripcion;
	Bundle b;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leer);	
		titulo = (TextView) findViewById(R.id.tv_tituloInternaNota);
		seccion = (TextView) findViewById(R.id.tv_seccionInternaNota);	
		descripcion = (TextView) findViewById(R.id.tv_descripcionInternaNota);
		b = getIntent().getExtras();		
//		String url= b.getString("url");
		titulo.setText(b.getString("titulo"));
		seccion.setText(b.getString("seccion"));		
		descripcion.setText(b.getString("descripcion"));		
		getImageToDisplay(b.getString("imagen"));
		
		
		ActionBar ab = getSupportActionBar();
		ab.setTitle(R.string.app_name);					
		ab.setSubtitle(b.getString("seccion"));
		ab.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.leer, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_Notasettings:
			Toast.makeText(this, "menu", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_Notashare:
			Toast.makeText(this, "fuente", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_Notafuente:
			mostrarDialog();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	
	public void mostrarDialog(){
		
		DialogFuente dialogFuente = new DialogFuente();
		dialogFuente.show(getSupportFragmentManager(),"Mi dialog de fuente");
		
	}
	
	@Override
	public void tamanoFuente(int tamano) {	
		descripcion.setTextSize((float)tamano);		
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
			}
			@Override
			public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
			}			
			@Override
			public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
			}			
			@Override
			public void onLoadingCancelled(String arg0, View arg1) {
			}				
		};		
		imageLoader.displayImage(url, img,opciones, listener);			
	}

}
