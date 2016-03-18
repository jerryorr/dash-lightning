package com.jerryorr.lightning.jmx;

public class JmxRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JmxRuntimeException() {
		super();
	}

	public JmxRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public JmxRuntimeException(String message) {
		super(message);
	}

	public JmxRuntimeException(Throwable cause) {
		super(cause);
	}

}
