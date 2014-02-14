package com.notitarde.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.TextView;

public class Global {

	public static final String TAG = "Notitarde";	
	public static final String URL = "https://googledrive.com/host/0ByoQ8u8IrvxGMXBMcFpGY0twdkU/";
	public static final String XML_TITULARES = "titulares.xml";
	public static final String XML_VALENCIA = "valencia.xml";
	public static final String XML_PAIS = "pais.xml";
	public static final String XML_ECONOMIA = "economia.xml";
	public static final String XML_INTERNACIONAL = "internacional.xml";
	public static final String XML_DEPORTES ="deportes.xml";
	public static final String XML_SUCESOS = "sucesos.xml";
	public static final String XML_LACOSTA = "lacosta.xml";
	public static final String XML_REGIONES = "regiones.xml";
	public static final String XML_ESPECTACULOS = "espectaculos.xml";
	public static final String XML_INSTITUCIONALES = "institucionales.xml";
	public static final String XML_SOCIALES = "sociales.xml";
	public static  Boolean HAY_INTERNET = false;
	
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
