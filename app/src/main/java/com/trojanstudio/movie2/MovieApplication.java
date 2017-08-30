package com.trojanstudio.movie2;

import android.app.Application;

import com.trojanstudio.movie2.api.webservice.retrofit.MovieRetrofitWebService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by smjs2000 on 8/29/17.
 */
public class MovieApplication extends Application {

    private static final String BASE_URL = "http://api.themoviedb.org/";

    private MovieRetrofitWebService movieRetrofitWebService;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();

        movieRetrofitWebService = new MovieRetrofitWebService(retrofit);
    }

    public MovieRetrofitWebService getMovieRetrofitWebService() {
        return movieRetrofitWebService;
    }
}
