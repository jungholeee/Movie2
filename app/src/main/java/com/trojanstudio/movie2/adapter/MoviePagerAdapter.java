package com.trojanstudio.movie2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.trojanstudio.movie2.fragment.MovieListFragment;

import java.util.List;

/**
 * Created by smjs2000 on 8/29/17.
 */
public class MoviePagerAdapter extends FragmentPagerAdapter {

    List<MovieListFragment> movieListFragments;

    public MoviePagerAdapter(FragmentManager fm, List<MovieListFragment> fragments) {
        super(fm);
        movieListFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return movieListFragments.get(position);
    }

    @Override
    public int getCount() {
        return movieListFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return movieListFragments.get(position).getArguments().getString(MovieListFragment.KEY_LIST_TYPE);
    }
}
