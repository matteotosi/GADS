package com.gads.model;

/**
 * Public class that represents a single Geo point
 * with latitude and longitude as Coordinates
 * 
 * @author Matteo Tosi
 * 
 */
public class LatLng {

	private double lat;
	private double lng;
	private String address;
	/**
	 * Constructs the LatLng object with the specified latitude and longitude
	 * @param lat
	 * @param lng
	 */
	public LatLng(double lat, double lng){
		
		this.lat = lat;
		this.lng = lng;
	}
	public LatLng(String address, double lat, double lng){
		
		this.address = address;
		this.lat = lat;
		this.lng = lng;
	}
	/**
	 * Method that returns the object's latitude
	 * @return the latitude
	 */
	public double getLatitude(){
		return this.lat;
	}
	/**
	 * Method that returns the object's longitude
	 * @return the longitude
	 */
	public double getLongitude(){
		return this.lng;
	}
	public String getAddress(){
		return this.address;
	}
	/**
	 * Method that returns the String representation of the object
	 * @return String representation of the object
	 */
	@Override
	public String toString(){
		return this.lat + "," + this.lng;
	}
}
