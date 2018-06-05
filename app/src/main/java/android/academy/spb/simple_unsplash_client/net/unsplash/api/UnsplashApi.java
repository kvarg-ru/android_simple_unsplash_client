package android.academy.spb.simple_unsplash_client.net.unsplash.api;

import android.academy.spb.simple_unsplash_client.net.unsplash.pojo.Collection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by User on 24.05.2018.
 */

public interface UnsplashApi {

    @GET("collections/curated")
    Call<List<Collection>> getCuratedCollection();

}
