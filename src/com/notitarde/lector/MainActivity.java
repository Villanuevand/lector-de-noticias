package com.notitarde.lector;


import com.notitarde.fragments.DeportesFragment;
import com.notitarde.fragments.EconomiaFragment;
import com.notitarde.fragments.EspectaculosFragment;
import com.notitarde.fragments.InstitucionalesFragment;
import com.notitarde.fragments.InternacionalFragment;
import com.notitarde.fragments.LaCostaFragment;
import com.notitarde.fragments.PaisFragment;
import com.notitarde.fragments.RegionesFragment;
import com.notitarde.fragments.SocialesFragment;
import com.notitarde.fragments.SucesosFragment;
import com.notitarde.fragments.TitularesFragment;
import com.notitarde.fragments.ValenciaFragment;
import com.notitarde.fragments.Global;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener, OnPageChangeListener, DialogRefreshFragment.FragmentComunicator{

	private ViewPager mViewPager;	
	private long lastPressedTime;
	NoticiasAdapter nAdapter;
	Global g;
	Bundle b;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		b = getIntent().getExtras();
		boolean ofs = b.getBoolean("okFromSplashScreen");
		boolean efs = b.getBoolean("exceptionFromSplashScreen");
		Log.d(Global.TAG, "okFromSplash "+String.valueOf(b.getBoolean("okFromSplashScreen")));
		Log.d(Global.TAG, "exceptionFromSplash "+String.valueOf(b.getBoolean("exceptionFromSplashScreen")));
		if(ofs){			
			AsyncPostDownload apd = new AsyncPostDownload();
			apd.execute();			
		}else{
			if(efs){
				DownloadAllXML dax = new DownloadAllXML();
				dax.execute();
			}
				
		}			
 		setContentView(R.layout.swipe_tab);				
		PagerAdapter pAdapter = new PagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(pAdapter);
		mViewPager.setOnPageChangeListener(this);
		
		ActionBar ab = getSupportActionBar();
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		
		String[] arraySec;
		arraySec = getResources().getStringArray(R.array.secciones);
		for (int i = 0; i < arraySec.length; i++) {			
			String s = arraySec[i];
			Tab t = ab.newTab()
					.setText(s)
					.setTabListener(this);
			ab.addTab(t);
			Log.d(Global.TAG,"Tab "+s+" creado");
		}		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {	
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
		
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {		
		case R.id.action_mainSettings:
			InflarMenuOpciones();
			return true;
		case R.id.action_refresh:
			mostrarDialog();			
			return true;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	//Inflar Dialog Refresh
	private void mostrarDialog() {
		DialogRefreshFragment dialogRefresh = new DialogRefreshFragment();
		dialogRefresh.show(getSupportFragmentManager(), Global.TAG);
		
	}
	
	
	//Que tecla física se ha presionado
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
	        switch (event.getAction()) {
	        case KeyEvent.ACTION_DOWN:
	            if (event.getDownTime() - lastPressedTime < Global.PRESSURE_PERIOD) {
	                finish();
	            } else {
	                Toast.makeText(getApplicationContext(), R.string.toast_exit_menssage,Toast.LENGTH_SHORT).show();
	                lastPressedTime = event.getEventTime();
	            }
	            return true;	      
	        }	        	        
	    }
	    if(event.getKeyCode() == KeyEvent.KEYCODE_MENU)
	    {
	    	InflarMenuOpciones();
	    }
	    return false;
	}
	
	@Override
	public boolean updateAll(boolean flag) {
		if(flag){
			actualizarXml();
		}
		return false;
	}
	
	//Inflar Opciones de Menú
	private void InflarMenuOpciones() {
		startActivity(new Intent(this,PreferenciasActivity.class));		
	}
	
	//Actualizar Archivos XML	
	private void actualizarXml(){
		g = new Global(getBaseContext());
		if(g.conexionInternet()){
			DownloadAllXML dax = new DownloadAllXML();
			dax.execute();								
		}
		else{
			Toast.makeText(getBaseContext(), R.string.error_conexion_internet, Toast.LENGTH_SHORT).show();
		}

	}

	//	Adpatador de Páginas 
	public class PagerAdapter extends FragmentPagerAdapter {

		public PagerAdapter(FragmentManager fm) {
			super(fm);
		}		
		public Fragment getItem(int arg0) {
			switch (arg0) {
	            case 0: 
	                return new TitularesFragment();
	            case 1:
	            	return new ValenciaFragment();	                
	            case 2:
	            	return new PaisFragment() ;	            	
	            case 3:
	            	return new EconomiaFragment();
	            case 4:
	            	return new InternacionalFragment();
	            case 5:
	            	return new DeportesFragment();
	            case 6:
	            	return new SucesosFragment();
	            case 7:
	            	return new LaCostaFragment();
	            case 8:
	            	return new RegionesFragment();
	            case 9:
	            	return new EspectaculosFragment();
	            case 10:
	            	return new InstitucionalesFragment();
	            case 11:
	            	return new SocialesFragment();	            
	            default:
	            	return null;
			}
		}
		public int getCount() {
			return 12;
		}

	}

//	*** Métodos de Páginas ***
	@Override
	public void onPageScrollStateChanged(int arg0) {		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int arg0) {
		getSupportActionBar().setSelectedNavigationItem(arg0);
	}
	
// *** Métodos de Tabs ***
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {		
		
	}
	

	//	AsyncTask para completar la carga de secciones
	private class AsyncPostDownload extends AsyncTask<Void, Void, Void>{		
		
		@Override
		protected Void doInBackground(Void... params) {
			Downloader.DownloadAllFiles(getApplicationContext(),6,12);
			return null;
		}
	}
	
	//	AsyncTask actualizacion completa, todos los archivos XML
	private class DownloadAllXML extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			Downloader.DownloadAllFiles(getApplicationContext(),0,12);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Toast t = Toast.makeText(getBaseContext(), R.string.toast_actualizacion_completa, Toast.LENGTH_LONG);			
			t.show();
		}
					
	}

	
	

}
