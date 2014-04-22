package com.gads.controller;

import java.io.BufferedReader;   
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter; 
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.gads.model.GeoStep;
import com.gads.model.LatLng;

/**
 * Public class for parsing XML data received from the queries to Google Maps api.
 * 
 * @author Matteo Tosi
 * 
 */
public class XMLGeoParser {
	
	public XMLGeoParser() { }
	
	/**
	 * Method that returns the XML document with data of a single location.
	 * @param address --> Address in String form of the desired location.
	 * @return the document.
	 */
	public Document getDocument(String address) {
		
		String uri = "";
		
		address = address.replace(",", "+");
		address = address.replace(" ", "+");
		uri = "http://maps.googleapis.com/maps/api/geocode/xml?address="
			+ address
            + "&sensor=false";
		
        return getFromUri(uri);
        
	}
	
	/**
	 * Method that returns the XML document with directions data from the start address to 
	 * the end address (Specified in String form).
	 * @param start --> Address in String form of the desired start point.
	 * @param end --> Address in String form of the desired end point.
	 * @return the document.
	 */
	public Document getDocument(String start, String end) {
		
		String uri = "";
		
		start = start.replace(",", "+");
		start = start.replace(" ", "+");
		end = end.replace(",", "+");
		end = end.replace(" ", "+");
		
		uri = "http://maps.googleapis.com/maps/api/directions/xml?" 
            		+ "origin=" + start 
            		+ "&destination=" + end 
            		+ "&sensor=false&units=metric&mode=driving";
		
        return getFromUri(uri);
        
	}
	
	/**
	 * Method that returns the XML document with directions data from the start address to 
	 * the end address (Specified in LatLong form).
	 * @param start --> Address in LatLong form of the desired start point.
	 * @param end --> Address in LatLong form of the desired end point.
	 * @return the document.
	 */
	public Document getDocument(LatLng start, LatLng end) {
		
		String uri = "";
		
		uri = "http://maps.googleapis.com/maps/api/directions/xml?" 
            		+ "origin=" + start.toString()  
            		+ "&destination=" + end.toString() 
            		+ "&sensor=false&units=metric&mode=driving";
		
        return getFromUri(uri);
        
	}
	
	/**
	 * Method that reads the response of the query from the net and generates
	 * the XML document.
	 * @param uri --> Url for the query to Google Maps Api.
	 * @return the XML document.
	 */
	public Document getFromUri(String uri){
		String thisLine = "";
		try {
        	URL url = new URL(uri);
        	
        	BufferedReader theHTML = new BufferedReader(new InputStreamReader(url.openStream()));

        	FileWriter fstream = new FileWriter("url.xml");
        	BufferedWriter out = new BufferedWriter(fstream);
        	while ((thisLine = theHTML.readLine()) != null)
            out.write(thisLine);
        	out.close();

        	File file = new File("url.xml");
        	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        	DocumentBuilder db = dbf.newDocumentBuilder();
        	Document doc = db.parse(file);
        	doc.getDocumentElement().normalize();
        	
        	return doc;
        } catch (Exception e) {
        	//
        }
		return null;
	}
	
	/**
	 * Method that returns the LatLong value of an Address from the
	 * XML document (generated from the method getDocument(String address) ).
	 * @param doc --> the XML document.
	 * @return the LatLong value.
	 */
	public LatLng getCoordinates (Document doc){
		
		NodeList nl = doc.getElementsByTagName("lat");
        Element n = (Element)nl.item(0);
        String st = n.getFirstChild().getNodeValue();

        NodeList n2 = doc.getElementsByTagName("lng");
        Element nn = (Element)n2.item(0);
        String st1 = nn.getFirstChild().getNodeValue();

        return new LatLng(Double.parseDouble(st),Double.parseDouble(st1));
    }
	
	public String getFormAddress (Document doc){
		
		NodeList nl = doc.getElementsByTagName("formatted_address");
        Element n = (Element)nl.item(0);
        String st = n.getFirstChild().getNodeValue();
        
        return st;
		
	}
	
	/**
	 * Method that returns the duration text from the XML document
	 * with the directions from a start point to an end point.
	 * @param doc --> the XML document.
	 * @return the duration text.
	 */
	public String getDurationText (Document doc){
		NodeList nl1 = doc.getElementsByTagName("duration");
        Node node1 = nl1.item(nl1.getLength()-1);
        NodeList nl2 = node1.getChildNodes();
        Node node2 = nl2.item(getNodeIndex(nl2, "text"));
        
		return node2.getTextContent();
	}
	
	/**
	 * Method that returns the duration value from the XML document
	 * with the directions from a start point to an end point.
	 * @param doc --> the XML document.
	 * @return the duration value.
	 */
	public int getDurationValue (Document doc){
		NodeList nl1 = doc.getElementsByTagName("duration");
        Node node1 = nl1.item(nl1.getLength()-1);
        NodeList nl2 = node1.getChildNodes();
        Node node2 = nl2.item(getNodeIndex(nl2, "value"));
        
		return Integer.parseInt(node2.getTextContent());
	}
	
	/**
	 * Method that returns the distance text from the XML document
	 * with the directions from a start point to an end point.
	 * @param doc --> the XML document.
	 * @return the distance text.
	 */
	public String getDistanceText (Document doc){
		NodeList nl1 = doc.getElementsByTagName("distance");
        Node node1 = nl1.item(nl1.getLength()-1);
        NodeList nl2 = node1.getChildNodes();
        Node node2 = nl2.item(getNodeIndex(nl2, "text"));
        
		return node2.getTextContent();
	}
	
	/**
	 * Method that returns the duration text from the XML document
	 * with the directions from a start point to an end point.
	 * @param doc --> the XML document.
	 * @return the duration text.
	 */
	public int getDistanceValue (Document doc){
		NodeList nl1 = doc.getElementsByTagName("distance");
        Node node1 = nl1.item(nl1.getLength()-1);
        NodeList nl2 = node1.getChildNodes();
        Node node2 = nl2.item(getNodeIndex(nl2, "value"));
        
		return Integer.parseInt(node2.getTextContent());
	}
	
	/**
	 * Method that returns the start address from the XML document
	 * with the directions from a start point to an end point.
	 * @param doc --> the XML document.
	 * @return the start address.
	 */
	public String getStartAddress (Document doc){
		NodeList nl1 = doc.getElementsByTagName("start_address");
        Node node1 = nl1.item(0);
        
		return node1.getTextContent();
	}
	
	/**
	 * Method that returns the end address from the XML document
	 * with the directions from a start point to an end point.
	 * @param doc --> the XML document.
	 * @return the end address.
	 */
	public String getEndAddress (Document doc){
		NodeList nl1 = doc.getElementsByTagName("end_address");
        Node node1 = nl1.item(0);
        
		return node1.getTextContent();
	}
	
	/**
	 * Method that returns an ArrayList of GeoStep that represents the diractions between 
	 * a start point and an end point, readed from the XML document.
	 * @param doc --> the XML document.
	 * @return the ArrayList of GeoStep.
	 */
	public ArrayList<GeoStep> getDirection (Document doc){
		
		NodeList nl1, nl2, nl3;
		ArrayList<GeoStep> steps = new ArrayList<GeoStep>();
		
        nl1 = doc.getElementsByTagName("step");
        if (nl1.getLength() > 0) {
            for (int i = 0; i < nl1.getLength(); i++) {
            	
            	ArrayList<LatLng> listGeopoints = new ArrayList<LatLng>();
                String encoded = "";
            	String durationStep ="";
            	int durationValue =0;
                String distanceStep="";
                
                Node node1 = nl1.item(i);
                nl2 = node1.getChildNodes();

                Node locationNode = nl2.item(getNodeIndex(nl2, "start_location"));
                nl3 = locationNode.getChildNodes();
                Node latNode = nl3.item(getNodeIndex(nl3, "lat"));
                double lat = Double.parseDouble(latNode.getTextContent());
                Node lngNode = nl3.item(getNodeIndex(nl3, "lng"));
                double lng = Double.parseDouble(lngNode.getTextContent());
                listGeopoints.add(new LatLng(lat, lng));

                locationNode = nl2.item(getNodeIndex(nl2, "polyline"));
                nl3 = locationNode.getChildNodes();
                latNode = nl3.item(getNodeIndex(nl3, "points"));
                encoded = latNode.getTextContent();
                ArrayList<LatLng> arr = decodePoly(encoded);
                for(int j = 0 ; j < arr.size() ; j++) {
                	listGeopoints.add(new LatLng(arr.get(j).getLatitude(), arr.get(j).getLongitude()));
                }

                locationNode = nl2.item(getNodeIndex(nl2, "end_location"));
                nl3 = locationNode.getChildNodes();
                latNode = nl3.item(getNodeIndex(nl3, "lat"));
                lat = Double.parseDouble(latNode.getTextContent());
                lngNode = nl3.item(getNodeIndex(nl3, "lng"));
                lng = Double.parseDouble(lngNode.getTextContent());
                listGeopoints.add(new LatLng(lat, lng));
                
                Node distanceNode = nl2.item(getNodeIndex(nl2, "distance"));
                nl3 = distanceNode.getChildNodes();
                Node stepDistNode = nl3.item(getNodeIndex(nl3, "text"));
                distanceStep = stepDistNode.getTextContent();
                
                Node durationNode = nl2.item(getNodeIndex(nl2, "duration"));
                nl3 = durationNode.getChildNodes();
                Node stepDurNode = nl3.item(getNodeIndex(nl3, "text"));
                durationStep = stepDurNode.getTextContent();
                nl3 = durationNode.getChildNodes();
                stepDurNode = nl3.item(getNodeIndex(nl3, "value"));
                durationValue = Integer.parseInt(stepDurNode.getTextContent());
                
               steps.add(i, new GeoStep(listGeopoints,encoded,durationStep,durationValue,distanceStep));
            }
        }
        
        return steps;
	}
	
	/**
	 * Method that returns a specific node index in the XML document.
	 * @param nl --> the NodeList.
	 * @param nodename --> the name of the desired node.
	 * @return the int value of the node's index.
	 */
	public int getNodeIndex(NodeList nl, String nodename) {
		for(int i = 0 ; i < nl.getLength() ; i++) {
			if(nl.item(i).getNodeName().equals(nodename))
				return i;
		}
		return -1;
	}
	
	/**
	 * Method that do the opposite steps of the Polyline Algorithm 
	 * used by Google to encode a list of Latitudes and Longitudes.
	 * @param encoded --> the encoded String.
	 * @return and ArrayList of LatLng.
	 */
	public ArrayList<LatLng> decodePoly(String encoded) {
		ArrayList<LatLng> poly = new ArrayList<LatLng>();
		int index = 0, len = encoded.length();
		int lat = 0, lng = 0;
		while (index < len) {
			int b, shift = 0, result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lat += dlat;
			shift = 0;
			result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lng += dlng;
			
			LatLng position = new LatLng((double)lat / 1E5, (double)lng / 1E5);
			poly.add(position);
		}
		return poly;
	}
}
