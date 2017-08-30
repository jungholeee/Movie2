package com.trojanstudio.movie2;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trojanstudio.movie2.adapter.MoviePagerAdapter;
import com.trojanstudio.movie2.databinding.ActivityMainBinding;
import com.trojanstudio.movie2.fragment.MovieListFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);

        List<MovieListFragment> movieFragments;
        movieFragments = new ArrayList<>();
        movieFragments.add(MovieListFragment.newInstance(getString(R.string.popular)));
        movieFragments.add(MovieListFragment.newInstance(getString(R.string.upcoming)));

        MoviePagerAdapter adapter = new MoviePagerAdapter(getSupportFragmentManager(), movieFragments);
        binding.viewpager.setAdapter(adapter);
        binding.tabs.setupWithViewPager(binding.viewpager);
    }
}
