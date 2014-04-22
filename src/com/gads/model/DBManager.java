package com.gads.model;

import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gads.controller.XMLGeoParser;


/**
 * Public class that represents the Database Manager.
 * It creates the database and has all the methods interacting with it.
 * @author Matteo Tosi
 *
 */
public class DBManager {

	/**
	 * Contructor that creates the database and stores in it two tables:
	 * routes --> table that stores all the routes.
	 * steps --> table that stores all the steps of a route.
	 * These are connected by a Foreign Key constraint on the id of the route.
	 */
	public DBManager(){
	    Connection connection = null;
	    try
	    {
	      Class.forName("org.sqlite.JDBC");
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:gads.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	      statement.executeUpdate("PRAGMA foreign_keys = ON;");
	      
	      statement.executeUpdate("CREATE TABLE IF NOT EXISTS places " +
		      		"(id INTEGER AUTO_INCREMENT," +
		      		"address TEXT NOT_NULL," +
		      		" form_address TEXT NOT_NULL, " +
		      		" lat DOUBLE NOT_NULL, " +
		      		" lng DOUBLE NOT_NULL," +
		      		" PRIMARY KEY(address,form_address))");
	      
	      statement.executeUpdate("CREATE TABLE IF NOT EXISTS routes " +
	      		"(id INTEGER AUTO_INCREMENT," +
	      		" start TEXT," +
	      		" end TEXT, " +
	      		" duration TEXT NOT_NULL, " +
	      		" distance TEXT NOT_NULL," +
	      		" PRIMARY KEY(start,end))");
	      
	      statement.executeUpdate("CREATE TABLE IF NOT EXISTS steps " +
		      		"(num INT, " +
	    		    " encoded TEXT NOT_NULL," +
		      		" routeid INTEGER," +
		      		" duration TEXT NOT_NULL, " +
		      		" durationvalue INT NOT_NULL, " +
		      		" distance TEXT NOT_NULL," +
		      		" PRIMARY KEY(num,routeid)," +
		      		" FOREIGN KEY(routeid) REFERENCES routes(id) ON DELETE CASCADE)");
	      
	    }
	    catch(SQLException e)
	    {
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e)
	      {
	        // connection close failed.
	        System.err.println(e);
	      }
	    }
	}

	public boolean insert(LatLng tg, String address, String form_address){
		Connection connection = null;
	    try
	    {
	      Class.forName("org.sqlite.JDBC");
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:gads.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);
	      
	      PreparedStatement prep = connection.prepareStatement(
	              "insert into places(address,form_address,lat,lng) values (?, ?, ?, ?);");
	      prep.setString(1, address);
	      prep.setString(2, form_address);
          prep.setDouble(3, tg.getLatitude());
          prep.setDouble(4, tg.getLongitude());
          prep.addBatch();
          prep.executeBatch();
	
          connection.setAutoCommit(false);
          prep.executeBatch();
          connection.setAutoCommit(true);
          return true;
	    }catch(SQLException e)
	    {
	    	return false;
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e)
	      {
	        return false;
	      }
	    }
	
	return false;
		
	}
	/**
	 * Method that insert in the database a route.
	 * @param route --> the route to be inserted.
	 * @param id --> the id of the new route.
	 * @return
	 */
	public boolean insert(GeoRoute route, int id){
		
		Connection connection = null;
	    try
	    {
	      Class.forName("org.sqlite.JDBC");
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:gads.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);
	      
	      PreparedStatement prep = connection.prepareStatement(
	              "insert into routes(start,end,duration,distance) values (?, ?, ?, ?);");
	      prep.setString(1, route.getStart());
	      prep.setString(2, route.getEnd());
          prep.setString(3, route.getDuration());
          prep.setString(4, route.getDistance());
          prep.addBatch();
          prep.executeBatch();
          
    	  prep = connection.prepareStatement("insert into steps values (?, ?, ?, ?, ?, ?);");
          for(int i = 0; i < route.getSteps().size() ; i++){
        	  prep.setInt(1, i);
              prep.setString(2, route.getSteps().get(i).getEncodedPolylines());
              prep.setInt(3, id);
              prep.setString(4, route.getSteps().get(i).getDurationText());
              prep.setInt(5, route.getSteps().get(i).getDurationValue());
              prep.setString(6, route.getSteps().get(i).getDistance());
              prep.addBatch();
          }
          connection.setAutoCommit(false);
          prep.executeBatch();
          connection.setAutoCommit(true);
          return true;
	    }catch(SQLException e)
	    {
	    	
		      return false;
		    } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    finally
		    {
		      try
		      {
		        if(connection != null)
		          connection.close();
		      }
		      catch(SQLException e)
		      {
		        return false;
		      }
		    }
		
		return false;
		
	}
	public LatLng getPlace(String address){
		Connection connection = null;
		LatLng ll = null;
	    try
	    {
	    	Class.forName("org.sqlite.JDBC");
		      // create a database connection
		      connection = DriverManager.getConnection("jdbc:sqlite:gads.db");
		      Statement statement = connection.createStatement();
		      statement.setQueryTimeout(30);
		      
	          ResultSet rs = statement.executeQuery("SELECT * FROM places WHERE address ='" + address+"'");
	          
	          while(rs.next()){
	        	  ll = new LatLng(rs.getString("form_address"),rs.getDouble("lat"), rs.getDouble("lng"));
	          }
	          
	    }catch(SQLException e)
	    {
	    	return null;
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e)
	      {
	        return null;
	      }
	    }
		return ll;
	}

	public LatLng getPlace(String address, String form_address){
		Connection connection = null;
		LatLng ll = null;
	    try
	    {
	    	Class.forName("org.sqlite.JDBC");
		      // create a database connection
		      connection = DriverManager.getConnection("jdbc:sqlite:gads.db");
		      Statement statement = connection.createStatement();
		      statement.setQueryTimeout(30);
		      
		      String selectSQL = "SELECT * FROM places WHERE form_address = ? AND address = ?";
		      PreparedStatement prep = connection.prepareStatement(selectSQL);
		      prep.setString(1, form_address);
		      prep.setString(2, address);
		      
	          ResultSet rs = prep.executeQuery(selectSQL);
	          
	          while(rs.next()){
	        	  ll = new LatLng(rs.getDouble("lat"), rs.getDouble("lng"));
	          }
	          
	    }catch(SQLException e)
	    {
	    	return null;
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e)
	      {
	        return null;
	      }
	    }
		return ll;
	}
	
	public ArrayList<LatLng> selectAllPlaces(){
		
		ArrayList<LatLng> places = new ArrayList<LatLng>();
		Connection connection = null;
	    try
	    {
	      Class.forName("org.sqlite.JDBC");
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:gads.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  
		  ResultSet rs = statement.executeQuery("SELECT * FROM places ORDER BY id");
		  
	      while(rs.next())
	      {
	    	 int id = rs.getInt("id");
	    	 String addr = rs.getString("form_address");
	    	 double lat = rs.getDouble("lat");
	    	 double lng = rs.getDouble("lng");
	    	 
	    	 places.add(id,new LatLng(addr,lat,lng));
	    	 
	      }
	    }catch(SQLException e)
	    {
	    	return null;
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e)
	      {
	        return null;
	      }
	    }
		return places;
		
	}
	/**
	 * Method that select from the database all the routes stored, and
	 * returns them as an ArrayList of GeoRoutes objects.
	 * @param parser --> the XMLGeoParser object.
	 * @return --> the ArrayList of GeoRoutes.
	 */
	public ArrayList<GeoRoute> selectAllRoutes(XMLGeoParser parser){
		
		ArrayList<GeoRoute> routes = new ArrayList<GeoRoute>();
		Connection connection = null;
	    try
	    {
	      Class.forName("org.sqlite.JDBC");
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:gads.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  
		  ResultSet rs = statement.executeQuery("SELECT * FROM routes ORDER BY id");
		  int num = 0;
	      while(rs.next())
	      {
	    	 ArrayList<GeoStep> steps = new ArrayList<GeoStep>();
	    	 String start = rs.getString("start");
	    	 String end = rs.getString("end");
	    	 String duration = rs.getString("duration");
	    	 String distance = rs.getString("distance");
	    	 
	    	 routes.add(num,new GeoRoute(start,end,steps,distance,duration));
	    	 num+=1;
	    	 
	      }
	      
	      for(int j = 0; j<routes.size(); j++){

		    	 ResultSet rs0 = statement.executeQuery("SELECT * FROM steps "+
		    	 "WHERE routeid=" + j);
		    	 ArrayList<GeoStep> s = new ArrayList<GeoStep>();
		    	 while(rs0.next())
			      {
		    		 GeoStep step = new GeoStep(
		    				 parser.decodePoly(rs0.getString("encoded")),
		    						 rs0.getString("encoded"),
		    						 rs0.getString("duration"),
		    						 rs0.getInt("durationvalue"),
		    						 rs0.getString("distance")
		    						 );
		    		 s.add(rs0.getInt("num"),step);
			      }
		    	 routes.get(j).setSteps(s);
	      }
	      
	      return routes;
	      
	    }catch(SQLException e)
	    {
		      System.err.println(e.getMessage());
		    } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    finally
		    {
		      try
		      {
		        if(connection != null)
		          connection.close();
		      }
		      catch(SQLException e)
		      {
		        // connection close failed.
		        System.err.println(e);
		      }
		    }
	    return routes;
	}
}
