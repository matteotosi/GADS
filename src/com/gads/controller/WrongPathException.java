package com.gads.controller;
/**
 * Exception thrown when the specified path for the Android sdk tools directory is wrong.
 * @author Matteo Tosi
 *
 */
@SuppressWarnings("serial")
public class WrongPathException extends Exception {

	public WrongPathException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WrongPathException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public WrongPathException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public WrongPathException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
