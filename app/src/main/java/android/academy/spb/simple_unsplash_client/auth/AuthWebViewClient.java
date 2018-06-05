package android.academy.spb.simple_unsplash_client.auth;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by User on 04.06.2018.
 */

public class AuthWebViewClient extends WebViewClient {

    private final static String AUTH_RETURN_CODE_URL = "https://unsplash.com/oauth/authorize/";

    private AuthCallback callback;

    public AuthWebViewClient(AuthCallback callback) {
        this.callback = callback;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.startsWith(AUTH_RETURN_CODE_URL)) {
            String authCode = url.substring(AUTH_RETURN_CODE_URL.length());
            callback.onAuthorizationCodeReceived(authCode);
        }
        Log.d("WebView", url);
        return super.shouldOverrideUrlLoading(view, url);
    }
}
