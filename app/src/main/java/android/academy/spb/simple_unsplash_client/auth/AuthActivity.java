package android.academy.spb.simple_unsplash_client.auth;

import android.academy.spb.simple_unsplash_client.App;
import android.academy.spb.simple_unsplash_client.R;
import android.academy.spb.simple_unsplash_client.net.NetworkModule;
import android.academy.spb.simple_unsplash_client.net.unsplash.Constants;
import android.academy.spb.simple_unsplash_client.net.unsplash.pojo.AuthResponse;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 01.06.2018.
 */

public class AuthActivity extends AppCompatActivity {

    public static final int AUTH_ACTIVITY_ID = 333;
    public static final String OAUTH2_ACCESS_TOKEN = "oauth2_token";


    private static final String AUTH_URL = Constants.API_AUTH_BASE_URL + "/authorize/?" +
            "client_id=" + Constants.ACCESS_KEY +
            "&redirect_uri=" + Constants.AUTH_REDIRECT_URI +
            "&response_type=code" +
            "&scope=public+write_likes";

    private static final String GRANT_TYPE = "authorization_code";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        WebView webView = findViewById(R.id.authWebView);
        webView.setWebViewClient(new AuthWebViewClient(new AuthCallback() {

            @Override
            public void onAuthorizationCodeReceived(String code) {
                //Toast.makeText(getBaseContext(), "onAuthorizationCodeReceived: " + code, Toast.LENGTH_SHORT).show();
                ((App) getApplication()).getOauth2UnsplashApi().authTokenRequest(Constants.ACCESS_KEY,
                        Constants.SECRET_KEY,
                        Constants.AUTH_REDIRECT_URI,
                        code,
                        GRANT_TYPE).enqueue(new Callback<AuthResponse>() {

                    @Override
                    public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {

                        Log.d("Oauth2UnsplashApi", "onSuccessAuthResponse");

                        if (response.code() == 200) {

                            String access_token = response.body().getAccess_token();

                            Intent intent = new Intent();
                            intent.putExtra(OAUTH2_ACCESS_TOKEN, access_token);
                            setResult(RESULT_OK, intent);


                            Log.d("Oauth2UnsplashApi", "access_token: " + access_token);

                        } else {

                            setResult(RESULT_CANCELED);

                        }

                        finish();

                    }

                    @Override
                    public void onFailure(Call<AuthResponse> call, Throwable t) {
                        Log.d("Oauth2UnsplashApi", "onFailureAuthResponse");
                        setResult(RESULT_CANCELED);
                        finish();
                    }

                });
            }

        }));

        webView.loadUrl(AUTH_URL);
    }

    public static void startForResult(Activity activity) {

        activity.startActivityForResult(new Intent(activity, AuthActivity.class), AUTH_ACTIVITY_ID);

    }
}
