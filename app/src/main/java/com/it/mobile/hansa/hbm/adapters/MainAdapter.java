package com.it.mobile.hansa.hbm.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.it.mobile.hansa.hbm.fragments.CalendarioMain;
import com.it.mobile.hansa.hbm.fragments.DasboardMain;
import com.it.mobile.hansa.hbm.fragments.MapaMain;

/**
 * Created by JTrujillo on 25/09/2017.
 */

public class MainAdapter extends FragmentPagerAdapter {

    private static final int ESCRITORIOS = 3;

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment f = null;

        switch (position) {
            case 0:
                f = new MapaMain();
                break;
            case 1:
                f = new CalendarioMain();
                break;
            case 2:
                f = new DasboardMain();
                break;
            default:
                f = new MapaMain();
                break;
        }

        return  f;

    }

    @Override
    public int getCount() {
        return ESCRITORIOS;
    }
}
