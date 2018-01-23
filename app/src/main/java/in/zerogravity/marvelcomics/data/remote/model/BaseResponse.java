package in.zerogravity.marvelcomics.data.remote.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    @SerializedName("response_code")
    public String responseCode = "";

    @SerializedName("response_message")
    public String responseMessage = "";


    /**
     * Check whether this response object is valid i.e. contains any responseCode or not
     *
     * @return true if this response contains any responseCode otherwise false
     */
    public boolean containsValidResponse(){
        if(!TextUtils.isEmpty(this.responseCode)){
            return true;
        }

        return false;
    }

}
