package com.notitarde.lector;


import com.notitarde.fragments.FragmentEconomia;
import com.notitarde.fragments.FragmentPais;
import com.notitarde.fragments.FragmentValencia;
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

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener, OnPageChangeListener {

	private ViewPager mViewPager;
	static final String TAG ="Debug-Notitarde";	
	
	NoticiasAdapter nAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
//	Adpatador de Páginas 
	public class PagerAdapter extends FragmentPagerAdapter {

		public PagerAdapter(FragmentManager fm) {
			super(fm);
		}		
		public Fragment getItem(int arg0) {
			switch (arg0) {
	            case 0: 
	                return new FragmentValencia();
	            case 1:
	                return new FragmentPais() ;
	            case 2:
	            	return new FragmentEconomia();
	            default:
	            	return null;
			}
		}
		public int getCount() {
			return 3;
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
	


}
