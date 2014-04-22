package com.gads.view;

class ExecutionResult {

	String cmd_output;
	int cmd_index;

	ExecutionResult(int index, String output) {
		super();
		this.cmd_output = output;
		this.cmd_index = index;
	}

}
