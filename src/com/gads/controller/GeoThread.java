package com.gads.controller;

import java.io.IOException;  
import java.util.ArrayList;

import com.gads.model.GeoStep;
import com.gads.model.LatLng;

/**
 * Public class that extends Thread. It has the job to send a list of geo fix 
 * commands to the emulator.
 * @author Matteo Tosi
 *
 */
public class GeoThread extends Thread{
	
	private ArrayList<GeoStep> steps;
	private GeoFix geo;
	private GeoController sender;
	private int percentage = 0;
	private int interval = 1000;
	private boolean isNav = false;
	
	/**
	 * Constructor that takes an ArrayList of GeoStep and the port
	 * for the creation of the GeoFix object.
	 * @param port
	 * @param steps
	 */
	public GeoThread(final int port, ArrayList<GeoStep> steps,int interval, boolean nav, GeoController sender){
		
		this.isNav = nav;
		this.steps = steps;
		this.sender = sender;
		//the interval must always be >= 1000 milliseconds
		if(interval > 1000)
			this.interval = interval;
		geo = new GeoFix(port);
		
	}
	
	/**
	 * Run method that connects the GeoFix object to the emulator and
	 * send a list of geo fix commands, readed from the ArrayList of GeoStep.
	 */
	@Override
	public void run(){
		
		
		try {
			geo.connect();
			if(!isNav)
				sender.notifyView(percentage,false);
			
			for(int i = 0; i < steps.size(); i++){
				
				if(GeoController.pool.isEmpty()) break;
				makeStep(steps.get(i),interval, this.geo);
				
				percentage = ((i+1)*100)/steps.size();
				if(!isNav)
					sender.notifyView(percentage,false);
				else
					sender.notifyView(percentage,true);
			}
			
			if(!GeoController.pool.isEmpty() && !isNav){
				
				sender.notifySendingRouteToView(2);
				GeoController.pool.remove(0);}
			else if(!isNav)
				sender.notifySendingRouteToView(1);
			
		} catch (IOException e1) {
			
		}

	}
	
	/**
	 * Method that simulates a single navigation step, sending 
	 * a list of points to the emulator
	 * @param step
	 * @throws IOException 
	 */
	protected void makeStep(GeoStep step,int milliseconds, GeoFix geo) throws IOException{
		
		for(int i = 0; i < step.getPolylines().size(); i++){
			if(GeoController.pool.isEmpty()) break;// for GeoNavThread
			LatLng point = step.getPolylines().get(i);
			try {
				geo.setFix(point.getLatitude(), point.getLongitude());
				synchronized(this){
				this.wait(milliseconds);
				}
			} catch (InterruptedException e) {
			}
			
		}
	}
	
}
