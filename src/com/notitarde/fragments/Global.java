package com.notitarde.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Global {

	public static final String TAG = "Notitarde";	
	public static final String URL = "http://webservice.notitarde.com/Site/Binary/";
	public static final String XML_TITULARES = "xml.aspx";
	public static final String XML_VALENCIA = "xml.aspx?categoria=1";
	public static final String XML_PAIS = "xml.aspx?categoria=2";
	public static final String XML_ECONOMIA = "xml.aspx?categoria=3";
	public static final String XML_INTERNACIONAL = "xml.aspx?categoria=4";
	public static final String XML_DEPORTES ="xml.aspx?categoria=5";
	public static final String XML_SUCESOS = "xml.aspx?categoria=6";
	public static final String XML_LACOSTA = "xml.aspx?categoria=7";
	public static final String XML_REGIONES = "xml.aspx?categoria=8";
	public static final String XML_ESPECTACULOS = "xml.aspx?categoria=9";
	public static final String XML_INSTITUCIONALES = "xml.aspx?categoria=10";
	public static final String XML_SOCIALES = "xml.aspx?categoria=11";	
	public static final int PRESSURE_PERIOD= 2000;
	public static final long SPLASH_SCREEN_DELAY = 4000;	
	public final boolean HAY_INTERNET = false;
	public static boolean FLAG = false;
	
	private  Context _ctx;
			
	public Global(Context _context) {		
		this._ctx = _context;
	}

	public  boolean conexionInternet(){
		ConnectivityManager conectividad = (ConnectivityManager) _ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(conectividad != null){
			NetworkInfo[] info = conectividad.getAllNetworkInfo();
			for (int i = 0; i < info.length; i++) {
				if(info[i].getState() == NetworkInfo.State.CONNECTED)
					return true;
			}			
		}
		return false;
	}
		
}
