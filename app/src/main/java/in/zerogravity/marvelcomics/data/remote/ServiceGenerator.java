package in.zerogravity.marvelcomics.data.remote;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import in.zerogravity.marvelcomics.BuildConfig;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class ServiceGenerator{
    private static ServiceGenerator instance;
    private static Retrofit retrofit;

    private ServiceGenerator(){

    }

    public static ServiceGenerator getInstance(){
        synchronized (ServiceGenerator.class) {
            if (instance == null) {
                instance = new ServiceGenerator();
            }
        }
        return instance;
    }

    public synchronized void init(File cacheFile, Interceptor apiInterceptor, Interceptor loggingInterceptor,Converter.Factory converterFactory){
        synchronized (ServiceGenerator.class) {
            Cache cache = null;
            try {
                cache = new Cache(cacheFile, 10 * 1024 * 1024);
            } catch (Exception e) {
                e.printStackTrace();
            }

            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                    .addInterceptor(apiInterceptor)
                    .cache(cache)
                    .connectTimeout(90, TimeUnit.SECONDS)
                    .writeTimeout(90, TimeUnit.SECONDS)
                    .readTimeout(90, TimeUnit.SECONDS);

            Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_BASE_URL)

                    .addConverterFactory(converterFactory);

            if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            // set your desired log level
//            logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
//            // add your other interceptors …
//            // add logging as last interceptor
                okHttpClientBuilder.addInterceptor(loggingInterceptor);
            }
//
//        Gson gson = new GsonBuilder()
//                .serializeNulls()
//                .create();
//
//        builder.addConverterFactory(GsonConverterFactory.create(gson));

//        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
//        builder.addConverterFactory(GsonConverterFactory.create(gson));

            retrofit = retrofitBuilder.client(okHttpClientBuilder.build()).build();
        }
    }

    public static Retrofit retrofit() {
        return retrofit;
    }

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    public static class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

            Class<T> rawType = (Class<T>) type.getRawType();
            if (rawType != String.class) {
                return null;
            }
            return (TypeAdapter<T>) new StringAdapter();
        }
    }

    public static class StringAdapter extends TypeAdapter<String> {
        public String read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return "";
            }
            return reader.nextString().equalsIgnoreCase("null") ? "" : reader.nextString();
        }

        public void write(JsonWriter writer, String value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }

}
