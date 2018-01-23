package in.zerogravity.marvelcomics.data.local.db;


import java.util.List;

import in.zerogravity.marvelcomics.data.AppDataManager;
import in.zerogravity.marvelcomics.data.DbHelper;
import in.zerogravity.marvelcomics.data.model.MarvelCharacter;

public class MockDbHelper implements DbHelper {
    private final MockDataBase dataBase;

    public MockDbHelper(MockDataBase dataBase){

        this.dataBase = dataBase;
    }
    @Override
    public void getCharacterCollection(AppDataManager.Callback<List<MarvelCharacter>> callback) {
        callback.onSuccess(dataBase.getCharacters());
    }

    @Override
    public void saveCharacterCollection(List<MarvelCharacter> characterList) {

    }

    @Override
    public void deleteAllCharacters() {

    }

    @Override
    public void deleteCharacter() {

    }
}
