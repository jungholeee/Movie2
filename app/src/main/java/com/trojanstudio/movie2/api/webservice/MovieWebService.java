package com.trojanstudio.movie2.api.webservice;

import com.trojanstudio.movie2.api.model.Movie;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by smjs2000 on 8/29/17.
 */
public interface MovieWebService {
    Single<List<Movie>> fetchMovieList(String listType, String apiKey);
}
