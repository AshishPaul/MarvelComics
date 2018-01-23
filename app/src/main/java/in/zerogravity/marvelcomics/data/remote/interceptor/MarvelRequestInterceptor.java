package in.zerogravity.marvelcomics.data.remote.interceptor;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;


/**
 * Interceptor to add the auth key, and generate the hash for every request
 * The data is exposed in the constructor
 *
 * @author glomadrian @ com.github.glomadrian.mvpcleanarchitecture
 * @ModifiedBy zeroG
 */
public class MarvelRequestInterceptor implements Interceptor {
    private static final String PARAM_KEY = "apikey";
    private static final String PARAM_TIMESTAMP = "ts";
    private static final String PARAM_HASH = "hash";

    private String publicKey;
    private String privateKey;

    public MarvelRequestInterceptor(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String timeStamp = generateTimestamp();
        HttpUrl url = originalRequest.url().newBuilder()
                .addQueryParameter(PARAM_TIMESTAMP, timeStamp)
                .addQueryParameter(PARAM_KEY, publicKey)
                .addQueryParameter(PARAM_HASH, generateMarvelHash(timeStamp, privateKey, publicKey))
                .build();
        Request.Builder newRequestBuilder = originalRequest.newBuilder().url(url);
        Response response = chain.proceed(newRequestBuilder.build());
        response.cacheResponse();
        return chain.proceed(newRequestBuilder.build());
    }

    private String generateTimestamp() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return String.valueOf(timestamp.getTime());
    }


    private String generateMarvelHash(String timeStamp, String privateKey, String publicKey) {

//        try {
//            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//            String marvelHash = timeStamp + privateKey + publicKey;
//            byte[] bytes = marvelHash.getBytes();
//            return new BigInteger(SIGNUM, messageDigest.digest(bytes)).toString(BYTES);
//
//        } catch (NoSuchAlgorithmException e) {
//            Timber.e(e);
//            return "invalid";
//        }

        try {
            String value = String.valueOf(System.currentTimeMillis()) + privateKey + publicKey;
            MessageDigest md5Encoder = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5Encoder.digest(value.getBytes());

            StringBuilder md5 = new StringBuilder();
            for (int i = 0; i < md5Bytes.length; ++i) {
                md5.append(Integer.toHexString((md5Bytes[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return md5.toString();
        }catch (NoSuchAlgorithmException e) {
            Timber.e(e);
            return "invalid";
        }
    }


}
