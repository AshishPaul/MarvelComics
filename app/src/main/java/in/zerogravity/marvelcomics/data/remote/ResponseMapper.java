package in.zerogravity.marvelcomics.data.remote;


import java.util.List;

/**
 * The domain model is different from others resources models, in each case the repository must map
 * the response to a basic model
 *
 * @author glomadrian
 */
public interface ResponseMapper<R,P> {
    List<R> mapResponse(P response);
}
