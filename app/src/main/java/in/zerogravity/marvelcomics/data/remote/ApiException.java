package in.zerogravity.marvelcomics.data.remote;

import in.zerogravity.marvelcomics.exception.BaseException;


public class ApiException extends BaseException {
    public ApiException(String cause) {
        super(cause);
    }

    public ApiException(String causeOfError, String suggestion) {
        super(causeOfError, suggestion);
    }
}
