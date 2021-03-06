package android.academy.spb.simple_unsplash_client.net.unsplash.api;

import android.academy.spb.simple_unsplash_client.net.unsplash.pojo.Collection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by User on 24.05.2018.
 */

public interface UnsplashApi {

    @GET("collections/curated")
    Call<List<Collection>> getCuratedCollection();

    @POST("photos/{photo_id}/like")
    Call<Void> likePhoto(@Path("photo_id") String photoId);

    @DELETE("photos/{photo_id}/like")
    Call<Void> unlikePhoto(@Path("photo_id") String photoId);

}
