package com.gads.controller;
/**
 * Exeption thrown when there's no AVD on the desired port.
 * @author Matteo Tosi
 *
 */
@SuppressWarnings("serial")
public class MissingAVDException extends Exception {

	public MissingAVDException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MissingAVDException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public MissingAVDException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public MissingAVDException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}