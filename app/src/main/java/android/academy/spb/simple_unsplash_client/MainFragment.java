package android.academy.spb.simple_unsplash_client;

import android.academy.spb.simple_unsplash_client.ViewPageComponents.ScreenSlidePageFragment;
import android.academy.spb.simple_unsplash_client.ViewPageComponents.ScreenSlidePagerAdapter;
import android.academy.spb.simple_unsplash_client.ViewPageComponents.ZoomOutPageTransformer;
import android.academy.spb.simple_unsplash_client.net.unsplash.pojo.Collection;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 28.05.2018.
 */

public class MainFragment extends Fragment {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        mPager = rootView.findViewById(R.id.pager);

        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((App) getActivity().getApplication()).getmUnsplashApi().getCuratedCollection().enqueue(new Callback<List<Collection>>() {
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

                    Toast.makeText(getContext(), "200 code!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "Error code!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Collection>> call, Throwable t) {

                Toast.makeText(getContext(), "Some shit happend!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void createViewPage() {

        List<ScreenSlidePageFragment> fragmentList = new ArrayList<>();

        for (int id : CollectionRepository.getInstance().getIdList()) {
            fragmentList.add(ScreenSlidePageFragment.newInstance(id));
        }

        mPagerAdapter = new ScreenSlidePagerAdapter(getChildFragmentManager(), fragmentList);
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());

    }

    public static MainFragment newInstance() {

        return new MainFragment();

    }

}
