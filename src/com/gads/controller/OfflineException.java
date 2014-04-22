package com.gads.controller;
/**
 * Exception thrown where there's no internet connection.
 * @author Matteo Tosi
 *
 */
@SuppressWarnings("serial")
public class OfflineException extends Exception {

	public OfflineException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OfflineException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public OfflineException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public OfflineException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}