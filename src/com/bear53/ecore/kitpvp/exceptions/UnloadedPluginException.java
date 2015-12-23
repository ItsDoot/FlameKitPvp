package com.bear53.ecore.kitpvp.exceptions;

public class UnloadedPluginException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnloadedPluginException(String message) {
		super(message);
	}

	public UnloadedPluginException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public String getMessage() {
		return super.getMessage();
	}
}