package com.trojanstudio.movie2.api.webservice.retrofit;

import com.trojanstudio.movie2.api.model.MovieDB;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by smjs2000 on 8/29/17.
 */
public interface MovieRetrofitAPI {

    @GET("/3/movie/{list_type}")
    Single<MovieDB> getMovies(@Path("list_type")String listType, @Query("api_key")String apiKey);
}
