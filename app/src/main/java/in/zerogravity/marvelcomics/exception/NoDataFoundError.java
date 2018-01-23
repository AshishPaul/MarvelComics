package in.zerogravity.marvelcomics.exception;


public class NoDataFoundError extends BaseException {
    public NoDataFoundError(String cause) {
        super(cause);
    }

    public NoDataFoundError(String causeOfError, String suggestion) {
        super(causeOfError, suggestion);
    }
}
