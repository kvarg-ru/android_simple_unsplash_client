package android.academy.spb.simple_unsplash_client.auth;

import android.academy.spb.simple_unsplash_client.net.unsplash.pojo.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

/**
 * Created by User on 04.06.2018.
 */

public interface Oauth2UnsplashApi {

    @POST("token/")
    @FormUrlEncoded
    Call<AuthResponse> authTokenRequest(@Field("client_id") String clientID,
                                        @Field("client_secret") String clientSecret,
                                        @Field("redirect_uri") String redirectUri,
                                        @Field("code") String code,
                                        @Field("grant_type") String grantType);
}
