package in.zerogravity.marvelcomics.data.local.db;


import java.util.List;

import in.zerogravity.marvelcomics.data.AppDataManager;
import in.zerogravity.marvelcomics.data.DbHelper;
import in.zerogravity.marvelcomics.data.model.MarvelCharacter;
import in.zerogravity.marvelcomics.exception.UnknownError;

public class AppDbHelperImpl implements DbHelper{

    private final AppDataBase appDataBase;

    public AppDbHelperImpl(AppDataBase appDataBase){
        this.appDataBase = appDataBase;
    }

    @Override
    public void getCharacterCollection(AppDataManager.Callback<List<MarvelCharacter>> callback) {
        callback.onError(new UnknownError("Local db not implemented yet"));
    }

    @Override
    public void saveCharacterCollection(List<MarvelCharacter> characterList) {
        throw new RuntimeException("Local db not implemented yet");
    }

    @Override
    public void deleteAllCharacters() {
        throw new RuntimeException("Local db not implemented yet");
    }

    @Override
    public void deleteCharacter() {
        throw new RuntimeException("Local db not implemented yet");
    }
}
