package in.zerogravity.marvelcomics.data;


import android.support.annotation.NonNull;

import java.util.List;

import hugo.weaving.DebugLog;
import in.zerogravity.marvelcomics.data.model.MarvelCharacter;
import in.zerogravity.marvelcomics.exception.BaseException;
import timber.log.Timber;

public class CharacterDataManager extends AppDataManager {
    public CharacterDataManager(@NonNull DbHelper dbHelper, @NonNull ApiHelper apiHelper) {
        super(dbHelper, apiHelper);
    }

    @DebugLog
    private void saveDataToLocalDb(List<MarvelCharacter> result) {
        dbHelper.saveCharacterCollection(result);
    }

    @DebugLog
    private void getDataFromLocalDb(final Callback<List<MarvelCharacter>> callback){
        dbHelper.getCharacterCollection(callback);
    }

    @DebugLog
    private void getDataFromRemoteApi(int limit, final Callback<List<MarvelCharacter>> callback){
        apiHelper.getCharacterCollection(limit,callback);
    }

    @DebugLog
    public void getCharacterCollection(boolean forceRefresh,int limit, final Callback<List<MarvelCharacter>> callback) {
        Timber.d("getCharacterCollection:forceRefresh:"+forceRefresh);
        if(forceRefresh){
            //If data need to be force refreshed, e.g Pull down to refresh,
            // Call remote Api
            getDataFromRemoteApi(limit, new Callback<List<MarvelCharacter>>() {
                @Override
                public void onSuccess(List<MarvelCharacter> result) {
                    //save data to local db
                    saveDataToLocalDb(result);
                    //call local data fetch process with provided callback
                    getDataFromLocalDb(callback);
                }

                @Override
                public void onError(BaseException error) {
                    callback.onError(error);
                }
            });
        }else{
            //If no force refresh required, fetch data from local db
            getDataFromLocalDb(new Callback<List<MarvelCharacter>>() {
                @Override
                public void onSuccess(List<MarvelCharacter> result) {
                    callback.onSuccess(result);
                }

                @Override
                public void onError(BaseException error) {
                    //if local db fetch fails, try the remote api
                    getDataFromRemoteApi(limit,callback);
                }
            });
        }

    }
}
