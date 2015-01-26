package com.manturf.manturf.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.astuetz.PagerSlidingTabStrip;
import com.manturf.manturf.Fragments.Favorites;
import com.manturf.manturf.Fragments.Messeage;
import com.manturf.manturf.Fragments.Profile;
import com.manturf.manturf.Fragments.Timeline;
import com.manturf.manturf.Fragments.TopTimeLine;
import com.manturf.manturf.R;


/**
 * Created by RyoSakaguchi on 15/01/12.
 */
public class TabsPagerAdapter extends FragmentStatePagerAdapter implements PagerSlidingTabStrip.IconTabProvider{

    private final String[] pageTitle = {"ホーム","おすすめ","メッセージ","お気に入り","プロフィール"};

    private final int[] titles = { R.drawable.ic_action_action_home, R.drawable.ic_action_action_list,
            R.drawable.ic_action_communication_message, R.drawable.ic_action_action_star_rate,R.drawable.ic_action_action_face_unlock, };

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new TopTimeLine();
            case 1:
                // Games fragment activity
                return new Timeline();
            case 2:
                // Movies fragment activity
                return new Messeage();
            case 3:
                // Movies fragment activity
                return new Favorites();
            case 4:
                // Movies fragment activity
                return new Profile();

        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return titles.length;
    }


    @Override
    public int getPageIconResId(int position) {
        return titles[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle[position];
    }
}
