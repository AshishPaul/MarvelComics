package in.zerogravity.marvelcomics.data.remote;


import java.util.List;

import in.zerogravity.marvelcomics.data.ApiHelper;
import in.zerogravity.marvelcomics.data.AppDataManager;
import in.zerogravity.marvelcomics.data.model.MarvelCharacter;

public class MockApiHelper implements ApiHelper {

    private final MockApiService mockApiService;

    public MockApiHelper(MockApiService mockApiService){
        this.mockApiService = mockApiService;
    }

    @Override
    public void getCharacterCollection(int limit, final AppDataManager.Callback<List<MarvelCharacter>> callback) {
        callback.onSuccess(mockApiService.getCharacters(limit));
    }
}
