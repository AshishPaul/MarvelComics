package in.zerogravity.marvelcomics;


import android.content.Context;

import in.zerogravity.marvelcomics.data.ApiHelper;
import in.zerogravity.marvelcomics.data.remote.MockApiHelper;
import in.zerogravity.marvelcomics.data.remote.MockApiServiceImpl;

public class NetworkModule {
    public static void init(Context applicationContext){

    }

    public static ApiHelper getApiHelper() {
        return new MockApiHelper(new MockApiServiceImpl());
    }
}
