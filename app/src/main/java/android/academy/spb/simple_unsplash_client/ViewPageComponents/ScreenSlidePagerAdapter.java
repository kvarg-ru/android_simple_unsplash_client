package android.academy.spb.simple_unsplash_client.ViewPageComponents;

import android.academy.spb.simple_unsplash_client.MainActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by User on 25.05.2018.
 */

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private List<ScreenSlidePageFragment> fragmentList;

    public ScreenSlidePagerAdapter(FragmentManager fm, List<ScreenSlidePageFragment> fragmentList) {

        super(fm);

        this.fragmentList = fragmentList;

    }

    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);

    }

    @Override
    public int getCount() {

        return fragmentList.size();

    }
}
