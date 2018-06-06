package android.academy.spb.simple_unsplash_client;

import android.academy.spb.simple_unsplash_client.auth.Oauth2UnsplashApi;
import android.academy.spb.simple_unsplash_client.net.NetworkModule;
import android.academy.spb.simple_unsplash_client.net.unsplash.api.UnsplashApi;
import android.academy.spb.simple_unsplash_client.storage.PreferencesProvider;
import android.app.Application;

/**
 * Created by User on 25.05.2018.
 */

public class App extends Application {

    private UnsplashApi mUnsplashApi;
    private Oauth2UnsplashApi mOauth2UnsplashApi;

    @Override
    public void onCreate() {
        super.onCreate();

        NetworkModule networkModule = new NetworkModule(this);

        mUnsplashApi =  networkModule.getUnsplashApi();
        mOauth2UnsplashApi = networkModule.getOauth2UnsplashApi();

        PreferencesProvider.initialization(this);

    }

    public UnsplashApi getUnsplashApi() {

        return mUnsplashApi;

    }

    public Oauth2UnsplashApi getOauth2UnsplashApi() {

        return mOauth2UnsplashApi;

    }

}
