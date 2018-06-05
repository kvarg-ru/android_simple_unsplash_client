package android.academy.spb.simple_unsplash_client.auth;

import android.academy.spb.simple_unsplash_client.storage.Preferences;
import android.academy.spb.simple_unsplash_client.storage.PreferencesProvider;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by User on 04.06.2018.
 */

public class AuthInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        String accessToken = PreferencesProvider.getPreferences().getAccessToken();
        if (accessToken != null) {
            request = request.newBuilder().header("Authorization", "Bearer " + accessToken).build();
        }

        return chain.proceed(request);
    }

}
