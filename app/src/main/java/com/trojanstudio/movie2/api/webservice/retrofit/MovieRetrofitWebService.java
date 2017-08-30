package com.trojanstudio.movie2.api.webservice.retrofit;

import com.trojanstudio.movie2.api.model.Movie;
import com.trojanstudio.movie2.api.model.MovieDB;
import com.trojanstudio.movie2.api.webservice.MovieWebService;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by smjs2000 on 8/29/17.
 */
public class MovieRetrofitWebService implements MovieWebService {

    private MovieRetrofitAPI movieRetrofitAPI;

    public MovieRetrofitWebService(Retrofit retrofit) {
        movieRetrofitAPI = retrofit.create(MovieRetrofitAPI.class);
    }

    @Override
    public Single<List<Movie>> fetchMovieList(String listType, String apiKey) {
        return movieRetrofitAPI.getMovies(listType, apiKey)
                .observeOn(Schedulers.computation()).map(new Function<MovieDB, List<Movie>>() {
                    @Override
                    public List<Movie> apply(@NonNull MovieDB movieDB) throws Exception {
                        return movieDB.getResults();
                    }
                });
    }
}
