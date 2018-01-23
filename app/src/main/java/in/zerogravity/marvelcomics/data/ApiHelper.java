package in.zerogravity.marvelcomics.data;


import java.util.List;

import in.zerogravity.marvelcomics.data.model.MarvelCharacter;

public interface ApiHelper  {
    void getCharacterCollection(int limit, final AppDataManager.Callback<List<MarvelCharacter>> callback);
}
