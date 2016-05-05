package gyndroids.com.savingwatertips.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import gyndroids.com.savingwatertips.ui.fragments.FragmentIntro;
import gyndroids.com.savingwatertips.utils.Configs;

/**
 * Saving Water Tips
 * Created by joao on 5/4/16.
 */
public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentIntro.newInstance(position);
    }

    @Override
    public int getCount() {
        return Configs.NUM_PAGES;
    }
}
