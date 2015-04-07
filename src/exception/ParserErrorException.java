package exception;

/**
 * Exception class for parser errors
 * @author Daniel Rehnberg
 * @version 1.0
 */
public class ParserErrorException extends Exception {

	public ParserErrorException() {
	}

	public ParserErrorException(String message) {
		super(message);
	}

	public ParserErrorException(Throwable cause) {
		super(cause);
	}

	public ParserErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParserErrorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
