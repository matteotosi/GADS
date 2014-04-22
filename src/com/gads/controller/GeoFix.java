package com.gads.controller;

import java.io.IOException;   
import de.mud.telnet.TelnetWrapper;

/** Public class for connecting and sending a Geofix command to the emulator 
 * 
 * @author Matteo Tosi*/
public class GeoFix {

	private TelnetWrapper _telnet;
	private int _port;
	
	/**
	 * Creates a new GeoFix object, setting the port and creating the TelnetWrapper object.
	 * @param port
	 */
	public GeoFix(final int port){
		_port = port;
		_telnet = new TelnetWrapper();
	}
	
	/**
	 * Method that connects _telnet to the port.
	 * @throws IOException
	 */
	public void connect() throws IOException{
		// disconnect from any previous connection
	      _telnet.disconnect();
	      // reconnect to the new port
	      _telnet.connect("localhost", _port);
	}
	
	/**
	 * Method that switch the port and connects _telnet to the new one.
	 * @param port
	 * @throws IOException
	 */
	 public void setPort(final int port) throws IOException {
		 this._port = port;
		 // disconnect from any previous connection
	      _telnet.disconnect();
	      // reconnect to the new port
	      _telnet.connect("localhost", _port);
	      
	   }
	 
	 /**
	  * Method that send a geo fix command to the emulator connected on the port.
	  * @param latitude
	  * @param longitude
	  * @throws IOException
	  */
	 public void setFix(final double latitude, final double longitude) throws IOException{
		 // The effective geo fix command
		 _telnet.send("geo fix " + longitude + " " + latitude);
	 }
}
