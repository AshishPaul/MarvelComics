package in.zerogravity.marvelcomics.data.remote;


import java.util.List;

import in.zerogravity.marvelcomics.data.model.MarvelCharacter;

public interface MockApiService{
    List<MarvelCharacter> getCharacters(int limit);

}
