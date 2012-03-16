package dbdoc;

/**
 * @author jk
 */
public class DocException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -436063258879473105L;

	/**
	 * 
	 */
	public DocException() {
		super();
	}

	/**
	 * @param message
	 */
	public DocException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param args
	 */
	public DocException(String message, Object... args) {
		super(String.format(message, args));
	}

	/**
	 * @param cause
	 */
	public DocException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DocException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DocException(String message, Throwable cause, Object... args) {
		super(String.format(message, args), cause);
	}
}
