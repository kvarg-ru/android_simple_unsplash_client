package android.academy.spb.simple_unsplash_client.net.unsplash.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 01.06.2018.
 */

public class AuthResponse {

    @SerializedName("access_token")
    @Expose
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }
}
