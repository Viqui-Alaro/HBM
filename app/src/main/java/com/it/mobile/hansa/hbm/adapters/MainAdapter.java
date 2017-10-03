package com.it.mobile.hansa.hbm.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.it.mobile.hansa.hbm.fragments.CalendarioMain;
import com.it.mobile.hansa.hbm.fragments.DasboardMain;
import com.it.mobile.hansa.hbm.fragments.MapaMain;
import com.it.mobile.hansa.hbm.fragments.MenuMain;
import com.it.mobile.hansa.hbm.fragments.RealizadoMain;

/**
 * Created by JTrujillo on 25/09/2017.
 */

public class MainAdapter extends FragmentPagerAdapter {

    private static final int ESCRITORIOS = 5;

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new MenuMain();
            case 1:
                return new CalendarioMain();
            case 2:
                return new MapaMain();
            case 3:
                return new RealizadoMain();
            case 4:
                return new DasboardMain();
            default:
                return new CalendarioMain();
        }

    }

    @Override
    public int getCount() {
        return ESCRITORIOS;
    }
}
