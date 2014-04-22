package com.gads.controller;
/**
 * Exeption thrown when the user tries to add an existing route in the database.
 * @author Matteo Tosi
 *
 */
@SuppressWarnings("serial")
public class ExistingRouteException extends Exception {
	public ExistingRouteException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExistingRouteException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ExistingRouteException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ExistingRouteException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
