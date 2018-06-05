package android.academy.spb.simple_unsplash_client.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by User on 05.06.2018.
 */

public class Preferences {

    private final static String PREFERENCES_FILE_NAME = "Preferences";
    private final static String ACCESS_TOKEN = "AccessToken";

    private final SharedPreferences preferences;

    public Preferences(Context context) {

        this.preferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);

    }

    public void saveAccessToken(String accessToken) {

        preferences.edit().putString(ACCESS_TOKEN, accessToken).apply();

    }

    public String getAccessToken() {

        return preferences.getString(ACCESS_TOKEN, null);

    }

}
