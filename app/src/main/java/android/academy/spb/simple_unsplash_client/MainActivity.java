package android.academy.spb.simple_unsplash_client;

import android.academy.spb.simple_unsplash_client.ViewPageComponents.ScreenSlidePageFragment;
import android.academy.spb.simple_unsplash_client.ViewPageComponents.ScreenSlidePagerAdapter;
import android.academy.spb.simple_unsplash_client.ViewPageComponents.ZoomOutPageTransformer;
import android.academy.spb.simple_unsplash_client.net.NetworkModule;
import android.academy.spb.simple_unsplash_client.net.unsplash.pojo.Collection;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = findViewById(R.id.pager);

        ((App) getApplication()).getmUnsplashApi().getCuratedCollection().enqueue(new Callback<List<Collection>>() {
            @Override
            public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {

                if (response.code() == 200) {
                    List<Collection> collectionList = response.body();

                    for (Collection collection : collectionList) {
                        CollectionRepository.getInstance().save(collection);
                    }

                    if (!collectionList.isEmpty()) {
                        createViewPage();
                    }

                    Toast.makeText(getApplicationContext(), "200 code!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error code!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Collection>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Some shit happend!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void createViewPage() {

        List<ScreenSlidePageFragment> fragmentList = new ArrayList<>();

        for (int id : CollectionRepository.getInstance().getIdList()) {
            fragmentList.add(ScreenSlidePageFragment.newInstance(id));
        }

        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), fragmentList);
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());

    }

    @Override
    public void onBackPressed() {

        if (mPager.getCurrentItem() == 0) {

            super.onBackPressed();

        } else {

            mPager.setCurrentItem(mPager.getCurrentItem() - 1);

        }

    }

}
