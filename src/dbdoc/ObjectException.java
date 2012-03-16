package dbdoc;

/**
 * @author jk
 * 
 */
public class ObjectException extends DocException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2808300475970971167L;

	/**
	 * 
	 */
	public ObjectException() {
	}

	/**
	 * @param message
	 */
	public ObjectException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param args
	 */
	public ObjectException(String message, Object... args) {
		super(message, args);
	}

	/**
	 * @param cause
	 */
	public ObjectException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ObjectException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param args
	 */
	public ObjectException(String message, Throwable cause, Object... args) {
		super(message, cause, args);
	}

}
