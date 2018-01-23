package in.zerogravity.marvelcomics.data.remote;


import in.zerogravity.marvelcomics.data.remote.model.CharacterDataWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author glomadrian
 */
public interface MarvelApiService {

    @GET("/v1/public/characters")
    Call<CharacterDataWrapper> getCharacters(@Query("limit") int limit);

    @GET("/v1/public/characters")
    CharacterDataWrapper getCharacters(@Query("limit") int limit, @Query("offset") int offset);
}
