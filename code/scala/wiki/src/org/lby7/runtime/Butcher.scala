package org.lby7.runtime

object Butcher
{
  def exec( cmd: String )
  {
    try
    {
      val proc = Runtime.getRuntime.exec( "cmd /c start /low /b /wait " + cmd )
      proc.getOutputStream.close

      val eG = new Gobbler( proc.getErrorStream, "ERROR" ).start
      val oG = new Gobbler( proc.getInputStream, "OUTPUT" ).start

      proc.waitFor();
    }
    catch {
      case t: Throwable =>
        t.printStackTrace
    }
  }
}

import scala.actors.Actor

class Gobbler( val iS: java.io.InputStream, val name: String ) extends Actor
{
  def act()
  {
    try
    {
      val bR = new java.io.BufferedReader( new java.io.InputStreamReader( iS ) );

      while( true )
      {
        val line = bR.readLine
        if ( line eq null ) return;
        println( name + " > " + line )
      }
    }
    catch{
      case t: Throwable =>
        t.printStackTrace()
    }
  }
}
