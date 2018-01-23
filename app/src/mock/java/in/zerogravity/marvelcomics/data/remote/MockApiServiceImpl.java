package in.zerogravity.marvelcomics.data.remote;


import java.util.List;

import in.zerogravity.marvelcomics.data.MockDataProvider;
import in.zerogravity.marvelcomics.data.model.MarvelCharacter;

public class MockApiServiceImpl implements MockApiService {
    @Override
    public List<MarvelCharacter> getCharacters(int limit) {

        return MockDataProvider.generateMockCharacters();
    }




}
