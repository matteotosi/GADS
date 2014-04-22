package com.gads.controller;

/**
 * Exception thrown when there's no active navigation.
 * @author Matteo Tosi
 *
 */

@SuppressWarnings("serial")
public class NoNavigationException extends Exception {

	public NoNavigationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoNavigationException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public NoNavigationException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public NoNavigationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}