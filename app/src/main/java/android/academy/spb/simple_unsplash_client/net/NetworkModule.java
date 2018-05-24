package android.academy.spb.simple_unsplash_client.net;

import android.academy.spb.simple_unsplash_client.net.unsplash.Constants;
import android.academy.spb.simple_unsplash_client.net.unsplash.api.UnsplashApi;
import android.content.Context;
import android.util.Log;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 24.05.2018.
 */

public class NetworkModule {

    private final static long HTTP_CACHE_SIZE = 1024 * 1024 * 10; // 10 MB

    private UnsplashApi mUnsplashApi;

    public NetworkModule(Context context) {

        OkHttpClient okHttpClient = provideOkHttpClient(context, provideInterceptor());
        Retrofit retrofit = provideRetrofit(okHttpClient);
        mUnsplashApi = provideUnsplashApi(retrofit);

        //providePicasso(context, okHttpClient);

    }

    public UnsplashApi getUnsplashApi() {
        return mUnsplashApi;
    }

    private UnsplashApi provideUnsplashApi(Retrofit retrofit) {
        return retrofit.create(UnsplashApi.class);
    }

    private Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(Constants.API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient provideOkHttpClient(Context context, Interceptor interceptor) {

        HttpLoggingInterceptor.Logger logger = new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("OkHttpLog", message);
            }
        };

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(logger).setLevel(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor)
                .cache(new Cache(new File(context.getCacheDir(), "SIMPLE_UNSPLASH_CLIENT_HTTP_CACHE"), HTTP_CACHE_SIZE))
                .build();

        return okHttpClient;
    }

    private Interceptor provideInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl urlWithApiKey = originalHttpUrl.newBuilder()
                        .addQueryParameter("client_id", Constants.ACCESS_KEY)
                        .build();

                Request updatedRequest = original.newBuilder().url(urlWithApiKey).build();

                return chain.proceed(updatedRequest);
            }
        };
    }

    private Picasso providePicasso(Context context, OkHttpClient okHttpClient) {
        Picasso picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();

        Picasso.setSingletonInstance(picasso);

        return picasso;
    }
}
