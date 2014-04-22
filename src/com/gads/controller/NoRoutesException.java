package com.gads.controller;
/**
 * Exception thrown when there are no route stored in the database.
 * @author Matteo Tosi
 *
 */
@SuppressWarnings("serial")
public class NoRoutesException extends Exception {

	public NoRoutesException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoRoutesException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public NoRoutesException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public NoRoutesException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}