package com.notitarde.lector;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import com.notitarde.fragments.Global;

import android.content.Context;
import android.util.Log;

/*
 * Helper class para descargar un archivo
 */
public class Downloader {
	
    
    //Handler msg that represents we are posting a progress update.
    static final int POST_PROGRESS = 1;
    
    /************************************************
     * Descargar un archivo en internet y guardarlo localmente.
     * 
     * @param URL - Url del archivo a descargar
     * @param fos - Un FileOutputStream para guardar el archivo descargado.
     ************************************************/
    public static void DownloadFromUrl(String URL, FileOutputStream fos) {  //this is the downloader method
            try {

                    URL url = new URL(URL); //URL of the file
                    
                    //keep the start time so we can display how long it took to the Log.
                    long startTime = System.currentTimeMillis();
                    Log.d(Global.TAG, "comenzando descarga");
                    
                    /* Open a connection to that URL. */
                    URLConnection ucon = url.openConnection();
                    
                    // this will be useful so that you can show a tipical 0-100% progress bar
        //int lenghtOfFile = ucon.getContentLength();

                    Log.i(Global.TAG, "Conexión abierta.");
                    
                    /************************************************
                     * Define InputStreams to read from the URLConnection.
                     ************************************************/
                    InputStream is = ucon.getInputStream();
                    BufferedInputStream bis = new BufferedInputStream(is);
                    Log.i(Global.TAG, "Got InputStream and BufferedInputStream");
                    
                    /************************************************
                     * Define OutputStreams to write to our file.
                     ************************************************/
                    
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    Log.i(Global.TAG, "Got FileOutputStream and BufferedOutputStream");
                    
                    /************************************************
                     * Start reading the and writing our file.
                     ************************************************/
                    byte data[] = new byte[1024];
                    
                    
        //long total = 0;
        int count;
        //loop and read the current chunk
        while ((count = bis.read(data)) != -1) {                    
                //keep track of size for progress.
            //total += count;
                
                //write this chunk
            bos.write(data, 0, count);
        }
                    //Have to call flush or the  file can get corrupted.
                    bos.flush();
                    bos.close();
                    
                    Log.d(Global.TAG, "Descarga lista en:  "
                                    + ((System.currentTimeMillis() - startTime))
                                    + " milisec");
            } catch (IOException e) {
                    Log.e(Global.TAG, "Error: " + e);
            }
    }
    
    public static void DownloadAllFiles(Context ctx, int ini,int fin){    	
    	String[] arr;
    	arr = ctx.getResources().getStringArray(R.array.xml_files);    	
    	for (int i = ini; i < fin; i++) {
    		try {    		
				DownloadFromUrl(Global.URL+arr[i], ctx.getApplicationContext().openFileOutput(arr[i], Context.MODE_PRIVATE));
				Log.i(Global.TAG,"Archivo "+arr[i]+ "descargado.");
			} catch (FileNotFoundException e) {				
				Log.e(Global.TAG, e.toString());
				e.printStackTrace();				
			}    		
		}		    	
    }

}
