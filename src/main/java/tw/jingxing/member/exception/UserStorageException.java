package tw.jingxing.member.exception;

public class UserStorageException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserStorageException(String message) {
        super(message);
    }
}