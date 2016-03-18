package com.jerryorr.lightning.dash;

public class PushException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PushException() {
		super();
	}

	public PushException(String message, Throwable cause) {
		super(message, cause);
	}

	public PushException(String message) {
		super(message);
	}

	public PushException(Throwable cause) {
		super(cause);
	}

}
