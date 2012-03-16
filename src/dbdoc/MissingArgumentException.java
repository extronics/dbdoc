package dbdoc;

/**
 * @author jk
 * 
 */
public class MissingArgumentException extends DocException {

	private static final long serialVersionUID = 4265506997902236013L;

	public MissingArgumentException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MissingArgumentException(String message, Object... args) {
		super(message, args);
		// TODO Auto-generated constructor stub
	}

	public MissingArgumentException(String message, Throwable cause,
			Object... args) {
		super(message, cause, args);
		// TODO Auto-generated constructor stub
	}

	public MissingArgumentException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MissingArgumentException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public MissingArgumentException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
