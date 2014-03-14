package com.notitarde.lector;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.notitarde.fragments.Global;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class LeerActivity extends ActionBarActivity implements DialogFuenteFragment.FragmentComunicador{

	private TextView titulo;
	private TextView seccion;		
	private TextView descripcion;
	Bundle b;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leer);	
		titulo = (TextView) findViewById(R.id.tv_tituloInternaNota);
		seccion = (TextView) findViewById(R.id.tv_seccionInternaNota);	
		descripcion = (TextView) findViewById(R.id.tv_descripcionInternaNota);
		b = getIntent().getExtras();		
		//String url= b.getString("url");
		setTamanoFuente();
		titulo.setText(b.getString("titulo"));
		seccion.setText(b.getString("seccion"));		
		descripcion.setText(b.getString("descripcion"));		
		getImageToDisplay(b.getString("imagen"));			
		ActionBar ab = getSupportActionBar();		
		ab.setTitle(R.string.app_name);					
		ab.setSubtitle(b.getString("seccion"));	
	}	



	private void setTamanoFuente() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		int opc = Integer.parseInt(sp.getString("PREF_FUENTE", "0"));
		switch (opc) {
		case 0:
			tamanoNormal();
		break;
		case 1:				
			tamanoMediano();
			break;
		case 2:
			tamanoGrande();
			break;
		case 3:
			tamanoMuyGrande();
			break;
		default:
			break;
		}
	}

	private void tamanoNormal(){
		titulo.setTextSize(getResources().getDimension(R.dimen.texto_normal_titulo));
		seccion.setTextSize(getResources().getDimension(R.dimen.texto_normal_seccion));
		descripcion.setTextSize(getResources().getDimension(R.dimen.texto_normal_descripcion));
	}
	private void tamanoMediano(){
		titulo.setTextSize(getResources().getDimension(R.dimen.texto_mediano_titulo));
		seccion.setTextSize(getResources().getDimension(R.dimen.texto_mediano_seccion));
		descripcion.setTextSize(getResources().getDimension(R.dimen.texto_mediano_descripcion));
	}
	private void tamanoGrande(){
		titulo.setTextSize(getResources().getDimension(R.dimen.texto_grande_titulo));
		seccion.setTextSize(getResources().getDimension(R.dimen.texto_grande_seccion));
		descripcion.setTextSize(getResources().getDimension(R.dimen.texto_grande_descripcion));
	}
	private void tamanoMuyGrande(){	
		titulo.setTextSize(getResources().getDimension(R.dimen.texto_muygrande_titulo));
		seccion.setTextSize(getResources().getDimension(R.dimen.texto_muygrande_seccion));
		descripcion.setTextSize(getResources().getDimension(R.dimen.texto_muygrande_descripcion));
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.leer, menu);	
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {		
		case R.id.action_Notashare:
			mostrarOpcionShare();			
			return true;
		case R.id.action_Notafuente:
			mostrarDialog();
			return true;
		case R.id.action_Notaemail:
			mostarOpcionMail();		
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}	
	}


	private void mostarOpcionMail() {		
		Intent email = new Intent();
		email.putExtra(Intent.EXTRA_SUBJECT,b.getString("titulo"));
		email.putExtra(Intent.EXTRA_TEXT,getResources().getString(R.string.email_mensaje)+"\n"+b.getString("titulo")+"\n"+b.getString("url")+" vía Notitarde @"+getResources().getString(R.string.redes_sociales_twitter));
		email.setType("message/rfc822");
		startActivity(Intent.createChooser(email, getResources().getString(R.string.email_titulo_dialog)));			
	}

	private void mostrarOpcionShare() {
		Intent i = new Intent();
		i.setAction(Intent.ACTION_SEND);
		i.putExtra(Intent.EXTRA_TEXT,b.getString("titulo")+"\n"+b.getString("url")+" vía @"+getResources().getString(R.string.redes_sociales_twitter));
		i.setType("text/plain");
		startActivity(Intent.createChooser(i, getResources().getText(R.string.action_share)));		
	}

	public void mostrarDialog(){				
		DialogFuenteFragment dialogFuente = new DialogFuenteFragment();			
		dialogFuente.show(getSupportFragmentManager(),Global.TAG);		
	}
	
	@Override
	public void tamanoFuente(int tamano) {
		try {
			switch (tamano) {
			case 0:
				tamanoNormal();
				break;
			case 1:
				tamanoMediano();
				break;
			case 2:
				tamanoGrande();
				break;
			case 3:
				tamanoMuyGrande();
				break;
			default:
				break;
			}

		} catch (Exception e) {
			Toast.makeText(getBaseContext(), R.string.error_fuente_tamano, Toast.LENGTH_SHORT).show();
		}	
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
