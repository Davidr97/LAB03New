package com.example.david.lab03;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface MoviesApi {
    @GET("/")
    Call<SearchResult> getMovies(@QueryMap Map<String, String> attributes);

    @GET("/")
    Call<MovieDetails> getMovieDetails(@QueryMap Map<String, String> attributes);
}