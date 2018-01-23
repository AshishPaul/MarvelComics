package in.zerogravity.marvelcomics.data.local.db;


import java.util.List;

import in.zerogravity.marvelcomics.data.MockDataProvider;
import in.zerogravity.marvelcomics.data.model.MarvelCharacter;

public class MockDataBaseImpl implements MockDataBase {
    @Override
    public List<MarvelCharacter> getCharacters() {
        return MockDataProvider.generateMockCharacters();
    }
}
