package com.example.mpandroidchart.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mpandroidchart.fragment.PlaceholderFragment;

import java.util.ArrayList;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments ;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public SectionsPagerAdapter(FragmentManager fm,ArrayList<Fragment> fragments) {
        this(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position+"";
    }
}
