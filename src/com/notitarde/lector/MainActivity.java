package com.notitarde.lector;


import com.notitarde.fragments.FragmentDeportes;
import com.notitarde.fragments.FragmentEconomia;
import com.notitarde.fragments.FragmentEspectaculos;
import com.notitarde.fragments.FragmentInstitucionales;
import com.notitarde.fragments.FragmentInternacional;
import com.notitarde.fragments.FragmentLaCosta;
import com.notitarde.fragments.FragmentPais;
import com.notitarde.fragments.FragmentRegiones;
import com.notitarde.fragments.FragmentSociales;
import com.notitarde.fragments.FragmentSucesos;
import com.notitarde.fragments.FragmentTitulares;
import com.notitarde.fragments.FragmentValencia;

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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
//import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener, OnPageChangeListener{

	private ViewPager mViewPager;	
	NoticiasAdapter nAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		AsyncDownload ad = new AsyncDownload();
		ad.execute();
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
			Log.d("Tabs","Creado "+s);
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
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
			actualizarXml();
			return true;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	//Inflador Opciones de Menú
	private void InflarMenuOpciones() {
		startActivity(new Intent(this,PreferenciasActivity.class));		
	}
	
	//Actualizar Archivos XML	
	private void actualizarXml(){
		Toast.makeText(getBaseContext(), "Actualizando", Toast.LENGTH_LONG).show();
		AsyncDownload ad = new AsyncDownload();
		ad.execute();
	}

	//	Adpatador de Páginas 
	public class PagerAdapter extends FragmentPagerAdapter {

		public PagerAdapter(FragmentManager fm) {
			super(fm);
		}		
		public Fragment getItem(int arg0) {
			switch (arg0) {
	            case 0: 
	                return new FragmentTitulares();
	            case 1:
	            	return new FragmentValencia();	                
	            case 2:
	            	return new FragmentPais() ;	            	
	            case 3:
	            	return new FragmentEconomia();
	            case 4:
	            	return new FragmentInternacional();
	            case 5:
	            	return new FragmentDeportes();
	            case 6:
	            	return new FragmentSucesos();
	            case 7:
	            	return new FragmentLaCosta();
	            case 8:
	            	return new FragmentRegiones();
	            case 9:
	            	return new FragmentEspectaculos();
	            case 10:
	            	return new FragmentInstitucionales();
	            case 11:
	            	return new FragmentSociales();	            
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
	
private class AsyncDownload extends AsyncTask<Void, Void, Void>{

	@Override
	protected Void doInBackground(Void... params) {
		Downloader.DownloadAllFiles(getApplicationContext(),5,11);
		return null;
	}
	
}

}
