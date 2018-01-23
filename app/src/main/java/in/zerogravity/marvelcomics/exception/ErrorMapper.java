package in.zerogravity.marvelcomics.exception;


public interface ErrorMapper<T> {
    BaseException mapError(T error);
    default BaseException mapError(Throwable error){
//        if(t==null){
//            return new UnknownError();
//        }
//
//        if (t instanceof SocketTimeoutException
//                || t instanceof ConnectTimeoutException
//                || t instanceof ConnectException
//                || t instanceof UnknownHostException
//                || t instanceof SSLPeerUnverifiedException
//                || t instanceof SSLHandshakeException) {
//            return new NetworkError(t);
//        }else{
//            return new UnknownError();
//        }
        //TODO implement later
        return new UnknownError("");
    }
}
