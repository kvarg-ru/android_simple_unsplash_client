package android.academy.spb.simple_unsplash_client;

import android.academy.spb.simple_unsplash_client.PreviewFragment.PreviewFragment;
import android.academy.spb.simple_unsplash_client.ViewPageComponents.ScreenSlidePageFragment;
import android.academy.spb.simple_unsplash_client.ViewPageComponents.ScreenSlidePagerAdapter;
import android.academy.spb.simple_unsplash_client.ViewPageComponents.ZoomOutPageTransformer;
import android.academy.spb.simple_unsplash_client.auth.AuthActivity;
import android.academy.spb.simple_unsplash_client.net.NetworkModule;
import android.academy.spb.simple_unsplash_client.net.unsplash.pojo.Collection;
import android.academy.spb.simple_unsplash_client.storage.Preferences;
import android.academy.spb.simple_unsplash_client.storage.PreferencesProvider;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ScreenSlidePageFragment.FragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (PreferencesProvider.getPreferences().getAccessToken() == null) {
            AuthActivity.startForResult(this);
        }

        if (savedInstanceState == null) {
            openFragment(MainFragment.newInstance(), false);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AuthActivity.AUTH_ACTIVITY_ID) {
            if (resultCode == RESULT_OK) {

                String access_token = data.getStringExtra(AuthActivity.OAUTH2_ACCESS_TOKEN);
                PreferencesProvider.getPreferences().saveAccessToken(access_token);

                Log.d("MainActivity", "access_token: " + access_token);

            } else {

                Toast.makeText(this, "Authorization error!", Toast.LENGTH_LONG).show();

            }
        }
    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {

            super.onBackPressed();

        } else {

            getSupportFragmentManager().popBackStack();

        }

    }

    @Override
    public void onPreviewCollectionClick(int collectionId) {

        openFragment(PreviewFragment.newInstance(collectionId), true);

    }

    private void openFragment(Fragment fragment, boolean addToBackStack) {

        FragmentTransaction transaction = getSupportFragmentManager()
                                                .beginTransaction()
                                                .setCustomAnimations(
                                                        R.anim.slide_in_right, R.anim.slide_out_left,
                                                        R.anim.slide_in_left, R.anim.slide_out_right);

        if (addToBackStack) {

            transaction.addToBackStack(null);

        }

        transaction
                .replace(R.id.content, fragment)
                .commit();

    }

}
