package android.academy.spb.simple_unsplash_client.storage;

import android.content.Context;
import android.support.annotation.MainThread;

/**
 * Created by User on 05.06.2018.
 */

public class PreferencesProvider {

    private PreferencesProvider() {}

    private static Preferences preferences;

    @MainThread
    public static void initialization(Context context) throws IllegalStateException {

        if (preferences == null) {

            preferences = new Preferences(context);

        } else {

            throw new IllegalStateException("You can't initialize storage twice");

        }

    }

    public static Preferences getPreferences() {

        if (preferences != null) {

            return preferences;

        } else {

            throw new IllegalStateException("Object doesn't exists. You should call initialization method first");

        }

    }
}
