package com.gads.controller;

import java.io.File;   
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import com.gads.model.DBManager;
import com.gads.model.GeoRoute;
import com.gads.model.GeoStep;
import com.gads.model.LatLng;


/**
 * Public class that represents the controller of the application.
 * It receives commands from the main class and executes them.
 * @author Matteo Tosi
 *
 */
public class GeoController {
	
	private static int _port = 5554;
	private ArrayList<GeoRoute> routes;
	private XMLGeoParser parser;
	private DBManager db;
	
	private int navStepAt = -1;
	private ArrayList<GeoStep> navSteps = null;
	private ViewBridge view;
	
	public static List<Integer> pool;
	
	private Preferences prefs;
	final private String PATH = "sdk_path";
	
	/**
	 * Constructor that create a new GeoController object.
	 * @param m --> The MessageManager with all the messages to the user.
	 */
	public GeoController(ViewBridge v){
		
		db = new DBManager();
		parser = new XMLGeoParser();
		loadFromDatabase();
		
		view = v;
		pool = new ArrayList<Integer>();
		prefs = Preferences.userNodeForPackage(com.gads.controller.GeoController.class);
	}

	/**
	 * Method that sets the port of the desired emulator.
	 * @param port --> port of the desired emulator.
	 */
	public void setPort(int port){
		_port = port;
	}
	
	/**
	 * Method that sets the path to the Android sdk tools directory.
	 * @param p --> path of the Android sdk tools directory.
	 */
	public void setPath(String p) {
		// es: C:\Users\Matteo\Documents\Android\android-sdk_r20.0.3-windows\android-sdk-windows\tools
		prefs.put(PATH, p);
		
	}
	
	/**
	 * Method that starts the desired emulator.
	 * @param emul --> name of the desired emulator.
	 * @throws WrongPathException 
	 */
	public void startEmulator(String emul) throws WrongPathException{
		
		String path = prefs.get(PATH, "no_sdk");
		try{
			String p = path.replaceAll("\"", "");
			String e = emul.replaceAll("\"", "");
		// Run the console command for opening the emulator
		String exe = new File(p + "/" + "emulator @"+e).getAbsolutePath();
		Runtime.getRuntime().exec(exe,
					null,
				 	new File(p));
		}
		catch(Exception e){
				throw new WrongPathException();
		}
	}
	
	/**
	 * Method that send a single geo fix command to the desired emulator.
	 * @param adr --> Address for the geofix command.
	 * @throws MissingAVDException 
	 * @throws OfflineException 
	 */
	public void setLocation(String adr) throws MissingAVDException, OfflineException{
		try{
			
			LatLng lt1 = db.getPlace(adr);
			
			if(lt1 == null){
				
				Document doc = parser.getDocument(adr);
				LatLng point = parser.getCoordinates(doc);
				String form = parser.getFormAddress(doc);
				
				db.insert(point, adr, form);
				
				GeoFix geo = new GeoFix(_port);
	    		geo.connect();
	    		geo.setFix(point.getLatitude(), point.getLongitude());
			}
			else{
				GeoFix geo = new GeoFix(_port);
	    		geo.connect();
	    		geo.setFix(lt1.getLatitude(), lt1.getLongitude());
			}
    		
		}catch(IOException e){
			throw new MissingAVDException(_port +"");
		}catch(NullPointerException e){
			throw new OfflineException();
		}
	}
	
	/**
	 * Method for inserting a new route in the database,
	 * after the XML parsing.
	 * @param from --> Start address.
	 * @param to --> End address.
	 * @throws OfflineException 
	 * @throws ExistingRouteException 
	 */
	public void newRoute(String from, String to) throws OfflineException, ExistingRouteException {
		
		try{
    		Document doc = parser.getDocument(from, to);
    		ArrayList<GeoStep> steps = parser.getDirection(doc);
    		String duration = parser.getDurationText(doc);
    		String distance = parser.getDistanceText(doc);
    		
    		GeoRoute route = new GeoRoute(from,to,steps,distance,duration);
    		boolean res = db.insert(route,routes.size());
    		
    		if(res)
    			routes.add(route);
    		else throw new ExistingRouteException();
    			
    		
		}catch(NullPointerException e){
			throw new OfflineException();
		}
	}
	
	/**
	 * Method that send a route to the desired emulator, choosen from all the routes 
	 * stored in the database ,using a GeoThread object.
	 * @param choose --> number of the choosen route.
	 * @throws NoRoutesException 
	 * @throws MissingAVDException 
	 * @throws MissingRouteException 
	 */
	public void sendRoute(int choose) throws NoRoutesException, MissingAVDException, MissingRouteException {
		
		if(routes.size()==0)
			throw new NoRoutesException();
		else{
			
			try{
				notifySendingRouteToView(0);
				pool.add(0, new Integer(0));
    			ArrayList<GeoStep> steps = routes.get(choose-1).getSteps();
    			GeoFix geo = new GeoFix(_port);
	    		geo.connect();
    			GeoThread gt = new GeoThread(_port, steps,1000,false,this);
    			synchronized(gt){gt.start();}
    		
			}catch(IndexOutOfBoundsException e){
				pool.remove(0);
				throw new MissingRouteException();
			}catch(NullPointerException e){
				pool.remove(0);
				throw new MissingAVDException(_port +"");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				pool.remove(0);
				throw new MissingAVDException(_port +"");
			}
		}
	}
	
	/**
	 * Public method that stops sending a route to the emulator.
	 */
	public void stopSendingRoute(){
		pool.remove(0);
	}
	
	/**
	 * Method for simulating a navigation on the emulator. First, the user choose a route from
	 * the ones stored in the database. Than it use a GeoNavThread object to send, step by step, all
	 * the points of the desired route to the emulator. The user can choose between doing another step
	 * or stopping the navigation.
	 * @param choose --> number of the choosen route.
	 * @throws MissingAVDException 
	 * @throws MissingRouteException 
	 * @throws NoRoutesException 
	 */
	public void simulateNavigation(int choose) throws MissingAVDException, MissingRouteException, NoRoutesException {///////////////////////////////////
		
		if(routes.size()==0)
			throw new NoRoutesException();
		else{
			
			try{
				pool.add(0, new Integer(0));
				navSteps = routes.get(choose-1).getSteps();
				notifyNavigatioToView(0);
				
				GeoFix geo = new GeoFix(_port);
	    		geo.connect();
	    		geo.setFix(navSteps.get(0).getStartAddress().getLatitude(),navSteps.get(0).getStartAddress().getLongitude());
	    		navStepAt+=1;
	    		
			}catch(IndexOutOfBoundsException e){
				navStepAt = -1;
				navSteps = null;
				pool.remove(0);
				throw new MissingRouteException();
			}catch(NullPointerException e){
				navStepAt = -1;
				navSteps = null;
				pool.remove(0);
				throw new MissingAVDException(_port +"");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				navStepAt = -1;
				navSteps = null;
				pool.remove(0);
				throw new MissingAVDException(_port +"");
				
			}
		}
	}
	
	public void nextStep() throws MissingAVDException, NoNavigationException {
		
		if(navStepAt < 0){
			throw new NoNavigationException();
		}
		try{
			GeoFix g = new GeoFix(_port);
			g.connect();
			int milliseconds = ((navSteps.get(navStepAt).getDurationValue())*1000)/navSteps.get(navStepAt).getPolylines().size();
			if(milliseconds < 1000)
				milliseconds=1000;
			ArrayList<GeoStep> thisStep = new ArrayList<GeoStep>();
			thisStep.add(navSteps.get(navStepAt));
			GeoThread gt = new GeoThread(_port,thisStep,milliseconds,true, this);
			navStepAt+=1;
			synchronized(gt){gt.start();}
		}
		catch(Exception e){
			if(e instanceof IOException)
				throw new MissingAVDException(_port +"");
		}
		
	}
	public void exitNav() throws NoNavigationException{
		
		if(navStepAt < 0){
			throw new NoNavigationException();
		}
		navStepAt = -1;
		navSteps = null;
		pool.remove(0);
		notifyNavigatioToView(1);
	}
	/**
	 * Method that returns all the available routes in String format.
	 * @throws NoRoutesException 
	 * @throws JSONException 
	 */
	public JSONArray getRoutes() throws NoRoutesException, JSONException{
	
		
		if(routes.size()==0)
			throw new NoRoutesException();
		
		//int pos = 1;
		//String r = "";
		int j = 0;
		JSONArray array = new JSONArray();
		for(int i = 0; i < routes.size() ; i++){
	        
		JSONObject map = new JSONObject();
        map.put("start", routes.get(i).getStart());
        map.put("end", routes.get(i).getEnd());
        map.put("duration", routes.get(i).getDuration());
        map.put("distance", routes.get(i).getDistance());
        array.put(j, map);
	        j++;
		}
		return array;
		
	}
	/**
	 * Method that loads from the database all the stored routes.
	 */
	private void loadFromDatabase(){
		
		routes = db.selectAllRoutes(parser);

	}
	
	public void notifyNavigatioToView(int status){
		switch(status){
			case 0: view.onNavigationStarted(); break;
			case 1: view.onNavigationInterrupted(); break;
			case 2: view.onNavigationFinished(); break;
		}
	}
	public void notifySendingRouteToView(int status){
		switch(status){
		case 0: view.onRouteSendingStarded(); break;
		case 1: view.onRouteSendingInterrupted(); break;
		case 2: view.onRouteSendingFinished(); break;
	}
	}
	
	public void notifyView(int prog,boolean nav){
		if(!nav)
			view.progress(prog);
		else{
			if (!(navSteps == null)){
			prog = ((navStepAt)*100)/navSteps.size();
			view.navProgress(prog);
			if(prog == 100){
				pool.remove(0);
				navStepAt = 0;
				navSteps = null;
				notifyNavigatioToView(2);
			}
			}
		}
	}
			
	
}
