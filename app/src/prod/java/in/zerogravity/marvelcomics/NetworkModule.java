package in.zerogravity.marvelcomics;


import android.content.Context;

import java.io.File;

import in.zerogravity.marvelcomics.data.ApiHelper;
import in.zerogravity.marvelcomics.data.remote.MarvelApiHelper;
import in.zerogravity.marvelcomics.data.remote.MarvelApiService;
import in.zerogravity.marvelcomics.data.remote.ResponseMapper;
import in.zerogravity.marvelcomics.data.remote.ServiceGenerator;
import in.zerogravity.marvelcomics.data.remote.interceptor.MarvelRequestInterceptor;
import in.zerogravity.marvelcomics.data.remote.mapper.MarvelApiResponseMapper;
import in.zerogravity.marvelcomics.data.remote.mapper.RetrofitErrorMapper;
import in.zerogravity.marvelcomics.utils.ErrorMapper;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkModule {
    public static void init(Context applicationContext){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        ServiceGenerator.getInstance().init(new File(applicationContext.getCacheDir(), "responses"),
                new MarvelRequestInterceptor(NetworkConfig.PUBLIC_KEY, NetworkConfig.PRIVATE_KEY),logging, GsonConverterFactory.create());
    }

    public static ApiHelper getApiHelper() {
        return getApiHelper(getApiService(), getApiResponseMapper(), getApiErrorMapper());
    }

    public static ApiHelper getApiHelper(MarvelApiService apiService, ResponseMapper apiResponseMapper, ErrorMapper errorMapper) {
        return new MarvelApiHelper(apiService, apiResponseMapper, errorMapper);
    }

    public static MarvelApiService getApiService(){
        return ServiceGenerator.createService(MarvelApiService.class);
    }

    public static ResponseMapper getApiResponseMapper() {
        return new MarvelApiResponseMapper();
    }

    public static ErrorMapper getApiErrorMapper() {
        return new RetrofitErrorMapper();
    }
}
