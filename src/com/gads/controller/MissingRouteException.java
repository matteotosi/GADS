package com.gads.controller;
/**
 * Exception thrown when there's no route for the user choice.
 * @author Matteo Tosi
 *
 */

@SuppressWarnings("serial")
public class MissingRouteException extends Exception {

	public MissingRouteException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MissingRouteException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public MissingRouteException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public MissingRouteException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
