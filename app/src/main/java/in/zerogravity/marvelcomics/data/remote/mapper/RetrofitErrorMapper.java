package in.zerogravity.marvelcomics.data.remote.mapper;


import java.io.IOException;
import java.lang.annotation.Annotation;

import in.zerogravity.marvelcomics.data.remote.ServiceGenerator;
import in.zerogravity.marvelcomics.data.remote.model.BaseResponse;
import in.zerogravity.marvelcomics.exception.BaseException;
import in.zerogravity.marvelcomics.exception.ErrorMapper;
import in.zerogravity.marvelcomics.exception.NetworkError;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class RetrofitErrorMapper implements ErrorMapper<Response<?> > {
    @Override
    public BaseException mapError(Response<?> response) {
        Converter<ResponseBody, BaseResponse> converter =
                ServiceGenerator.retrofit()
                        .responseBodyConverter(BaseResponse.class, new Annotation[0]);

        BaseResponse error;

        //TODO implement later
        try {
            error = converter.convert(response.errorBody());

//            if (error != null) {
//
//                return new NetworkError();
//            } else {
                return new NetworkError("");
//            }
        } catch (IOException e) {
            return new NetworkError("");
        }
    }

}
