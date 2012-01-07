package org.lby7.misc

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.{DefaultHttpClient, BasicResponseHandler}

import org.apache.commons.io.FileUtils
import java.io.File

object Downloader {
  val TMP_FOLDER = "tmp"

  def cacheGet( uri: String, name: String ): String = {

    val cache = new File( TMP_FOLDER, name )

    if ( cache.exists ) {
//      println( "Cache hit (" + name + "), no download!" )
      FileUtils.readFileToString( cache, "UTF-8" )
    }
    else{
      val html = get( uri )
//      println( "Writing cache (" + name + ")" );
      FileUtils.writeStringToFile( cache, html, "UTF-8" )
      html
    }
  }

  def get( uri: String ) = {
//    println( "Downloading (" + uri + ") ..." );
    val httpClient = new DefaultHttpClient
    val responseBody = httpClient.execute( new HttpGet( uri ), new BasicResponseHandler )
    httpClient.getConnectionManager.shutdown
//    println( "Download finished (" + uri + ")" );
    responseBody
  }
}