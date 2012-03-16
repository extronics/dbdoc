package dbdoc;

import dbdoc.DocException;

/**
 * @author jk
 * 
 */
public class InvalidArgumentException extends DocException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5431093203749006901L;

	public InvalidArgumentException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidArgumentException(String message, Object... args) {
		super(message, args);
		// TODO Auto-generated constructor stub
	}

	public InvalidArgumentException(String message, Throwable cause,
			Object... args) {
		super(message, cause, args);
		// TODO Auto-generated constructor stub
	}

	public InvalidArgumentException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidArgumentException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidArgumentException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
