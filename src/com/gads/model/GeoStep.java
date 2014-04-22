package com.gads.model;

import java.util.ArrayList; 

/**
 * Public class that represents a single Step in a navigation, and
 * stores all significant data.
 * @author Matteo Tosi
 *
 */
public class GeoStep {

	private ArrayList<LatLng> polylines;
	private String encodedPoly, durationText, distance;
	private int durationValue;
	
	/**
	 * Constructs a GeoStep object.
	 * @param poly --> All the LatLng of the step.
	 * @param encoded --> Encoded representation of the poly parameter.
	 * @param dur --> The duration of the step (text value).
	 * @param dist --> the distance of the step (text value).
	 */
	public GeoStep(ArrayList<LatLng> poly, String encoded, String dur, int durv, String dist){
		
		this.polylines = poly;
		this.encodedPoly = encoded;
		this.durationText = dur;
		this.durationValue = durv;
		this.distance = dist;
		
	}
	
	/**
	 * Get method that returns the polylines parameter.
	 * @return polylines parameter.
	 */
	public ArrayList<LatLng> getPolylines(){
		return polylines;
	}
	
	/**
	 * Get method that returns the encodedPoly parameter.
	 * @return encodedPoly parameter.
	 */
	public String getEncodedPolylines(){
		return encodedPoly;
	}
	
	/**
	 * Get method that returns the text reprensentation of the duration parameter.
	 * @return duration parameter.
	 */
	public String getDurationText(){
		return durationText;
	}
	
	/**
	 * Get method that returns the representation of duratithe on parameter in seconds.
	 * @return duration parameter.
	 */
	public int getDurationValue(){
		return durationValue;
	}
	
	/**
	 * Get method that returns the distance parameter.
	 * @return distance parameter.
	 */
	public String getDistance(){
		return distance;
	}
	
	/**
	 * Get method that returns the LatLng representation 
	 * of the start address of the step.
	 * @return the fist LatLng object of the polylines parameter.
	 */
	public LatLng getStartAddress(){
		return polylines.get(0);
	}
	
	/**
	 * Get method that returns the LatLng representation 
	 * of the end address of the step.
	 * @return the last LatLng object of the polylines parameter.
	 */
	public LatLng getEndAddress(){
		return polylines.get(polylines.size()-1);
	}
}
