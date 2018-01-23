package in.zerogravity.marvelcomics.data;


import java.util.List;

import in.zerogravity.marvelcomics.data.model.MarvelCharacter;

public interface DbHelper {
    void getCharacterCollection(final AppDataManager.Callback<List<MarvelCharacter>> callback);
    void saveCharacterCollection(List<MarvelCharacter> characterList);
    void deleteAllCharacters();
    void deleteCharacter();
}
