package dbdoc;

import dbdoc.DocException;

/**
 * @author jk
 * 
 */
public class AssertionFailedException extends DocException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -207474819676499255L;

	public AssertionFailedException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AssertionFailedException(String message, Object... args) {
		super(message, args);
	}

	public AssertionFailedException(String message, Throwable cause,
			Object... args) {
		super(message, cause, args);
	}

	public AssertionFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public AssertionFailedException(String message) {
		super(message);
	}

	public AssertionFailedException(Throwable cause) {
		super(cause);
	}
}
