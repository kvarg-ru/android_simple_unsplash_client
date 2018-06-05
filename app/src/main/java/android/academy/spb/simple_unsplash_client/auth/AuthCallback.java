package android.academy.spb.simple_unsplash_client.auth;

/**
 * Created by User on 04.06.2018.
 */

public interface AuthCallback {

    public void onAuthorizationCodeReceived(String code);

}
