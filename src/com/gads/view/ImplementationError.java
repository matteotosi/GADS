package com.gads.view;

/**
 * Thrown to signal an implementation error.
 * @author Mauro Ferrari
 */
class ImplementationError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ImplementationError() {
		super();
	}

	public ImplementationError(String key, String... args) {
		//super(MessageManager.getMsg(key, (Object[])args));
	}

}
