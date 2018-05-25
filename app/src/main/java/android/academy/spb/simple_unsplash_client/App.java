package android.academy.spb.simple_unsplash_client;

import android.academy.spb.simple_unsplash_client.net.NetworkModule;
import android.academy.spb.simple_unsplash_client.net.unsplash.api.UnsplashApi;
import android.app.Application;

/**
 * Created by User on 25.05.2018.
 */

public class App extends Application {

    private UnsplashApi mUnsplashApi;

    @Override
    public void onCreate() {
        super.onCreate();

        mUnsplashApi = new NetworkModule(this).getUnsplashApi();

    }

    public UnsplashApi getmUnsplashApi() {

        return mUnsplashApi;

    }
}
