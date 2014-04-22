package com.gads.view;

public class Main {

	public static void main(String[] args) {
		Shell shell = new Shell();
		try{
			shell.start();
		} catch (Exception e) {
				e.printStackTrace();
		}
	}

}
