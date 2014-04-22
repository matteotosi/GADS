package com.gads.controller;

public interface ViewBridge {

	void progress(int percent);
	void navProgress(int percent);
	void onNavigationInterrupted();
	void onNavigationStarted();
	void onNavigationFinished();
	void onRouteSendingStarded();
	void onRouteSendingInterrupted();
	void onRouteSendingFinished();
	
}
