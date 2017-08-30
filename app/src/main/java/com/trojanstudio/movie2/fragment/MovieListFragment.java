package com.trojanstudio.movie2.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trojanstudio.movie2.MovieApplication;
import com.trojanstudio.movie2.R;
import com.trojanstudio.movie2.adapter.MovieListAdapter;
import com.trojanstudio.movie2.api.model.Movie;
import com.trojanstudio.movie2.databinding.FragmentMovieListBinding;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by smjs2000 on 8/29/17.
 */
public class MovieListFragment extends Fragment {

    public static final String KEY_LIST_TYPE = "LIST_TYPE";

    public static MovieListFragment newInstance(String listType) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_LIST_TYPE, listType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final FragmentMovieListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false);

        binding.movieRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        ((MovieApplication) getActivity().getApplication()).getMovieRetrofitWebService()
                .fetchMovieList(getArguments().getString(KEY_LIST_TYPE), getString(R.string.api_key))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        binding.movieRecyclerView.setAdapter(new MovieListAdapter(movies));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(MovieListFragment.class.getSimpleName(), "Fail to load");
                    }
                });

        return binding.getRoot();
    }
}
