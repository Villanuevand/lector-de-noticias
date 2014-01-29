package com.notitarde.lector;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import android.util.Log;

/*
 * Helper class para descargar un archivo
 */
public class Downloader {
	
	  //Etiquetas para declaracioens del log
    private static String myTag = "Noticias";
    
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
                    Log.d(myTag, "comenzando descarga");
                    
                    /* Open a connection to that URL. */
                    URLConnection ucon = url.openConnection();
                    
                    // this will be useful so that you can show a tipical 0-100% progress bar
        //int lenghtOfFile = ucon.getContentLength();

                    Log.i(myTag, "Conexión abierta.");
                    
                    /************************************************
                     * Define InputStreams to read from the URLConnection.
                     ************************************************/
                    InputStream is = ucon.getInputStream();
                    BufferedInputStream bis = new BufferedInputStream(is);
                    Log.i(myTag, "Got InputStream and BufferedInputStream");
                    
                    /************************************************
                     * Define OutputStreams to write to our file.
                     ************************************************/
                    
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    Log.i(myTag, "Got FileOutputStream and BufferedOutputStream");
                    
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
                    
                    Log.d(myTag, "Descarga lista en:  "
                                    + ((System.currentTimeMillis() - startTime))
                                    + " milisec");
            } catch (IOException e) {
                    Log.e(myTag, "Error: " + e);
            }
    }
}
