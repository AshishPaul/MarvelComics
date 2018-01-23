package in.zerogravity.marvelcomics.data;


import android.support.annotation.NonNull;

import in.zerogravity.marvelcomics.exception.BaseException;

public abstract class AppDataManager {
    public interface Callback<T>{
        void onSuccess(T result);
        void onError(BaseException error);
    }

    final DbHelper dbHelper;
    final ApiHelper apiHelper;
    protected AppDataManager(@NonNull DbHelper dbHelper, @NonNull ApiHelper apiHelper){
        this.dbHelper = dbHelper;
        this.apiHelper = apiHelper;
    }


}
