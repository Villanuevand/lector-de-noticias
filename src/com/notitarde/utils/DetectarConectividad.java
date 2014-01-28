package com.notitarde.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class DetectarConectividad {

	private Context _ctx;

	public DetectarConectividad(Context context) {
		
		this._ctx = context;
	}
	
	public boolean conexionInternetDisponible(){
		ConnectivityManager conectividad = (ConnectivityManager) _ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(conectividad != null){
			NetworkInfo[] info = conectividad.getAllNetworkInfo();
			if(info != null){
				for (int i = 0; i < info.length; i++) {
					if(info[i].getState() == NetworkInfo.State.CONNECTED)
						return true;
				}
			}
		}
		return false;
	}
}
