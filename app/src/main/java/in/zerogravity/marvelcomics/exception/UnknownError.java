package in.zerogravity.marvelcomics.exception;

public class UnknownError extends BaseException {
    public UnknownError(String cause) {
        super(cause);
    }

    public UnknownError(String causeOfError, String suggestion) {
        super(causeOfError, suggestion);
    }

//    public UnknownError(){
//        cause = ResourceUtils.instance().getString(R.string.alert_unknown_error);
//        suggestion = ResourceUtils.instance().getString(R.string.alert_common_suggestion);
//    }
}
