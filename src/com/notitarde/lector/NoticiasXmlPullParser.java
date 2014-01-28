package com.notitarde.lector;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;


import android.content.Context;

public class NoticiasXmlPullParser {

	static final String KEY_NOTICIA = "noticia";
	static final String KEY_TITULO = "titulo";
	static final String KEY_DESCRIPCION = "descripcion";
	static final String KEY_FECHA = "fecha";
	static final String KEY_SECCION = "seccion";
	static final String KEY_IMAGEN_URL = "imagen";
	static final String KEY_URL = "url";
	
	public static List<Noticias> getNoticiasFromFile(Context ctx){
		
//		Lista de noticias que será retornada
		List<Noticias> ListadoNoticias;
		ListadoNoticias = new ArrayList<Noticias>();
		
//		Temporal de Noticia actual durante el parseo
		Noticias curNoticia = null;
		
//		Tempora que contendrá el valor de texto durante el parseo
		String curText = "";
		
		  try {
              // Instanciando el ParserFactory PullParser
              XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
              XmlPullParser xpp = factory.newPullParser();
              
              // Open up InputStream and Reader of our file.
              FileInputStream fis = ctx.openFileInput("Noticias.xml");
              BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

              // point the parser to our file.
              xpp.setInput(reader);

              // get initial eventType
              int eventType = xpp.getEventType();

              // Loop through pull events until we reach END_DOCUMENT
              while (eventType != XmlPullParser.END_DOCUMENT) {
                      // Get the current tag
                      String tagname = xpp.getName();

                      // React to different event types appropriately
                      switch (eventType) {
                      case XmlPullParser.START_TAG:
                              if (tagname.equalsIgnoreCase(KEY_NOTICIA)) {
                                      // If we are starting a new <site> block we need
                                      //a new StackSite object to represent it
                                      curNoticia = new Noticias();
                              }
                              break;

                      case XmlPullParser.TEXT:
                              //grab the current text so we can use it in END_TAG event
                              curText = xpp.getText();
                              break;

                      case XmlPullParser.END_TAG:
                              if (tagname.equalsIgnoreCase(KEY_NOTICIA)) {
                                      // if </site> then we are done with current Site
                                      // add it to the list.
                                      ListadoNoticias.add(curNoticia);
                              } else if (tagname.equalsIgnoreCase(KEY_TITULO)) {
                                      // if </name> use setName() on curSite
                            	  curNoticia.setTitulo(curText);
                              } else if (tagname.equalsIgnoreCase(KEY_FECHA)) {
                                      // if </link> use setLink() on curSite
                            	  curNoticia.setFecha(curText);
                              } else if (tagname.equalsIgnoreCase(KEY_DESCRIPCION)) {
                                      // if </about> use setAbout() on curSite
                            	  curNoticia.setDescripcion(curText);
                              } else if (tagname.equalsIgnoreCase(KEY_SECCION)) {
                                      // if </image> use setImgUrl() on curSite
                            	  curNoticia.setSeccion(curText);
                              }else if (tagname.equalsIgnoreCase(KEY_IMAGEN_URL)){
                            	  curNoticia.setImgUrl(curText);                            	  
                              }else if (tagname.equalsIgnoreCase(KEY_URL)){
                            	  curNoticia.setUrl(curText);
                              }                              
                              break;

                      default:
                              break;
                      }
                      //Moviendo hacia la proxima iteración.
                      eventType = xpp.next();
              }
      } catch (Exception e) {
              e.printStackTrace();
      }
		
		return ListadoNoticias;
		
	}
	
}
