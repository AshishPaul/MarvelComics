package in.zerogravity.marvelcomics.data.remote;


import java.util.List;

import in.zerogravity.marvelcomics.data.ApiHelper;
import in.zerogravity.marvelcomics.data.AppDataManager;
import in.zerogravity.marvelcomics.data.model.MarvelCharacter;
import in.zerogravity.marvelcomics.data.remote.model.CharacterDataWrapper;
import in.zerogravity.marvelcomics.exception.ErrorMapper;
import retrofit2.Call;
import retrofit2.Response;

public class MarvelApiHelper implements ApiHelper {
    private final MarvelApiService marvelApiService;
    private final ResponseMapper responseMapper;
    private final ErrorMapper errorMapper;

    public MarvelApiHelper(MarvelApiService marvelApiService, ResponseMapper responseMapper, ErrorMapper errorMapper){
        this.marvelApiService = marvelApiService;
        this.responseMapper = responseMapper;
        this.errorMapper = errorMapper;
    }

    @Override
    public void getCharacterCollection(int limit, final AppDataManager.Callback<List<MarvelCharacter>> callback) {
        Call<CharacterDataWrapper> call = marvelApiService.getCharacters(limit);
        call.enqueue(new retrofit2.Callback<CharacterDataWrapper>() {

            @Override
            public void onResponse(Call<CharacterDataWrapper> call, Response<CharacterDataWrapper> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(responseMapper.mapResponse(response.body()));
                } else {
                    // This may be the case where HTTP status codes is not set as 200-OK in server
                    // In that case, check if server has sent any JSON response even if the HTTP code is not success.
//                    BaseResponse errorResponse = RetrofitErrorParser.getBaseErrorResponse(response);
//
//                    if (errorResponse != null && errorResponse.containsValidResponse()) {
//                        callback.onSuccess(errorResponse);
//                    } else {
//                        callback.onError(new NetworkError());
//                    }
                }
            }

            @Override
            public void onFailure(Call<CharacterDataWrapper> call, Throwable t) {
                callback.onError(errorMapper.mapError(t));
            }
        });
    }

//    @Override
//    public List<MarvelCharacter> getCharacterCollectionPaginated(int limit, int offset) throws GetCharactersException {
//        try {
//            CharacterDataWrapper characterDataWrapper = marvelAPI.getCharacters(limit, offset);
//            //Map response from api to domain model
//            return responseMapper.mapResponse(characterDataWrapper);
//        } catch (RetrofitError retrofitError) {
//            Log.e(LogUtils.generateTag(this), "Error on marvel api repository");
//            GetCharactersException getCharactersException = new GetCharactersException();
//            getCharactersException.setStackTrace(retrofitError.getStackTrace());
//            throw getCharactersException;
//        }
//    }
}
