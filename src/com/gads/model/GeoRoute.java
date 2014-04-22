package com.gads.model;

import java.util.ArrayList; 

/**
 * Public class that represents a single Route.
 * @author Matteo Tosi
 *
 */
public class GeoRoute {
	
	private String start;
	private String end;
	private ArrayList<GeoStep> steps;
	private String distance;
	private String duration;
	
	/**
	 * Constructor that takes five params:
	 * @param start --> Start address of the route.
	 * @param end --> End address of the route.
	 * @param steps --> ArrayList of GeoStep
	 * @param distance --> Total distance of the route.
	 * @param duration --> Total duration of the route.
	 */
	public GeoRoute(String start, String end,ArrayList<GeoStep> steps,String distance,String duration ){
		
		this.start = start;
		this.end = end;
		this.steps = steps;
		this.distance = distance;
		this.duration = duration;
		
	}
	
	/**
	 * Method that returns the Start address of the route.
	 * @return the Start address of the route.
	 */
	public String getStart(){
		return this.start;
	}
	/**
	 * Method that returns the End address of the route.
	 * @return the End address of the route.
	 */
	public String getEnd(){
		return this.end;
	}
	/**
	 * Method that returns the steps of the route.
	 * @return the steps of the route.
	 */
	public ArrayList<GeoStep> getSteps(){
		return this.steps;
	}
	/**
	 * Method that sets the steps of the route.
	 * @param the steps of the route.
	 */
	public void setSteps(ArrayList<GeoStep> s) {
		this.steps = s;
	}
	/**
	 * Method that returns the duration of the route.
	 * @return the duration of the route.
	 */
	public String getDuration(){
		return this.duration;
	}
	/**
	 * Method that returns the distance of the route.
	 * @return the distance of the route.
	 */
	public String getDistance(){
		return this.distance;
	}
	
}
