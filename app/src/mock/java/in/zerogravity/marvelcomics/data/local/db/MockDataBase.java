package in.zerogravity.marvelcomics.data.local.db;


import java.util.List;

import in.zerogravity.marvelcomics.data.model.MarvelCharacter;

public interface MockDataBase {
    List<MarvelCharacter> getCharacters();
}
