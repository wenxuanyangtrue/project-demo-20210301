package top.xsword.exception;

public class UserFindException extends RuntimeException{
    public UserFindException() {
    }

    public UserFindException(String message) {
        super(message);
    }

    public UserFindException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserFindException(Throwable cause) {
        super(cause);
    }

    public UserFindException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
